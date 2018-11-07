package org.team3128.guido.autonomous;

import org.team3128.guido.autonomous.util.PowerUpAutoValues;
//import org.graalvm.compiler.nodes.calc.LeftShiftNode;
import org.team3128.common.autonomous.primitives.CmdRunInParallel;
import org.team3128.common.util.enums.Direction;
import org.team3128.common.util.units.Length;
import org.team3128.guido.mechanisms.Forklift.ForkliftState;
import org.team3128.guido.mechanisms.Intake.IntakeState;

import org.team3128.guido.util.PlateAllocation;

public class PolygonAuto extends AutoGuidoBase {
	public PolygonAuto(double delay) {
        super(delay);
        float numberOfAngles = 5;
        float sumOfAngles1 = numberOfAngles-2;
        float sumOfAngles2 = sumOfAngles1*180;
        float oneAngle = sumOfAngles2/numberOfAngles;
        for (int x = 0;x<numberOfAngles;x++) {
            addSequential(drive.new CmdInPlaceTurn(oneAngle, 1.0, 10000, Direction.RIGHT));
            addSequential(drive.new CmdMoveForward(3*Length.ft, 10000, 1.0));
        }
        

        

        
	}
}