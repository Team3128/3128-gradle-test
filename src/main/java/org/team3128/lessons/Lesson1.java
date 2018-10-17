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

    //listens for something to change on joystick (joystick pushed, button pressed, etc)
	public ListenerManager lm;

    //what we control the robot with
	public Joystick joystick;


    @Override
    protected void constructHardware() {
        //this is how you print something to the console on the Driver Station
        Log.info("robot", "constructing harware for the robot!");

        //initialization of two VictorSPX motors controller
        victor1 = new VictorSPX(0);
        victor2 = new VictorSPX(1);

        /**There are many ways to tell a motor what to do using 
         * PercentOutput: Explanation here
         * Position: Explanation here
         * Velocity: Explanation here
         * Current: Explanation here
         * Follower: Explanation here
         * **/

        //set motor to 10% of max output
        victor1.set(ControlMode.PercentOutput, 10);
        //set motor to a velocity of 1 cm / 100 ms
        victor2.set(ControlMode.Velocity, 1*Length.cm);

        //initialization of two TalonSRX motor controllers
        right1 = new TalonSRX(2);
        right2 = new TalonSRX(3);
        left1 = new TalonSRX(4);
        left2 = new TalonSRX(5);
 
        //lets motors controller know that they will be hooked up to encoder (sensor)
        right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        
        right2.set(ControlMode.Follower, right1.getDeviceID());
        left2.set(ControlMode.Follower, left1.getDeviceID());

        //initilization of SRXTankDrive object.
        //hover over the word "initialize" on the line below to learn more about the params
        SRXTankDrive.initialize(left1, right1, wheelCirc, gearRatio, wheelBase, track,
				robotFreeSpeed);
        drive = SRXTankDrive.getInstance();
        
        //initialization of joystick and listener manager
        joystick = new Joystick(1);
		lm = new ListenerManager(joystick);
		addListenerManager(lm);
    }

    @Override
    protected void setupListeners() {
        //designating what each of the parts of the joystick should be called
        //aka the value of the joystick when it is pushed in the y-direction is called "MoveForwards"
        lm.nameControl(ControllerExtreme3D.JOYX, "MoveForwards");
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
                break;
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