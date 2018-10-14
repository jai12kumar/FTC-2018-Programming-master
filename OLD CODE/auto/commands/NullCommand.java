package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.auto.AutoBase;

/**
 * Created by wardp on 2/18/2018.
 */

public class NullCommand extends AutoCommand {
    public NullCommand(AutoBase opMode, int commandIndex) {
        super(opMode, commandIndex);
    }

    @Override
    public void setup() {
        // No-op
    }

    @Override
    public void update() {
        // No-op
    }

    @Override
    public boolean complete() { return true; }

    @Override
    public void finish() {
        // No-op
    }
}
