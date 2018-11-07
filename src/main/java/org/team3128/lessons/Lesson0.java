package org.team3128.lessons;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.NarwhalRobot;
import org.team3128.common.util.Constants;
import org.team3128.common.util.units.Length;
import org.team3128.common.util.Log;
import org.team3128.common.listener.ListenerManager;
import org.team3128.common.listener.POVValue;
import org.team3128.common.listener.controltypes.POV;
import org.team3128.common.listener.controllers.ControllerExtreme3D;
import org.team3128.common.listener.controltypes.Button;
import edu.wpi.first.wpilibj.Joystick;

public class Lesson0 extends NarwhalRobot {
    //two VictorSPX motor controllers
    VictorSPX victor1;
    VictorSPX victor2;

    //four TalonSRX motor controllers (can have feedback device / encoder)
    TalonSRX right1;
    TalonSRX right2;
    TalonSRX left1;
    TalonSRX left2;

    //type of drive train we have used for the past few years
    public SRXTankDrive drive;
    double wheelCirc = 0.0*Length.in;
    double gearRatio = 0.0;
    double wheelBase = 0.0*Length.in;
    double track = 0.0*Length.in;
    int robotFreeSpeed = 0; //native units per 100ms, a native unit is 1/4096 of a rotation

    //listens for something to change on joystick (joystick pushed, button pressed, etc)
	public ListenerManager lm;

    //what we control the robot with
	public Joystick joystick;


    @Override
    protected void constructHardware() {

    }

    @Override
    protected void setupListeners() {
        //designating what each of the parts of the joystick should be called
        //aka the value of the joystick when it is pushed in the y-direction is called "MoveForwards"
        lm.nameControl(ControllerExtreme3D.JOYY, "MoveForwards");
		lm.nameControl(ControllerExtreme3D.TWIST, "MoveTurn");
		lm.nameControl(ControllerExtreme3D.THROTTLE, "Throttle");

        //below you will see lambas i.e. the ()->{} part of the code
        //a lambda essentially pases in part of a function as a parameter rather than a single value
        //I will explain this more somewhere else... or click on the website below for more info
        //https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

		lm.addMultiListener(() ->
		{
			double x = lm.getAxis("MoveForwards");
			double y = lm.getAxis("MoveTurn");
			double t = lm.getAxis("Throttle") * -1;
			drive.arcadeDrive(x, y, t, true);
		}, "MoveForwards", "MoveTurn", "Throttle");

		lm.nameControl(new Button(2), "Action");
        lm.addButtonDownListener("Action", () -> 
        {
            //do something when button is pushed
        });
        lm.addButtonUpListener("Action", () -> 
        {
            //do something when button is released
        });


		lm.nameControl(new POV(0), "POV");
		lm.addListener("POV", (POVValue pov) ->
		{
			int val = pov.getDirectionValue();

			switch (val)
			{
			case 7:
			case 8:
			case 1:
				//do something
				break;
			case 3:
			case 4:
			case 5:
				//do something
				break;
			default:
				//do something
			}
		});
    }

    @Override
    protected void teleopInit() {
    }

    @Override
    protected void autonomousInit() {
    }

}