package org.team3128.guido.autonomous.debug;

import org.team3128.guido.main.MainGuido;
import org.team3128.guido.mechanisms.Forklift.ForkliftState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSetForkliftState extends CommandGroup
{
	public AutoSetForkliftState(MainGuido robot, ForkliftState state)
	{
		addSequential(robot.forklift.new CmdSetForkliftPosition(state));
	}
}