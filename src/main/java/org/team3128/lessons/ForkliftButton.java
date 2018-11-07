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
public class ForkliftButton extends NarwhalRobot {
    public enum forkliftState {
        DOWN(-50, "down"),
        STOPPED(0, "stopped"),
        UP(50, "up");
        private int forkliftHeight;
        private String name;
        private forkliftState(int height, String name)
        {
            this.forkliftHeight = height;
            this.name = name;
        }
    }
    //TalonSRX right1;
    //TalonSRX right2;
    TalonSRX left1;
    //TalonSRX left2;
    forkliftState state;
	public ListenerManager lm;
	public Joystick joystick;
    @Override
    protected void constructHardware() {
        Log.info("Jude's Code", "The code works");
        //right1 = new TalonSRX(11);
        //right2 = new TalonSRX(3);
        left1 = new TalonSRX(10);
        //left2 = new TalonSRX(5);
 
        //right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        
        //right2.set(ControlMode.Follower, right1.getDeviceID());
        //left2.set(ControlMode.Follower, left1.getDeviceID());

        joystick = new Joystick(0);
		lm = new ListenerManager(joystick);
		addListenerManager(lm);
    }
    @Override
    protected void setupListeners() {
		lm.nameControl(new Button(11), "up");
        lm.addButtonDownListener("up", () -> 
        {
            state = forkliftState.UP;
            Log.info("Jude's Code", state.name);
            left1.set(ControlMode.PercentOutput, state.forkliftHeight);
            Timer.delay(3);
            state = forkliftState.STOPPED;
            left1.set(ControlMode.PercentOutput, state.forkliftHeight);
        });
        lm.nameControl(new Button(12), "DOWN");
        lm.addButtonDownListener("DOWN",() -> 
        {
            state = forkliftState.DOWN;
            Log.info("Jude's Code", state.name);
            left1.set(ControlMode.PercentOutput, state.forkliftHeight);
            Timer.delay(3);
            state = forkliftState.STOPPED;
            left1.set(ControlMode.PercentOutput, state.forkliftHeight);
        });
		};

    @Override
    protected void teleopInit() {
    }

    @Override
    protected void autonomousInit() {
    }
}