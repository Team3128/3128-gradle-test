package org.team3128.guido.autonomous;
import edu.wpi.first.wpilibj.Timer;
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

public class ForkliftAuto extends AutoGuidoBase
{
   public ForkliftAuto(double delay)
	{
        
        super(delay);
        /*addSequential(new CmdRunInParallel(
				forklift.new CmdSetForkliftPosition(ForkliftState.GROUND)
            ));
            Timer.delay(3);*/
        addSequential(new CmdRunInParallel(
			forklift.new CmdSetForkliftPosition(ForkliftState.SWITCH)
        ));
        Timer.delay(3);
        addSequential(new CmdRunInParallel(
				forklift.new CmdSetForkliftPosition(ForkliftState.GROUND)
        ));
       
            
    }
}