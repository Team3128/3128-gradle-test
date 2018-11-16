package org.team3128.guido.autonomous;

import org.team3128.guido.autonomous.util.PowerUpAutoValues;
import org.team3128.common.autonomous.primitives.CmdDelay;
import org.team3128.common.autonomous.primitives.CmdLog;
import org.team3128.common.autonomous.primitives.CmdRunInParallel;
import org.team3128.common.autonomous.primitives.CmdRunInSeries;
import org.team3128.common.util.enums.Direction;
import org.team3128.common.util.units.Angle;
import org.team3128.common.util.units.Length;
import edu.wpi.first.wpilibj.Timer;

import org.team3128.guido.mechanisms.Forklift.ForkliftState;
import org.team3128.guido.mechanisms.Intake.IntakeState;

import org.team3128.guido.util.PlateAllocation;

public class AutoMoveForklift extends AutoGuidoBase
{
	public AutoMoveForklift(double delay)
	{
		super(delay);
        addSequential(forklift.new CmdSetForkliftPosition(ForkliftState.SCALE));
        Timer.delay(2);
        addSequential(forklift.new CmdSetForkliftPosition(ForkliftState.GROUND));
	}
}