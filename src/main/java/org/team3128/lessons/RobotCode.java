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
import org.team3128.common.narwhaldashboard.NarwhalDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team3128.guido.autonomous.CoolAuto;
import org.team3128.guido.autonomous.PolygonAuto;

public class RobotCode extends NarwhalRobot {

    TalonSRX right1;
    //TalonSRX right2;
    TalonSRX left1;
    //TalonSRX left2;

	public ListenerManager lm;

    public Joystick joystick;
    
    SRXTankDrive drive;

    @Override
    protected void constructHardware() {
        Log.info("Jude's Code", "The code works");
        right1 = new TalonSRX(11);
        //right2 = new TalonSRX(3);
        left1 = new TalonSRX(10);
        //left2 = new TalonSRX(5);
 
        right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);

        SRXTankDrive.initialize(left1, right1, 20, 1, 25.25 * Length.in, 30.5 * Length.in, 20);
		drive = SRXTankDrive.getInstance();
        //right2.set(ControlMode.Follower, right1.getDeviceID());
        //left2.set(ControlMode.Follower, left1.getDeviceID());

        joystick = new Joystick(0);
		lm = new ListenerManager(joystick);
		addListenerManager(lm);
    }

    @Override
    protected void setupListeners() {
        Log.info("Jude's Code", "working");
		lm.nameControl(new Button(11), "Left Forward");
        lm.addButtonDownListener("Left Forward", () -> 
        {
            Log.info("Jude's Code", "Left Forward");
            left1.set(ControlMode.PercentOutput, 75);
        });
        lm.addButtonUpListener("Left Forward", () -> 
        {
            left1.set(ControlMode.PercentOutput, 0);
        });
        lm.nameControl(new Button(12), "Right Forward");
        lm.addButtonDownListener("Right Forward", () -> 
        {
            right1.set(ControlMode.PercentOutput, 75);
        });
        lm.addButtonUpListener("Right Forward", () -> 
        {
            right1.set(ControlMode.PercentOutput, 0);
        });
        lm.nameControl(new Button(9), "Left Backward");
        lm.addButtonDownListener("Left Backward", () -> 
        {
            left1.set(ControlMode.PercentOutput, -50);
        });
        lm.addButtonUpListener("Left Backward", () -> 
        {
            left1.set(ControlMode.PercentOutput, 0);
        });
        lm.nameControl(new Button(10), "Right Backward");
        lm.addButtonDownListener("Right Backward", () -> 
        {
            right1.set(ControlMode.PercentOutput, -50);
        });
        lm.addButtonUpListener("Right Backward", () -> 
        {
            right1.set(ControlMode.PercentOutput, 0);
        });
		};

    @Override
    protected void teleopInit() {
    }

    @Override
    protected void autonomousInit() {

    }
    @Override
	protected void constructAutoPrograms()
	{
        NarwhalDashboard.addAuto("Cool Auto", new CoolAuto(0));
        NarwhalDashboard.addAuto("Polygon Auto", new PolygonAuto(0));
    }
}