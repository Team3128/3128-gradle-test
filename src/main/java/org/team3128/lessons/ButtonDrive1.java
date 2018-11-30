package org.team3128.lessons;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.drive.SRXTankDrive;





import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import org.team3128.common.util.Constants;
import org.team3128.common.util.units.Length;
import org.team3128.common.util.Log;
import org.team3128.common.listener.ListenerManager;
import org.team3128.common.listener.POVValue;
import org.team3128.common.listener.controltypes.POV;
import org.team3128.common.listener.controllers.ControllerExtreme3D;
import org.team3128.common.listener.controltypes.Button;
import edu.wpi.first.wpilibj.Joystick;




public class ButtonDrive1 extends NarwhalRobot{
    VictorSPX victor1;
    TalonSRX left1;
    TalonSRX left2;
    TalonSRX right1;
    TalonSRX right2;
    //SRXTankDrive drive;
    VictorSPX victor2;
   public SRXTankDrive drive;
   double wheelCirc = 0.0*Length.in;
   double gearRatio = 0.0;
   double wheelBase = 0.0*Length.in;
   double track = 0.0*Length.in;
   int robotFreeSpeed = 0;
    public ListenerManager lm;
    public Joystick joystick;
    @Override
    protected void constructHardware() {
        //VictorSPX victor1 = new VictorSPX(0);
        TalonSRX left1 = new TalonSRX(10);
        TalonSRX right1 = new TalonSRX(11);
        //double wheelCirc = 5.0*Length.in;
        right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        SRXTankDrive.initialize(left1, right1, wheelCirc, gearRatio, wheelBase, track,
                robotFreeSpeed);
       drive = SRXTankDrive.getInstance();
       joystick = new Joystick(1);
        lm = new ListenerManager(joystick);
        addListenerManager(lm);

    }

    @Override
    protected void autonomousInit() {
        
    }
    @Override
    protected void setupListeners() {
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
}