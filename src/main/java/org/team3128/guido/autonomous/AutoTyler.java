package org.team3128.guido.autonomous;

import org.team3128.guido.autonomous.AutoGuidoBase;
import org.team3128.guido.autonomous.AutoScaleFromSide;
import org.team3128.guido.autonomous.AutoSwitchFromSide;
import org.team3128.common.util.enums.Direction;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.util.Log;
import org.team3128.common.util.units.Length;

import org.team3128.common.autonomous.primitives.CmdRunInParallel;

import org.team3128.guido.mechanisms.Forklift;
import org.team3128.guido.mechanisms.Forklift.ForkliftState;
import org.team3128.guido.mechanisms.Intake.IntakeState;

import org.team3128.guido.util.PlateAllocation;

import org.team3128.guido.autonomous.util.PowerUpAutoValues;

public class AutoTyler extends AutoGuidoBase
{
    public int sides=4;
    public float angle=((sides-2)*180)/sides;
    public AutoTyler(double delay)
	{
        
        super(delay);
        for (int i=0;i<sides;i++)
            addSequential(
					drive.new CmdMoveForward(10 * Length.ft, 1500, 0.75)
					//forklift.new CmdRunIntake(IntakeState.INTAKE, 1500)
            );
            addSequential(drive.new CmdInPlaceTurn(angle, 1.0, 5000, Direction.RIGHT));
            Log.info("tyler", "It didn't fail that bad");
            
    }
}