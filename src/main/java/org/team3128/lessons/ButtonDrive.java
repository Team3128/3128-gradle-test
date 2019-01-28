package org.team3128.lessons;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
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

public class ButtonDrive extends NarwhalRobot {
   //two VictorSPX motor controllers
   VictorSPX victor1;
   VictorSPX victor2;

   //four TalonSRX motor controllers (can have feedback device / encoder)
   TalonSRX right1;
   TalonSRX right2;
   TalonSRX left1;
   TalonSRX left2;
   AHRS ahrs;
   //type of drive train we have used for the past few years
   public SRXTankDrive drive;
   double wheelCirc = 0.0*Length.in;
   double gearRatio = 1.0;
   double wheelBase = 0.0*Length.in;
   double track = 0.0*Length.in;
   int robotFreeSpeed = 0; //native units per 100ms, a native unit is 1/4096 of a rotation

   //listens for something to change on joystick (joystick pushed, button pressed, etc)
    public ListenerManager lm;

   //what we control the robot with
    public Joystick joystick;

 
   @Override
   protected void constructHardware() {
   /*   
       //this is how you print something to the console on the Driver Station
       Log.info("robot", "constructing harware for the robot!");

       //initialization of two VictorSPX motors controller
       //victor1 = new VictorSPX(0);
      // victor2 = new VictorSPX(1);

       /**There are many ways to tell a motor what to do using
        * PercentOutput: Explanation here
        * Position: Explanation here
        * Velocity: Explanation here
        * Current: Explanation here
        * Follower: Explanation here
        * **/

       //set motor to 10% of max output
       //victor1.set(ControlMode.PercentOutput, 10);

       //set motor to a velocity of 1 cm / 100 ms

//victor2.set(ControlMode.Velocity, 1*Length.cm);
       //initialization of two TalonSRX motor controllers
       right1 = new TalonSRX(11);
       //right2 = new TalonSRX(3);
       left1 = new TalonSRX(10);
       //left2= new TalonSRX(5);

       //lets motors controller know that they will be hooked up to encoder (sensor)
       right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
       left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);

       //right2.set(ControlMode.Follower, right1.getDeviceID());
       //left2.set(ControlMode.Follower, left1.getDeviceID());

       //initilization of SRXTankDrive object.
       //hover over the word "initialize" on the line below to learn more about the params
       SRXTankDrive.initialize(left1, left2, wheelCirc, gearRatio, wheelBase, track,
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



       //below you will see lambas i.e. the ()->{} part of the code
       //a lambda essentially pases in part of a function as a parameter rather than a single value
       //I will explain this more somewhere else... or click on the website below for more info
       //https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

lm.nameControl(new Button(10), "RightMotor");
lm.addButtonDownListener("RightMotor", () ->
{
   right1.set(ControlMode.PercentOutput,50);
});
lm.addButtonUpListener("RightMotor", () ->
{
   right1.set(ControlMode.PercentOutput,0);
});
lm.nameControl(new Button(8), "LeftMotor");
lm.addButtonDownListener("LeftMotor", () ->
{
   left1.set(ControlMode.PercentOutput,50);
});
lm.addButtonUpListener("LeftMotor", () ->
{
   left1.set(ControlMode.PercentOutput,0);
});

    
   }

   @Override
   protected void teleopInit() {
   }
  /* @Override
	protected void teleopPeriodic()
	{
		Float ThetaThreshold = (float)25;     
    Float Theta=ahrs.getPitch();
    //Float Theta=ahrs.getRoll();
        if(Theta>ThetaThreshold){
            left1.set(ControlMode.PercentOutput,Theta);
            right1.set(ControlMode.PercentOutput,Theta);
        }
		// Log.info("MainGuido", ((System.currentTimeMillis() - startTimeMillis)
		// / 1000.0) + "," + (wheelCirc *
		// rightDriveLeader.getSelectedSensorVelocity(0) * 10.0 / 4096.0));
		//axzsdc\
		//drive.autoshift();
	}*/
   @Override
   protected void autonomousInit() {
   }
}