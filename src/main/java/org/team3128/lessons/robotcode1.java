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

public class robotcode1 extends NarwhalRobot {
    TalonSRX right1;
    TalonSRX left1;

    public ListenerManager lm;
    SRXTankDrive drive;
    public Joystick joystick;

    @Override
    protected void constructHardware() {
        Log.info("Cat's Code", "The code is Lillian");
        right1 = new TalonSRX(11);
        left1 = new TalonSRX(10);


}
