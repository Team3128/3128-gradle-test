package org.team3128.guido.autonomous.util;

import org.team3128.guido.util.PlateAllocation;

import edu.wpi.first.wpilibj.command.Command;

public class CmdUpdatePlateAlloc extends Command {
	private int msec;
	public CmdUpdatePlateAlloc(int msec)
	{
		super(msec / 1000.0);
		this.msec = msec;
	}
	
	@Override
	protected void initialize()
	{
		super.initialize();
		PlateAllocation.fetched = false;
		PlateAllocation.update();
	}
	
	@Override
	protected boolean isFinished()
	{
		return isTimedOut() || PlateAllocation.fetched;
	}
	
}