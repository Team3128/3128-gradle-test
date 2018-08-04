package org.team3128.gradletest.main;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.narwhaldashboard.NarwhalDashboard;
import org.team3128.common.util.Log;
import org.team3128.gradletest.autonomous.CmdAutoSpeaketh;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;

public class MainNarDashTest extends NarwhalRobot {
	@Override
	protected void constructHardware() {
        
    }
    
    @Override
    protected void constructAutoPrograms() {
        NarwhalDashboard.addAuto("Say \'Hello\'", new CmdAutoSpeaketh("Hello"));
        NarwhalDashboard.addAuto("Say \'Bonjour\'", new CmdAutoSpeaketh("Bonjour"));
    }

	@Override
	protected void setupListeners() {
		
	}

	@Override
	protected void teleopInit() {
		
	}

	@Override
	protected void autonomousInit() {
		
    }
    
    @Override
    protected void updateDashboard() {
        NarwhalDashboard.put("time", DriverStation.getInstance().getMatchTime());
        NarwhalDashboard.put("voltage", RobotController.getBatteryVoltage());

        NarwhalDashboard.addButton("say_hi", (boolean down) -> {
            if (down) {
                Log.info("MainNarDashTest", "(button say_hi was pressed) hai.");
            }
        });
        NarwhalDashboard.addButton("rezero", (boolean down) -> {
            if (down) {
                Log.info("MainNarDashTest", "Rezero process beginning. Lowering forklift.");
            }
            else {
                Log.info("MainNarDashTest", "Forklift rezeroed.");
            }
        }); 
    }
}
