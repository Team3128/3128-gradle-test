package org.team3128.guido.autonomous;

import org.team3128.guido.autonomous.AutoGuidoBase;
import org.team3128.guido.autonomous.AutoScaleFromSide;
import org.team3128.guido.autonomous.AutoSwitchFromSide;

import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.util.enums.Direction;
import org.team3128.common.util.units.Length;

import org.team3128.guido.mechanisms.Forklift;
import org.team3128.guido.util.PlateAllocation;

public class AutoSideSwitchOrScale extends AutoGuidoBase
{
	public AutoSideSwitchOrScale(Direction side, double delay)
	{
		super(delay);
		
		if (side == PlateAllocation.getScale()) {
			addSequential(new AutoScaleFromSide(side, delay));
		}
		else if (side == PlateAllocation.getNearSwitch()) {
			addSequential(new AutoSwitchFromSide(side, delay));
		}
		else {
			addSequential(drive.new CmdMoveForward(100 * Length.in, 5000, true));
			addSequential(drive.new CmdMoveForward(-50 * Length.in, 5000, true));
		}
	}
}
