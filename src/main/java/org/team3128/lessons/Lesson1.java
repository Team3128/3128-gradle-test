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



public class Lesson1 extends NarwhalRobot {
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

	public ListenerManager lm;

	public Joystick joystick;


    @Override
    protected void constructHardware() {
        //this is how you print something to the console on the Driver Station
        Log.info("robot", "constructing harware for the robot!");

        victor1 = new VictorSPX(0);
        victor2 = new VictorSPX(1);
        victor1.set(ControlMode.PercentOutput, 0);
        victor2.set(ControlMode.Current, 0);

        right1 = new TalonSRX(2);
        right2 = new TalonSRX(3);
        //right1.set(ControlMode.Position, 0);
        //right1.set(ControlMode.Velocity, 0);
        right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        
        right2.set(ControlMode.Follower, right1.getDeviceID());
        left2.set(ControlMode.Follower, left1.getDeviceID());

        SRXTankDrive.initialize(left1, left2, wheelCirc, gearRatio, wheelBase, track,
				robotFreeSpeed);
        drive = SRXTankDrive.getInstance();
        
        joystick = new Joystick(1);
		lm = new ListenerManager(joystick);
		addListenerManager(lm);
    }

    @Override
    protected void setupListeners() {
        lm.nameControl(ControllerExtreme3D.JOYY, "MoveForwards");
		lm.nameControl(ControllerExtreme3D.TWIST, "MoveTurn");
		lm.nameControl(ControllerExtreme3D.THROTTLE, "Throttle");

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


		lm.nameControl(new POV(0), "IntakePOV");
		lm.addListener("IntakePOV", (POVValue pov) ->
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