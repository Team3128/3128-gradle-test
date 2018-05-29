package org.team3128.common.drive.pathfinder;

import java.util.HashMap;

import org.team3128.common.util.RobotMath;

/**
 * Represents a single quintic spline that makes up part of the entire
 * parameterized trajectory.
 * 
 * @author Ronak
 *
 */
public class Segment {
	private double x0, x1;
	private double x0p, x1p;
	
	private double y0, y1;
	private double y0p, y1p;
	
	private double ax, bx, cx;
	private double ay, by, cy;
	
	public static void main(String[] args) {
		 
	}
	
	public Segment(Waypoint first, Waypoint second, double smoothness) {		
		x0 = first.x;
		x1 = second.x;
		
		x0p = smoothness * RobotMath.cos(first.angle);
		x1p = smoothness * RobotMath.cos((second.angle));
		
		ax = 0.5 * (-12 * x0 -  6 * x0p + 12 * x1 -  6 * x1p);
		bx = 0.5 * ( 30 * x0 + 16 * x0p - 30 * x1 + 14 * x1p + 1);
		cx = 0.5 * (-20 * x0 - 12 * x0p + 20 * x1 -  8 * x1p - 2);
		
		
		y0 = first.y;
		y1 = second.y;
		
		y0p = smoothness * RobotMath.sin(first.angle);
		y1p = smoothness * RobotMath.sin(second.angle);
		
		ay = 0.5 * (-12 * y0 -  6 * y0p + 12 * y1 -  6 * y1p);
		by = 0.5 * ( 30 * y0 + 16 * y0p - 30 * y1 + 14 * y1p + 1);
		cy = 0.5 * (-20 * y0 - 12 * y0p + 20 * y1 -  8 * y1p - 2);
	}
	
	public double getX(double s) {
		//return 0.5 * ax * Math.pow(s, 5) + 0.5 * bx * Math.pow(s, 4) + 0.5 * cx * Math.pow(s, 3) + 0.5 * Math.pow(s, 2) + x0p * s + x0;
		return RobotMath.polynomial(s, ax, bx, cx, 0.5, x0p, x0);
	}
	
	public double getY(double s) {
		//return 0.5 * ay * Math.pow(s, 5) + 0.5 * by * Math.pow(s, 4) + 0.5 * cy * Math.pow(s, 3) + 0.5 * Math.pow(s, 2) + y0p * s + y0;
		return RobotMath.polynomial(s, ay, by, cy, 0.5, y0p, y0);
	}
}
