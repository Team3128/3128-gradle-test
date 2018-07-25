package org.team3128.common.drive.pathfinder;

import com.ctre.phoenix.motion.TrajectoryPoint;

/**
 * Holds the left and right {@link TrajectoryPoint} for a specific target point
 * of the motion.
 * 
 * @author Ronak
 * 
 */
public class ProfilePoint {
    public TrajectoryPoint leftPoint, rightPoint;

    public ProfilePoint(TrajectoryPoint leftPoint, TrajectoryPoint rightPoint) {
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
    }
}