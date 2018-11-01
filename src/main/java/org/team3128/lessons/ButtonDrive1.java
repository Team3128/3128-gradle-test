package org.team3128.lessons;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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




public class ButtonDrive1 extends NarwhalRobot{
    TalonSRX left1;
    TalonSRX right1;


    public SRXTankDrive tankdrive;
    double wheelCirc = 0.0*Length.in;
    double gearRatio = 0.0;
    double wheelBase = 0.0*Length.in;
    double track = 0.0*Length.in;
    int robotFreeSpeed = 0;

    public ListenerManager listenerManager;
    public Joystick Joystick1;

    @Override
    protected void constructHardware() {
        Log.info("robot","Making robot hardware");
        left1 = new TalonSRX(0);
        right1 = new TalonSRX(1);

        SRXTankDrive.initialize(left1, right1, wheelCirc, gearRatio, wheelBase, track, robotFreeSpeed);
        tankdrive=SRXTankDrive.getInstance();

        Joystick1 = new Joystick(6);
        listenerManager = new ListenerManager(Joystick1);
        addListenerManager(listenerManager);
    }

    @Override
    protected void setupListeners() {
        listenerManager.nameControl(new Button(11), "LeftMotor");
        listenerManager.addButtonDownListener("LeftMotor", () ->
        {
            left1.set(ControlMode.PercentOutput,50);
        });
        listenerManager.addButtonUpListener("LeftMotor", () ->
        {
           left1.set(ControlMode.PercentOutput, 0);
        });

        listenerManager.nameControl(new Button(12), "RightMotor");
        listenerManager.addButtonDownListener("RightMotor", () ->
        {
            left1.set(ControlMode.PercentOutput,50);
        });
        listenerManager.addButtonUpListener("RightMotor", () ->
        {
           left1.set(ControlMode.PercentOutput,0);
        });

        listenerManager.nameControl(new Button(9), "BothMotors");
        listenerManager.addButtonDownListener("BothMotors", () ->
        {
            left1.set(ControlMode.PercentOutput,30);
            right1.set(ControlMode.PercentOutput,30);
        });
        listenerManager.addButtonUpListener("BothMotors", () ->
        {
           left1.set(ControlMode.PercentOutput,0);
        });
    }

    @Override
    protected void teleopInit() {
    }

    @Override
    protected void autonomousInit() {
    }
}

