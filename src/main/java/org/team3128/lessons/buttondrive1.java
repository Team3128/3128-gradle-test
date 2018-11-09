package org.team3128.lessons;

import org.team3128.common.NarwhalRobot;

public class buttondrive1 extends NarwhalRobot{
    VictorSPX vic1;
    TalonSRX tal1;
    TalonSRX  tal2;
    TalonSRX tal3;
    TalonSRX tal4;

    @Override
    protected void constructHardware() {
        vic1=new VictorSPX(0);
        tal1 = new TalonSRX(1);
        tal2 = new TalonSRX(2);
        tal3 = new TalonSRX(3);
        tal4 = new TalonSRX(4);

        //vicl.set(ControlMode.PercentOutput,0.50);
        //tal1.set(ControlMode.Position, 8.50);

        left2.set(ControlMode.Follower, left1.getDeviceID());
        right2.set(ControlMode.Follwer,right2. getDeviceID());
        drive.initialize(left1, right1, wheelCirc, gearRatio, wheelBase, track, robotFreeSpeed);
        drive = new SRXTTankDrive.getInstance();
   
    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected void teleopInit() {

    }

    @Override
    protected void autonomousInit() {

    }
}