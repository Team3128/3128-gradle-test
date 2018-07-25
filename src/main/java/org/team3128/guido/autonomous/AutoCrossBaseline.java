package org.team3128.guido.autonomous;

import org.team3128.guido.autonomous.util.PowerUpAutoValues;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.guido.mechanisms.Forklift;

public class AutoCrossBaseline extends AutoGuidoBase
{
	public AutoCrossBaseline(double delay)
	{
		super(delay);
		
		addSequential(drive.new CmdMoveForward(PowerUpAutoValues.SWITCH_FRONT_DISTANCE, 10000, 1.0));
	}
}
