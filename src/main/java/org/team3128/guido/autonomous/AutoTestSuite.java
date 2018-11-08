package org.team3128.guido.autonomous;

import org.team3128.guido.autonomous.util.PowerUpAutoValues;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.esotericsoftware.minlog.Log;

import org.team3128.common.drive.SRXTankDrive;
import org.team3128.guido.mechanisms.Forklift;

public class AutoTestSuite extends AutoGuidoBase
{
	public AutoTestSuite(double delay)
	{
		super(delay);
		TalonSRX leftMotors = drive.getLeftMotors();
		TalonSRX rightMotors = drive.getRightMotors();

		rightMotors.set(ControlMode.PercentOutput, 100);
		int code = rightMotors.getSelectedSensorPosition(0); 
		String strCode = String.valueOf(code);
		String val = String.valueOf(strCode);
		Log.info("adham", val);

	}
}
