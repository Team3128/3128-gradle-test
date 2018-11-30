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


public class AndrewGButtonDrive extends NarwhalRobot {

    TalonSRX right;
    TalonSRX left;

    public SRXTankDrive tankdrive;
    double wheelCirc = 0.0*Length.in;
    double gearRatio = 0.0;
    double wheelBase = 0.0*Length.in;
    double track = 0.0*Length.in;
    int robotFreeSpeed = 0;


    public ListenerManager lm;
	public Joystick joystick;

    @Override
    protected void constructHardware() {
        right = new TalonSRX(2);
        left = new TalonSRX(3);

        right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);
        left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.CAN_TIMEOUT);

        SRXTankDrive.initialize(right, left, wheelCirc, gearRatio, wheelBase, track, robotFreeSpeed);
        tankdrive=SRXTankDrive.getInstance();
        
        //initialization of joystick and listener manager
        joystick = new Joystick(1);
		lm = new ListenerManager(joystick);
		addListenerManager(lm);
    }

    @Override
    protected void setupListeners() {
        lm.nameControl(ControllerExtreme3D.JOYX, "MoveForwards");
		lm.nameControl(ControllerExtreme3D.TWIST, "MoveTurn");
		lm.nameControl(ControllerExtreme3D.THROTTLE, "Throttle");
        //if presssed: 
    }

    @Override
    protected void teleopInit() {
        //nothing here
    }

    @Override
    protected void autonomousInit() {
        //nothing here
    }
}