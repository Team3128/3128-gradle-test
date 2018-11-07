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
import edu.wpi.first.wpilibj.Timer;
import org.team3128.guido.autonomous.AutoSideSwitchOrScale;
import org.team3128.guido.autonomous.AutoCrossBaseline;
import org.team3128.guido.autonomous.AutoScaleFromSide;
import org.team3128.guido.autonomous.AutoScaleSwitchFromRight;
import org.team3128.guido.autonomous.AutoSwitchFromCenter;
import org.team3128.guido.autonomous.AutoSwitchFromSide;
import org.team3128.guido.autonomous.AutoTwoScaleFromSide;
import org.team3128.guido.autonomous.AutoTwoSwitchFromCenter;
import org.team3128.guido.autonomous.debug.AutoArcTurn;
import org.team3128.guido.autonomous.debug.AutoDriveDistance;
import org.team3128.guido.mechanisms.Forklift;
import org.team3128.guido.mechanisms.Forklift.ForkliftState;
import org.team3128.guido.mechanisms.Intake;
import org.team3128.guido.mechanisms.Intake.IntakeState;
import org.team3128.guido.util.PlateAllocation;
import org.team3128.guido.autonomous.AutoTyler;
import org.team3128.common.NarwhalRobot;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.hardware.misc.Piston;
import org.team3128.common.hardware.misc.TwoSpeedGearshift;
import org.team3128.common.listener.ListenerManager;
import org.team3128.common.listener.POVValue;
import org.team3128.common.listener.controllers.ControllerExtreme3D;
import org.team3128.common.listener.controltypes.Button;
import org.team3128.common.listener.controltypes.POV;
import org.team3128.common.narwhaldashboard.NarwhalDashboard;
import org.team3128.common.util.Constants;
import org.team3128.common.util.Log;
import org.team3128.common.util.enums.Direction;
import org.team3128.common.util.units.Angle;
import org.team3128.common.util.units.Length;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team3128.guido.autonomous.AutoTyler;

public class ButtonDrive extends NarwhalRobot {
   //two VictorSPX motor controllers
   //VictorSPX victor1;
   //VictorSPX victor2;

   //four TalonSRX motor controllers (can have feedback device / encoder)
   TalonSRX right1;

   //TalonSRX right2;
   TalonSRX left1;
  
   SRXTankDrive drive;
   //TalonSRX left2;
   heightState state;
   public static enum heightState{
        forward(50),
        reverse(-50);
        public double direction;
        private heightState(double tempHeight){
            this.direction = tempHeight;
        }
    }
    //public static void moveMotor(){

    
   // }
            
   //type of drive train we have used for the past few years
  /* public SRXTankDrive drive;
   double wheelCirc = 0.0*Length.in;
   double gearRatio = 0.0;
   double wheelBase = 0.0*Length.in;
   double track = 0.0*Length.in;
   int robotFreeSpeed = 0;*/ //native units per 100ms, a native unit is 1/4096 of a rotation

   //listens for something to change on joystick (joystick pushed, button pressed, etc)
    public ListenerManager lm;

   //what we control the robot with
    public Joystick joystick;

    
   @Override
   protected void constructHardware() {
       //this is how you print something to the console on the Driver Station
       Log.info("tyler", "constructing harware for the robot!");

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
       SRXTankDrive.initialize(left1, right1, 20, 1, 12, 12,
                12);
       drive = SRXTankDrive.getInstance();

       //initialization of joystick and listener manager
       joystick = new Joystick(0);
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
        state = heightState.reverse;
        right1.set(ControlMode.PercentOutput,-50);
        Timer.delay(5);
        right1.set(ControlMode.PercentOutput,0);
        Log.info("tyler","The state was set to reverse");
        /*right1.set(ControlMode.PercentOutput,50);
        Timer.delay(5);
        right1.set(ControlMode.PercentOutput,0);*/
   //right1.set(ControlMode.PercentOutput,50);
});
lm.addButtonUpListener("RightMotor", () ->
{
   //right1.set(ControlMode.PercentOutput,0);
});
lm.nameControl(new Button(8), "LeftMotor");
lm.addButtonDownListener("LeftMotor", () ->
{
    Log.info("tyler","The state was set to forward");
    state = heightState.forward;
    right1.set(ControlMode.PercentOutput,50);
        Timer.delay(5);
        right1.set(ControlMode.PercentOutput,0);
   //left1.set(ControlMode.PercentOutput,50);
});
lm.addButtonUpListener("LeftMotor", () ->
{
   //left1.set(ControlMode.PercentOutput,0);
});

//if (prevforkliftHeight!=forkliftHeight){
    //prevforkliftHeight=forkliftHeight;
    Log.info("robot","The state is"+state);
    if (state == heightState.forward) {
        right1.set(ControlMode.PercentOutput,50);
        Timer.delay(5);
        right1.set(ControlMode.PercentOutput,0);
    }
    if (state == heightState.reverse) {
        right1.set(ControlMode.PercentOutput,-50);
        Timer.delay(5);
        right1.set(ControlMode.PercentOutput,0);
    }
    
} 
   //}

   @Override
   protected void teleopInit() {
   }

   @Override
   protected void autonomousInit() {
       
   }
   @Override
   protected void constructAutoPrograms() {
        NarwhalDashboard.addAuto("Tyler's Auto", new AutoTyler(0));
   }

   
}