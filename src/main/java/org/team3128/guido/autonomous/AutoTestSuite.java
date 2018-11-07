package org.team3128.guido.autonomous;

import org.team3128.guido.autonomous.util.PowerUpAutoValues;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.team3128.common.drive.SRXTankDrive;
import org.team3128.guido.mechanisms.Forklift;

public class AutoTestSuite extends AutoGuidoBase
{
	public AutoTestSuite(double delay)
	{
		super(delay);
		TalonSRX leftMotors = drive.getLeftMotors();
		TalonSRX rightMotors = drive.getRightMotors();
		try{
		rightMotors.set(ControlMode.PercentOutput, 100);
		rightMotors.getSelectedSensorVelocity(0);
		} catch(){

		}
	}
}
