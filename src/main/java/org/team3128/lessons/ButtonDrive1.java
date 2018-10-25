package org.team3128.lessons;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.team3128.common.NarwhalRobot;




public class ButtonDrive1 extends NarwhalRobot{
    @Override
    protected void constructHardware() {
        VictorSPX victor1 = new VictorSPX(0);
        VictorSPX victor2 = new VictorSPX(1);
        TalonSRX talon2 = new TalonSRX(0);
        talon2.set(ControlMode.PercentOutput, 10);
    }

    @Override
    protected void autonomousInit() {
        
    }
    @Override
    protected void setupListeners() {
        
    }

    @Override
    protected void teleopInit() {
        
    }
}