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

public class Drive extends NarwhalRobot{

    TalonSRX right1;
    TalonSRX left1;

    public ListenerManager lm;

    protected void constructHardware() {
        right1 = new TalonSRX(11);

        left1 = new TalonSRX(10);
    


    }
    protected void setupListeners() {
        lm.nameControl(new Button(9),"forwardright");
        lm.addButtonDownListener("forwardright", () -> 
        {
            left1.set(ControlMode.PercentOutput, 90);
            //do something when button is pushed
        });
    

        lm.nameControl(new Button(10),"forwardleft");
        lm.addButtonDownListener("forwardleft", () -> 
        {
            right1.set(ControlMode.PercentOutput, 90);
            //do something when button is pushed
        });

        lm.nameControl(new Button(11),"backleft");
        lm.addButtonDownListener("backleft", () -> 
        {
            left1.set(ControlMode.PercentOutput,-90);
            //do something when button is pushed
        });
    
        lm.nameControl(new Button(12),"backright");
        lm.addButtonDownListener("backright", () -> 
        {
            right1.set(ControlMode.PercentOutput,-90);
            //do something when button is pushed
        });
    }

    @Override
    protected void teleopInit() {

    }

    @Override
    protected void autonomousInit() {

    }
}