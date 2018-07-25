package org.team3128.common.drive.pathfinder;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;

import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.util.Convert;
import org.team3128.common.util.RobotMath;


/**
 * Main class for the pathfinder system. Generates entire trajectories and creates the series of motion profile points to follow a trajectory.
 * 
 * @author Ronak
 * 
 */
public class Pathfinder {
    final static TrajectoryDuration duration = TrajectoryDuration.Trajectory_Duration_30ms;
    final public static double durationSec = duration.value / 1000.0;
    final public static int durationMs = duration.value;

    public static Trajectory generate(double smoothness, Waypoint... waypoints) {
        Segment[] segments = new Segment[waypoints.length - 1];

        for (int i = 0; i < waypoints.length - 1; i++) {
            segments[i] = new Segment(waypoints[i], waypoints[i+1], smoothness);
        }
        
        return new Trajectory(segments);
    }

    public static List<ProfilePoint> getProfilePoints(Trajectory trajectory) {
        // Length        is measured in cm
        // Velocity      is measured in cm/s
        // Acceleration  is measured in cm/s^2

        // The only time nu and nu/100ms are used is for passing the feed-fwd
        // velocity and feedback distance to the Talon SRX through TrajectoryPoint

        List<ProfilePoint> points = new ArrayList<ProfilePoint>();

        SRXTankDrive drive = SRXTankDrive.getInstance();

        final double v_max = Convert.velocityCTREtoCMS(drive.robotMaxSpeed, drive.wheelCircumfrence);
        final double wb = drive.wheelBase;

        final int num_segments = trajectory.getSegments().length;
        final int precision = 10000;

        final Waypoint start = trajectory.getStart();

        double l_sum = 0;
        double r_sum = 0;

        double x_tp = start.x;
        double y_tp = start.y;
        double a_tp = start.angle;

        double x_l_tp = start.x - (wb / 2) * RobotMath.cos(start.angle - 90);
        double y_l_tp = start.x - (wb / 2) * RobotMath.sin(start.angle - 90);

        double x_r_tp = start.x + (wb / 2) * RobotMath.cos(start.angle - 90);
        double y_r_tp = start.x + (wb / 2) * RobotMath.sin(start.angle - 90);

        double x_p = start.x;
        double y_p = start.y;
        double a_p = start.angle;
        double d_p = 0;

        double x, y, a;
        double x_l, y_l, x_r, y_r;
        double da, d, dt;

        double v_major, v_minor;
        double v_l, v_r;

        for (int step = 1; step < precision * num_segments; step++) {
            double s = 1.0 * (step % precision) / precision;

            Segment segment = trajectory.getSegments()[step / precision];

            x = segment.getX(s);
            y = segment.getY(s);
            a = segment.getAngle(s);

            da = a - a_tp;
            d = RobotMath.distance(x, y, x_tp, y_tp);

            System.out.println(s + ": " + d);

            dt = (wb * Math.toRadians(Math.abs(da)) / 4 + d) / v_max;

            if (dt > durationSec) {
                v_major = Convert.velocityCMStoCTRE(v_max, drive.wheelCircumfrence);
                v_minor = Convert.velocityCMStoCTRE(2 * d_p / durationSec - v_major, drive.wheelCircumfrence);

                if (da > 0) {
                    v_l = v_minor;
                    v_r = v_major;
                }
                else {
                    v_l = v_major;
                    v_r = v_minor;
                }

                x_l = x_p + (wb / 2) * RobotMath.cos(a_p - 90);
                y_l = y_p + (wb / 2) * RobotMath.sin(a_p - 90);

                x_r = x_p - (wb / 2) * RobotMath.cos(a_p - 90);
                y_r = y_p - (wb / 2) * RobotMath.sin(a_p - 90);

                l_sum += Convert.lengthCMtoCTRE(RobotMath.distance(x_l, y_l, x_l_tp, y_l_tp), drive.wheelCircumfrence);
                r_sum += Convert.lengthCMtoCTRE(RobotMath.distance(x_r, y_r, x_r_tp, y_r_tp), drive.wheelCircumfrence);

                TrajectoryPoint leftPoint = new TrajectoryPoint();
                leftPoint.position = l_sum;
                leftPoint.velocity = v_l;
                leftPoint.profileSlotSelect0 = 0;
                leftPoint.timeDur = duration;
                leftPoint.zeroPos = false;
                leftPoint.isLastPoint = false;

                TrajectoryPoint rightPoint = new TrajectoryPoint();
                rightPoint.position = r_sum;
                rightPoint.velocity = v_r;
                rightPoint.profileSlotSelect0 = 0;
                rightPoint.timeDur = duration;
                rightPoint.zeroPos = false;
                rightPoint.isLastPoint = false;

                points.add(new ProfilePoint(leftPoint, rightPoint));

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