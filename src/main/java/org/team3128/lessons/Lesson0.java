package org.team3128.lessons;

import javax.script.ScriptEngineManager;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.util.units.Length;


public class Lesson0 extends NarwhalRobot{
    VictorSPX vic1;
    TalonSRX left1;
    TalonSRX left2;
    TalonSRX right1;
    TalonSRX right2;

    SRXTankDrive drive;
    double wheelCirc = 0.0*Length.in;
    double gearRatio = 0.0;
    double wheelBase = 0.0*Length.in;
    double track = 0.0*Length.in;
    int robotFreeSpeed = 0;


    @Override
    protected void constructHardware() {
        vic1=new VictorSPX(0);
        left1 = new TalonSRX(1);
        left2 = new TalonSRX(2);
        right1 = new TalonSRX(3);
        right2 = new TalonSRX(4);

        //vic1.set(ControlMode.PercentOutput, 0.50);
        //tal1.set(ControlMode.Position, 8.50);

        left2.set(ControlMode.Follower, left1.getDeviceID());
        right2.set(ControlMode.Follower, right2.getDeviceID());

        drive.initialize(left1, right1, wheelCirc, gearRatio, wheelBase, track, robotFreeSpeed);
        drive = SRXTankDrive.getInstance();
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