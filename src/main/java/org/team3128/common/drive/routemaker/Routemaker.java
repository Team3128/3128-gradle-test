package org.team3128.common.drive.routemaker;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;

import org.team3128.common.drive.Odometer;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.util.Convert;
import org.team3128.common.util.Log;
import org.team3128.common.util.RobotMath;
import org.team3128.common.util.units.Length;

import edu.wpi.first.wpilibj.Timer;


/**
 * Main class for the routemaker system. Generates entire trajectories and creates the series of
 * motion profile points to follow a trajectory.
 * 
 * Okay, so I have some explaining to do. Basically, this system handles the generation of aunomtous
 * routes by generating a series of continuous, continuous slope, and continuous curvature
 * quintic splines. The splines are generated by the hardcoded math in {@link Segment}.
 * 
 * In order to follow the route, something I'm calling Team 3128's <i>Proprietary, Brute-Force Route-
 * Following Algorithm</i> (or the <i>PBFRFA</i>, pronounced "P buff-ruff-a"). It essenitally iterate through
 * the generated parametric splines in certain increments of the parameter s, hereby known as <i>parametric
 * incrememnts</i>, or <i>parincs</i>.
 * 
 * The PBFRFA uses some more math to figure out the farthest distance how far the robot can travel along
 * this path in a circular arc to arrive at another point on the route during the TrajectoryDuration
 * if traveling at a certain maximum velocity. It then passes the feed-forward velocity and distance travelled
 * by each side of the weelbase to the TalonSRXs to be followed.
 * 
 * The incredibly experimental and probably not going to work <i>Realtime PBFRFA(tm)</i> use the same Brute-Force
 * algorithm to generate the next point in the trajectory, but upon arrival at each of these points, the Realime 
 * PBFRFA generates a new spline with starting waypoint located at the estimated robot position calculated by
 * the {@link Odometer}, using that spline to produce the next {@link ProfilePoint}.
 * 
 * @author Ronak
 * 
 */
public class Routemaker {
    final public static TrajectoryDuration duration = TrajectoryDuration.Trajectory_Duration_30ms;
    final public static double durationSec = duration.value / 1000.0;
    final public static int durationMs = duration.value;

    /**
     * How many parincs would fit in a single meter
     */
    final private double K_PARINC = 100;


    // For realtime path generation and following
    // After intense deliberation, I've decided to follow an singelton pattern here,
    // as opposed to a functional programming pattern, because there are numerous persistent
    // values, and opposed to a standard object-oriented pattern due to the fact that
    // there should only be a singular instance of Routemaker that is only active for about
    // fifteen seconds.

    private static Routemaker instance = null;
    public static Routemaker getInstance() {
        if (instance != null) {
			return instance;
		}
		
		Log.fatal("Routemaker", "Attempted to get instance before initializtion! Call initialize(...) first.");
		return null;
    }

    public static void initialize(double power, double smoothness, Waypoint... waypoints) {
        instance = new Routemaker(power, smoothness, waypoints);
    }

    private double smoothness;
    private Waypoint[] waypoints;

    private double[] parincs;

    private double l_sum = 0;
    private double r_sum = 0;

    private SRXTankDrive drive;

    private double v_max;
    private double wb;

    private Routemaker(double power, double smoothness, Waypoint... waypoints) {
        this.smoothness = smoothness;
        this.waypoints = waypoints;

        drive = SRXTankDrive.getInstance();

        v_max = power * Convert.velocityCTREtoCMS(drive.robotMaxSpeed, drive.wheelCircumfrence);
        wb = drive.wheelBase;

        parincs = new double[waypoints.length - 1];

        Segment segment;

        double length = 0;
        final double STEP = 0.05;

        for (int i = 0; i < waypoints.length - 1; i++) {
            segment = new Segment(waypoints[i], waypoints[i+1], smoothness);

            for (double s = STEP; s <= 1.0; s += STEP) {
                length += RobotMath.distance(
                    segment.getX(s - STEP), segment.getY(s - STEP),
                    segment.getX(s),        segment.getY(s));
            }

            parincs[i] = K_PARINC / (Length.m * length);
        }

        Waypoint start = waypoints[0];

        Odometer.getInstance().setPosition(start.x, start.y, start.angle);

        ref_segment = 0;
    }

    private int ref_segment;
    private Segment spline;
    
    private double x_tp, y_tp, a_tp;

    private double x_l_tp, y_l_tp;

