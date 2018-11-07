package org.team3128.guido.autonomous;

import org.team3128.guido.autonomous.util.PowerUpAutoValues;
import org.team3128.common.autonomous.primitives.CmdRunInParallel;
import org.team3128.common.util.enums.Direction;
import org.team3128.common.util.units.Length;

import org.team3128.guido.mechanisms.Forklift.ForkliftState;
import org.team3128.guido.mechanisms.Intake.IntakeState;

import org.team3128.guido.util.PlateAllocation;

public class CoolAuto extends AutoGuidoBase {
	public CoolAuto(double delay) {
		super(delay);
        addSequential(drive.new CmdMoveForward(100, 10000, 1.0));
        
	}
}
