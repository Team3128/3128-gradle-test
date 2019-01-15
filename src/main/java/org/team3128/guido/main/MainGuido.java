package org.team3128.guido.main;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.listener.ListenerManager;
import org.team3128.common.listener.controllers.ControllerExtreme3D;
import org.team3128.common.util.units.Length;

import edu.wpi.first.wpilibj.Joystick;

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
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.listener.ListenerManager;
import org.team3128.common.listener.controllers.ControllerExtreme3D;
import org.team3128.common.util.units.Length;

import edu.wpi.first.wpilibj.Joystick;

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
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.team3128.guido.autonomous.AutoSideSwitchOrScale;
import org.team3128.guido.autonomous.AutoCrossBaseline;
import org.team3128.guido.autonomous.AutoScaleFromSide;
import org.team3128.guido.autonomous.AutoScaleSwitchFromRight;
import org.team3128.guido.autonomous.AutoSwitchFromCenter;
import org.team3128.guido.autonomous.AutoSwitchFromSide;
import org.team3128.guido.autonomous.AutoTwoScaleFromSide;
import org.team3128.guido.autonomous.AutoTwoSwitchFromCenter;
import org.team3128.guido.autonomous.AutoTyler;
import org.team3128.guido.autonomous.debug.AutoArcTurn;
import org.team3128.guido.autonomous.debug.AutoDriveDistance;
import org.team3128.guido.mechanisms.Forklift;
import org.team3128.guido.mechanisms.Forklift.ForkliftState;
import org.team3128.guido.mechanisms.Intake;
import org.team3128.guido.mechanisms.Intake.IntakeState;
import org.team3128.guido.util.PlateAllocation;

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

public class MainGuido extends NarwhalRobot
{
	public double auto_delay = 0;
	
	// Drive Train
	public double wheelCirc;
	public SRXTankDrive drive;
	public TalonSRX leftDriveLeader, leftDriveFollower;
	public TalonSRX rightDriveLeader, rightDriveFollower;
	AHRS ahrs;
	public ADXRS450_Gyro gyro;

	public TwoSpeedGearshift gearshift;
	public Piston gearshiftPiston, climberPiston, climberLockPiston;
	
	public double shiftUpSpeed, shiftDownSpeed;

	public int lowGearMaxSpeed;

	// Pneumatics
	public Compressor compressor;

	// Forklift
	public Forklift forklift;
	public TalonSRX forkliftMotorLeader, forkliftMotorFollower;
	DigitalInput forkliftSoftStopLimitSwitch;

	public int limitSiwtchLocation, forkliftMaxVelocity;

	// Intake
	Intake intake;
	IntakeState intakeState;
	Piston intakePiston;
	public VictorSPX intakeMotorLeader, intakeMotorFollower;
	DigitalInput intakeLimitSwitch;
	
	boolean intakeInverted;

	// Controls
	public ListenerManager listenerRight;
	public ListenerManager listenerLeft;

	public Joystick leftJoystick;
	public Joystick rightJoystick;

	// Misc(general)
	public PowerDistributionPanel powerDistPanel;

	public long startTimeMillis = 0;
	
	public DriverStation ds;
	public RobotController rc;

	public double forkliftHeight = 0;
	public double linearSpeed = 0;

	public final double lowGearRatio = 8 + 1.0/3;
	public final double highGearRatio = 3 + 2.0/3;

	public double speedMult;

	@Override
	protected void constructHardware()
	{
		// Drive Train Setup
		leftDriveLeader = new TalonSRX(20);
		leftDriveFollower = new TalonSRX(21);
		rightDriveLeader = new TalonSRX(10);
		rightDriveFollower = new TalonSRX(11);

		// set Leaders
		leftDriveLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
		rightDriveLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,
				Constants.CAN_TIMEOUT);

		// set Followers
		leftDriveFollower.set(ControlMode.Follower, leftDriveLeader.getDeviceID());
		rightDriveFollower.set(ControlMode.Follower, rightDriveLeader.getDeviceID());

		gyro = new ADXRS450_Gyro();
		
		// create SRXTankDrive
		SRXTankDrive.initialize(leftDriveLeader, rightDriveLeader, wheelCirc, 1, 25.25 * Length.in, 30.5 * Length.in,
				lowGearMaxSpeed);
		drive = SRXTankDrive.getInstance();
		
		shiftUpSpeed = 5.0 * Length.ft * 60 / wheelCirc;
		shiftDownSpeed = 4.0 * Length.ft * 60 / wheelCirc;
		
		gearshift = new TwoSpeedGearshift(false, gearshiftPiston);
		drive.addShifter(gearshift, shiftUpSpeed, shiftDownSpeed);