    private double x_r_tp, y_r_tp;

    private double x_p, y_p, a_p, d_p;

    private double x, y, a;
    private double x_l, y_l, x_r, y_r;
    private double da, d, dt;

    private double v_major, v_minor;
    private double v_l, v_r;

    private double r_l, r_r;

    private double s;

    private double v_cap;

    private boolean last = false;

    public ProfilePoint getNextPoint(double spdFrac) {
        x_tp = Odometer.getInstance().getX();
        y_tp = Odometer.getInstance().getY();
        a_tp = Odometer.getInstance().getAngle();

        spline = new Segment(new Waypoint(x_tp, y_tp, a_tp), waypoints[ref_segment+1], smoothness);

        x_l_tp = x_tp - (wb / 2) * RobotMath.cos(a_tp - 90);
        y_l_tp = y_tp - (wb / 2) * RobotMath.sin(a_tp - 90);

        x_r_tp = x_tp + (wb / 2) * RobotMath.cos(a_tp - 90);
        y_r_tp = y_tp + (wb / 2) * RobotMath.sin(a_tp - 90);

        x_p = x_tp;
        y_p = y_tp;
        a_p = a_tp;
        d_p = 0;

        dt = 0;
        
        v_cap = spdFrac * v_max;

        while (dt < durationSec) {
            s = parincs[ref_segment];
            while (s <= 1) {
                x = spline.getX(s);
                y = spline.getY(s);
                a = spline.getAngle(s);

                da = a - a_tp;
                if (da > 360 - Constants.ARBITRARY_LOOP_PROTECTION_BUFFER) {
                    da = 360 - da;
                }
                else if (da < Constants.ARBITRARY_LOOP_PROTECTION_BUFFER - 360) {
                    da = 360 + da;
                }

                d = RobotMath.distance(x, y, x_tp, y_tp);

                dt = (wb * Math.abs(Math.toRadians(da)) / 4 + d) / v_cap;

                s += parincs[ref_segment];

                if (dt < durationSec) {
                    x_p = x;
                    y_p = y;
                    a_p = a;

                    d_p = d;
                }
                else {
                    break;
                }
            }

            if (dt < durationSec) {
                ref_segment++;
            }

            if (ref_segment == waypoints.length - 2) {
                Waypoint lastPoint = waypoints[waypoints.length - 1];

                x_p = lastPoint.x;
                y_p = lastPoint.y;
                a_p = lastPoint.angle;

                last = true;
            }
        }

        v_major = Convert.velocityCMStoCTRE(v_cap, drive.wheelCircumfrence);
        v_minor = Convert.velocityCMStoCTRE(2 * d_p / durationSec - v_major, drive.wheelCircumfrence);

        x_l = x_p - (wb / 2) * RobotMath.cos(a_p - 90);
        y_l = y_p - (wb / 2) * RobotMath.sin(a_p - 90);

        x_r = x_p + (wb / 2) * RobotMath.cos(a_p - 90);
        y_r = y_p + (wb / 2) * RobotMath.sin(a_p - 90);

        if (da > 0) {
            v_l = v_minor;
            v_r = v_major;

            r_l = RobotMath.distance(x_l_tp, y_l_tp, x_l, y_l) / (2 * RobotMath.sin(da/2));
            r_r = r_l + wb;
        }
        else {
            v_l = v_major;
            v_r = v_minor;

            r_r = RobotMath.distance(x_r_tp, y_r_tp, x_r, y_r) / (2 * RobotMath.sin(-1.0 * da/2));
            r_l = r_r + wb;
        }

        l_sum += Convert.lengthCMtoCTRE(r_l * Math.abs(Math.toRadians(da)), drive.wheelCircumfrence);
        r_sum += Convert.lengthCMtoCTRE(r_r * Math.abs(Math.toRadians(da)), drive.wheelCircumfrence);

        return (new ProfilePoint(last, l_sum, r_sum, (last) ? 0 : v_l, (last) ? 0 : v_r));
    }


    // For static path generation
    public static Trajectory generate(double smoothness, Waypoint... waypoints) {
        Segment[] segments = new Segment[waypoints.length - 1];

        for (int i = 0; i < waypoints.length - 1; i++) {
            segments[i] = new Segment(waypoints[i], waypoints[i+1], smoothness);
        }
        
        return new Trajectory(segments);
    }

