package org.team3128;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.util.Constants;

public class Lesson1 extends NarwhalRobot{
    VictorSPX victor1;
    VictorSPX victor2;

    TalonSRX talon1;
    TalonSRX talon2;


    @Override
    protected void constructHardware() {
        victor1 = new VictorSPX(0);
        victor2 = new VictorSPX(1);
        victor1.set(ControlMode.PercentOutput, 0);
        victor2.set(ControlMode.Current, 0);
        
        talon1 = new TalonSRX(2);
        talon2 = new TalonSRX(3);
        talon1.set(ControlMode.Position, 0);
        talon2.set(ControlMode.Velocity, 0);

        talon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        talon2.set(ControlMode.Follower, talon1.getDeviceID());
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