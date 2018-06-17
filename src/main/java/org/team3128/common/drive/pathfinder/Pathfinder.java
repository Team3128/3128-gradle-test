package org.team3128.common.drive.pathfinder;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motion.TrajectoryPoint;

/**
 * Main class for the pathfinder system. Generates entire trajectories and creates the series of motion profile points to follow a trajectory.
 * 
 * @author Ronak
 * 
 */
public class Pathfinder {
    public static Trajectory generate(double smoothness, Waypoint... waypoints) {
        Segment[] segments = new Segment[waypoints.length - 1];

        for (int i = 0; i < waypoints.length - 1; i++) {
            segments[i] = new Segment(waypoints[i], waypoints[i+1], smoothness);
        }
        
        return new Trajectory(segments);
    }

    public static List<TrajectoryPoint> getProfilePoints(Trajectory trajectory) {
        List<TrajectoryPoint> points = new ArrayList<TrajectoryPoint>();

        // Actually figure it out

        return points;
    }
}