    public static List<ProfilePoint> getStaticProfilePoints(Trajectory trajectory, double power) {
        // Length        is measured in cm
        // Velocity      is measured in cm/s
        // Acceleration  is measured in cm/s^2

        // The only time nu and nu/100ms are used is for passing the feed-fwd
        // velocity and feedback distance to the Talon SRX through TrajectoryPoint

        SRXTankDrive drive = SRXTankDrive.getInstance();

        final double v_max = power * Convert.velocityCTREtoCMS(drive.robotMaxSpeed, drive.wheelCircumfrence);
        final double wb = drive.wheelBase;

        final int num_segments = trajectory.getSegments().length;
        final int precision = 1000;

        final Waypoint start = trajectory.getStart();

        double l_sum = 0;
        double r_sum = 0;

        double x_tp = start.x;
        double y_tp = start.y;
        double a_tp = start.angle;

        double x_l_tp = start.x - (wb / 2) * RobotMath.cos(start.angle - 90);
        double y_l_tp = start.y - (wb / 2) * RobotMath.sin(start.angle - 90);

        double x_r_tp = start.x + (wb / 2) * RobotMath.cos(start.angle - 90);
        double y_r_tp = start.y + (wb / 2) * RobotMath.sin(start.angle - 90);

        double x_p = start.x;
        double y_p = start.y;
        double a_p = start.angle;
        double d_p = 0;

        double x, y, a;
        double x_l, y_l, x_r, y_r;
        double da, d, dt;

        double v_major, v_minor;
        double v_l, v_r;

        double r_l, r_r;

        List<ProfilePoint> points = new ArrayList<ProfilePoint>();
        points.add(new ProfilePoint(false, l_sum, r_sum, 0, 0));

        //System.out.println("s,d,dt,da,a,x,y");

        //double start_time;
        for (int step = 1; step < precision * num_segments; step++) {
            //start_time = Timer.getFPGATimestamp();
            double s = 1.0 * (step % precision) / precision;

            Segment segment = trajectory.getSegments()[step / precision];

            x = segment.getX(s);
            y = segment.getY(s);
            a = segment.getAngle(s);

            da = a - a_tp;
            if (da > 360 - Constants.ARBITRARY_LOOP_PROTECTION_BUFFER) {
                da = 360 - da;
            }
            else if (da < Constants.ARBITRARY_LOOP_PROTECTION_BUFFER - 360) {
                da = 360 + da;
            }
            
            d = RobotMath.distance(x, y, x_tp, y_tp);

            dt = (wb * Math.abs(Math.toRadians(da)) / 4 + d) / v_max;

            if (dt > durationSec) {
                v_major = Convert.velocityCMStoCTRE(v_max, drive.wheelCircumfrence);
                v_minor = Convert.velocityCMStoCTRE(2 * d_p / durationSec - v_major, drive.wheelCircumfrence);
        
                x_l = x_p - (wb / 2) * RobotMath.cos(a_p - 90);
                y_l = y_p - (wb / 2) * RobotMath.sin(a_p - 90);
        
                x_r = x_p + (wb / 2) * RobotMath.cos(a_p - 90);
                y_r = y_p + (wb / 2) * RobotMath.sin(a_p - 90);
        
                if (da > 0) {
                    v_l = v_minor;
                    v_r = v_major;
        
                    r_l = RobotMath.distance(x_l_tp, y_l_tp, x_l, y_l) / (2 * RobotMath.sin(da/2));
                    r_r = r_l + wb;
                }
                else {
                    v_l = v_major;
                    v_r = v_minor;
        
                    r_r = RobotMath.distance(x_r_tp, y_r_tp, x_r, y_r) / (2 * RobotMath.sin(-1.0 * da/2));
                    r_l = r_r + wb;
                }
        
                l_sum += Convert.lengthCMtoCTRE(r_l * Math.abs(Math.toRadians(da)), drive.wheelCircumfrence);
                r_sum += Convert.lengthCMtoCTRE(r_r * Math.abs(Math.toRadians(da)), drive.wheelCircumfrence);

                points.add(new ProfilePoint(false, l_sum, r_sum, v_l, v_r));

                x_tp = x_p;
                x_l_tp = x_l;
                x_r_tp = x_r;
                
                y_tp = y_p;
                y_l_tp = y_l;
                y_r_tp = y_r;

                a_tp = a_p;
            }
            else {
                x_p = x;
                y_p = y;
                a_p = a;

                d_p = d;
            }
        }

        return points;
    }
}