		// create intake
		intakeState = Intake.IntakeState.STOPPED;
		intakeMotorLeader = new VictorSPX(1);
		intakeMotorFollower = new VictorSPX(2);

		intakeMotorFollower.set(ControlMode.Follower, intakeMotorLeader.getDeviceID());
		intakeMotorLeader.setInverted(true);

		compressor = new Compressor();

		Intake.initialize(intakeMotorLeader, intakeState, intakePiston, intakeInverted);
		intake = Intake.getInstance();

		// create forklift
		forkliftMotorLeader = new TalonSRX(30);
		forkliftMotorFollower = new TalonSRX(31);

		forkliftMotorLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,
				Constants.CAN_TIMEOUT);
		forkliftMotorFollower.set(ControlMode.Follower, forkliftMotorLeader.getDeviceID());

		Forklift.initialize(ForkliftState.GROUND, forkliftMotorLeader, forkliftSoftStopLimitSwitch,
				limitSiwtchLocation, forkliftMaxVelocity);
		forklift = Forklift.getInstance();

		// instantiate PDP
		powerDistPanel = new PowerDistributionPanel();

		// set Listeners
		leftJoystick = new Joystick(1);
		listenerLeft = new ListenerManager(leftJoystick);
		addListenerManager(listenerLeft);

		rightJoystick = new Joystick(0);
		listenerRight = new ListenerManager(rightJoystick);
		addListenerManager(listenerRight);

		ds = DriverStation.getInstance();

		speedMult = wheelCirc / 409.6 / 100.0;
		
		//SmartDashboard.putNumber("Autonomous Delay", auto_delay);

		NarwhalDashboard.addButton("rezero", (boolean down) -> {
			if (down) {
				forklift.override = true;
				forklift.powerControl(-0.5);
			}
			else {
				forkliftMotorLeader.setSelectedSensorPosition(0, 0, Constants.CAN_TIMEOUT);
				forklift.powerControl(0);
				forklift.override = false;
			}
		});

		NarwhalDashboard.addButton("start_compress", (boolean down) -> {
			if (down) {
				compressor.start();
			}
		});

		NarwhalDashboard.addButton("stop_compress", (boolean down) -> {
			if (down) {
				compressor.stop();
			}
		});
	}

	@Override
	protected void setupListeners()
	{
		listenerRight.nameControl(ControllerExtreme3D.JOYY, "MoveForwards");
		listenerRight.nameControl(ControllerExtreme3D.TWIST, "MoveTurn");
		listenerRight.nameControl(ControllerExtreme3D.THROTTLE, "Throttle");

		listenerRight.addMultiListener(() ->
		{
			double x = listenerRight.getAxis("MoveForwards");
			double y = listenerRight.getAxis("MoveTurn");
			double t = listenerRight.getAxis("Throttle") * -1;
			drive.arcadeDrive(x, y, t, true);
		}, "MoveForwards", "MoveTurn", "Throttle");

		listenerRight.nameControl(new Button(2), "GearShift");
		listenerRight.addButtonDownListener("GearShift", drive::shift);

		listenerRight.nameControl(new POV(0), "IntakePOV");
		listenerRight.addListener("IntakePOV", (POVValue pov) ->
		{
			int val = pov.getDirectionValue();

			switch (val)
			{
			case 7:
			case 8:
			case 1:
				intake.setState(IntakeState.OUTTAKE);
				break;
			case 3:
			case 4:
			case 5:
				intake.setState(IntakeState.INTAKE);
				break;
			default:
				intake.setState(IntakeState.STOPPED);
			}
		});

		listenerRight.nameControl(ControllerExtreme3D.TRIGGER, "SoftDrop");
		listenerRight.addButtonDownListener("SoftDrop", () -> {
			intake.setState(IntakeState.SOFT_DROP);
		});
		listenerRight.addButtonUpListener("SoftDrop", () -> {
			intake.setState(IntakeState.STOPPED);
		});
		
		listenerRight.nameControl(new Button(5), "ForkliftRightUp");
		listenerRight.addButtonDownListener("ForkliftRightUp", () ->
		{
			forklift.powerControl(1.0);
		});
		listenerRight.addButtonUpListener("ForkliftRightUp", () ->
		{
			forklift.powerControl(0);
		});

		listenerRight.nameControl(new Button(3), "ForkliftRightDown");
		listenerRight.addButtonDownListener("ForkliftRightDown", () ->
		{
			forklift.powerControl(-0.7);
		});
		listenerRight.addButtonUpListener("ForkliftRightDown", () ->
		{
			forklift.powerControl(0.0);
		});
		
		listenerLeft.nameControl(new Button(7), "ZeroForklift");
		listenerLeft.addButtonDownListener("ZeroForklift", () ->
		{
			forkliftMotorLeader.setSelectedSensorPosition(0, 0, Constants.CAN_TIMEOUT);
		});
		
		listenerLeft.nameControl(ControllerExtreme3D.TRIGGER, "Override");
		listenerLeft.addButtonDownListener("Override", () -> {
			forklift.override = true;
		});
		listenerLeft.addButtonUpListener("Override", () -> {
			forklift.override = false;
		});

		listenerRight.nameControl(new Button(11), "StartCompressor");
		listenerRight.addButtonDownListener("StartCompressor", () ->
		{
			compressor.start();
			Log.info("MainGuido", "Starting Compressor");

		});

		listenerRight.nameControl(new Button(12), "StopCompressor");
		listenerRight.addButtonDownListener("StopCompressor", () ->
		{
			compressor.stop();
		});

		listenerLeft.nameControl(new Button(11), "ClearStickyFaults");
		listenerLeft.addButtonDownListener("ClearStickyFaults", powerDistPanel::clearStickyFaults);

		listenerLeft.nameControl(ControllerExtreme3D.JOYY, "ForkliftTest");
		listenerLeft.addListener("ForkliftTest", (double joyY) ->
		{
			forklift.powerControl(joyY);
		});
		
		listenerLeft.nameControl(new Button(11), "ReZero");
		listenerLeft.addButtonDownListener("ReZero", () -> {
			forklift.override = true;
			forklift.powerControl(-0.5);
		});
		listenerLeft.addButtonUpListener("ReZero", () -> {
			forklift.override = false;
			forklift.powerControl(0);
		});
		
		listenerLeft.nameControl(new POV(0), "IntakePOV");
		listenerLeft.addListener("IntakePOV", (POVValue pov) ->
		{
			int val = pov.getDirectionValue();

			switch (val)
			{
			case 7:
			case 8:
			case 1:
				intake.setState(IntakeState.OUTTAKE);
				break;
			case 3:
			case 4:
			case 5:
				intake.setState(IntakeState.INTAKE);
				break;
			default:
				intake.setState(IntakeState.STOPPED);
				break;
			}
		});
		
		//		listenerLeft.nameControl(new Button(9), "FullDrive");
//		listenerLeft.addButtonDownListener("FullDrive", () -> {
//			drive.arcadeDrive(-1.0, 0, 1.0, true);
//		});
//		listenerLeft.addButtonUpListener("FullDrive", () -> {
//			drive.arcadeDrive(0, 0, 1.0, true);
//		});
	}

	@Override
	protected void constructAutoPrograms()
	{
		Log.info("Guidoooooooo", "main construct autos");
		PlateAllocation.update();
		
		// Oh boy
		//auto_delay = SmartDashboard.getNumber("Autonomous Delay", 0);
		//SmartDashboard.putNumber("Autonomous Delay", auto_delay);
		
//		Debug
//		
//		NarwhalDashboard.addAuto("Drive 50 Inches", new AutoDriv`eDistance(this, 50 * Length.in));
//		NarwhalDashboard.addAuto("Drive 75 Inches", new AutoDriveDistance(this, 75 * Length.in));
//		NarwhalDashboard.addAuto("Drive 100 Inches", new AutoDriveDistance(this, 100 * Length.in));
//		NarwhalDashboard.addAuto("Drive 125 Inches", new AutoDriveDistance(this, 125 * Length.in));
//		
//		NarwhalDashboard.addAuto("Test Smooth", new AutoTestSmooth(this));
//		NarwhalDashboard.addAuto("Test Not Smooth", new AutoTestNotSmooth(this));
//		
		NarwhalDashboard.addAuto("Arc Turn Forwards", new AutoArcTurn(this, 90 * Angle.DEGREES, Direction.RIGHT));
		NarwhalDashboard.addAuto("Arc Turn Backwards", new AutoArcTurn(this, -90 * Angle.DEGREES, Direction.LEFT));
//		
//		NarwhalDashboard.addAuto("Forklift Set Scale", new AutoSetForkliftState(this, ForkliftState.SCALE));
//		NarwhalDashboard.addAuto("Forklift Set Switch", new AutoSetForkliftState(this, ForkliftState.SWITCH));
//		NarwhalDashboard.addAuto("Forklift Set Floor", new AutoSetForkliftState(this, ForkliftState.GROUND));
		auto_delay = 0;
		NarwhalDashboard.addAuto("Cross Auto Line", new AutoCrossBaseline(auto_delay));
		
		NarwhalDashboard.addAuto("Center Switch", new AutoSwitchFromCenter(auto_delay));
		NarwhalDashboard.addAuto("Center Switch x2", new AutoTwoSwitchFromCenter(0));
		NarwhalDashboard.addAuto("Tyler's Auto", new AutoTyler(0));
		NarwhalDashboard.addAuto("Right Switch or Scale", new AutoSideSwitchOrScale(Direction.RIGHT, auto_delay));
		
		NarwhalDashboard.addAuto("Left Switch", new AutoSwitchFromSide(Direction.LEFT, auto_delay));
		
		NarwhalDashboard.addAuto("Right Scale", new AutoScaleFromSide(Direction.RIGHT, auto_delay));
		
		NarwhalDashboard.addAuto("Right Scale Two", new AutoTwoScaleFromSide(Direction.RIGHT, auto_delay));

		NarwhalDashboard.addAuto("Right Scale Switch", new AutoScaleSwitchFromRight(Direction.RIGHT, auto_delay));
	}

	@Override
	protected void teleopInit()
	{
		/*forklift.disabled = false;

		intakeState = IntakeState.STOPPED;

		leftDriveLeader.setSelectedSensorPosition(0, 0, Constants.CAN_TIMEOUT);
		rightDriveLeader.setSelectedSensorPosition(0, 0, Constants.CAN_TIMEOUT);

		gearshift.shiftToHigh();
		if (gearshift.isInHighGear())
		{

			Log.info("MainGuido", "Log: Gearshift is in High Gear");

		}
		else
		{
			Log.info("MainGuido", "Log: Gearshift is in Low Gear");

		}

		startTimeMillis = System.currentTimeMillis();*/
	}

	@Override
	protected void teleopPeriodic()
	{
		Float ThetaThreshold = (float)25;     
    Float Theta=ahrs.getPitch();
    //Float Theta=ahrs.getRoll();
        if(Theta>ThetaThreshold){
            leftDriveLeader.set(ControlMode.PercentOutput,Theta);
            rightDriveLeader.set(ControlMode.PercentOutput,Theta);
        }
		// Log.info("MainGuido", ((System.currentTimeMillis() - startTimeMillis)
		// / 1000.0) + "," + (wheelCirc *
		// rightDriveLeader.getSelectedSensorVelocity(0) * 10.0 / 4096.0));
		//a
		//drive.autoshift();
	}

	@Override
	protected void disabledInit()
	{
		forklift.disabled = true;
	}
	
	@Override
	protected void disabledPeriodic()
	{
		PlateAllocation.update();
	}

	@Override
	protected void autonomousInit()
	{
		forklift.disabled = false;
		drive.shiftToLow();
	}

	@Override
	protected void updateDashboard()
	{	
		
        NarwhalDashboard.put("time", DriverStation.getInstance().getMatchTime());
		NarwhalDashboard.put("voltage", RobotController.getBatteryVoltage());
		
        SmartDashboard.putNumber("left nu/100ms", rightDriveLeader.getSelectedSensorVelocity(0));

		NarwhalDashboard.put("speed",
			speedMult * Math.abs(0.5*(rightDriveLeader.getSelectedSensorVelocity(0) + leftDriveLeader.getSelectedSensorVelocity(0)))
		);
		
		forkliftHeight = 10.25/12.0 + forkliftMotorLeader.getSelectedSensorPosition(0) / 262.95 / 12;
		NarwhalDashboard.put("height", forkliftHeight);

//		SmartDashboard.putNumber("Forklift Velocity", forkliftMotorLeader.getSelectedSensorVelocity(0));
//		SmartDashboard.putNumber("Forklift Position", forkliftMotorLeader.getSelectedSensorPosition(0));
		
//		SmartDashboard.putNumber("Current Forklift Error (in)", forklift.error / Length.in);
//		SmartDashboard.putNumber("Current Forklift Position (in)", forklift.currentPosition / Length.in);
//
//		SmartDashboard.putNumber("Right Speed (nu/100ms)", rightDriveLeader.getSelectedSensorVelocity(0));
//		SmartDashboard.putNumber("Left Speed (nu/100ms)", leftDriveLeader.getSelectedSensorVelocity(0));
//	
//		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
//		
//		SmartDashboard.putNumber("Left Encoder Position", leftDriveLeader.getSelectedSensorPosition(0));
//		SmartDashboard.putNumber("Right Encoder Position", rightDriveLeader.getSelectedSensorPosition(0));
//		
//		SmartDashboard.putNumber("Left Motor Output", leftDriveLeader.getMotorOutputPercent());
//		SmartDashboard.putNumber("Right Motor Output", rightDriveLeader.getMotorOutputPercent());
//		
//		SmartDashboard.putString("Forklift Control Mode", forklift.controlMode.getName());		
//		
//		if (drive.isInHighGear())
//		{
//			SmartDashboard.putString("Gear", "HIGH GEAR");
//		}
//		else
//		{
//			SmartDashboard.putString("Gear", "LOW GEAR");
//		}
//
//		SmartDashboard.putNumber("Battery Voltage", PowerJNI.getVinVoltage());
	}
}