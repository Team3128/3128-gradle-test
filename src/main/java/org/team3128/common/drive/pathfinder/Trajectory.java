package org.team3128.common.drive.pathfinder;

/**
 * Represents the series of quintic spline segments that connects a series of waypoints.
 * 
 * @author Ronak
 * 
 */
public class Trajectory {
    private Segment[] segments;

    public Trajectory(Segment... segments) {
        this.segments = segments;
    }

    public Segment[] getSegments() {
        return segments;
    }
}