package org.team3128.lessons;


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

import org.team3128.common.NarwhalRobot;
import org.team3128.common.util.Constants;
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

public class ButtonDrive extends NarwhalRobot {
    // VictorSPX victor1;
    // TalonSRX left1;
    // TalonSRX left2;
    // TalonSRX right1;
    TalonSRX testMotor;

    double wheelCirc = 0.0*Length.in;
    double gearRatio = 0.0;
    double wheelBase = 0.0*Length.in;
    double track = 0.0*Length.in;
    int robotFreeSpeed = 0;

    public ListenerManager lm;

    @Override
    protected void constructHardware() {
        // victor1 = new VictorSPX(0);
        // left1 = new TalonSRX(1);
        // left2 = new TalonSRX(2);
        // right1 = new TalonSRX(3);
        testMotor = new TalonSRX(11);

        //victor1.set(ControlMode.PercentOutput, 1);
       // talon1.set(Control.Mode.PercentOutput, 10);

        // left2.set(ControlMode.Follower, left1.getDeviceID());

        // SRXTankDrive drive = SRXTankDrive.getInstance();
        // drive.initialize(left1, right1, wheelCirc, gearRatio, wheelBase, track, robotFreeSpeed);
        // robotFreeSpeed);
        //drive = new SRXTankDrive.getInstance();
    }

    @Override
    protected void setupListeners() {
        lm.nameControl(ControllerExtreme3D.THROTTLE, "Throttle");
        // lm.addMultiListener(() ->
		// {
		// 	double t = lm.getAxis("Throttle") * -1;
		// 	drive.arcadeDrive(t, true);
		// }, "Throttle");

		lm.nameControl(new Button(11), "motorForward");
        lm.addButtonDownListener("motorForward", () -> 
        {
            testMotor.set(ControlMode.PercentOutput, 100);  
        });
        lm.addButtonUpListener("motorForward", () ->
        {
            testMotor.set(ControlMode.PercentOutput, 0);
        });
        lm.nameControl(new Button(12), "motorBack");
        lm.addButtonDownListener("motorBack", () ->
        {
            testMotor.set(ControlMode.PercentOutput, -100);
        });
        lm.addButtonUpListener("motorBack", () ->
        {
            testMotor.set(ControlMode.PercentOutput, 0);
        });
    }

    @Override
    protected void teleopInit() {
        
    }

    @Override
    protected void autonomousInit() {

    }
} 