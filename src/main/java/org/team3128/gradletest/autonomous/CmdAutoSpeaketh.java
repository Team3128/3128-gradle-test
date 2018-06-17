package org.team3128.gradletest.autonomous;

import org.team3128.common.autonomous.primitives.CmdLog;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CmdAutoSpeaketh extends CommandGroup {
    public CmdAutoSpeaketh(String text) {
        addSequential(new CmdLog(text));
    }
}