package org.team3128.guido.autonomous;

import org.team3128.common.autonomous.primitives.CmdDelay;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.util.Log;

import org.team3128.guido.mechanisms.Forklift;
import org.team3128.guido.mechanisms.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGuidoBase extends CommandGroup {
	protected SRXTankDrive drive;
	protected Forklift forklift;
	protected Intake intake;

	protected AutoGuidoBase(double delay) {
		drive = SRXTankDrive.getInstance();
		//forklift = Forklift.getInstance();
		//intake = Intake.getInstance();

		//addSequential(forklift.new CmdZeroForklift());	
		addSequential(new CmdDelay(delay));
		
		Log.info("AutoGuidoBase", "Delay: " + delay);
	}
}
