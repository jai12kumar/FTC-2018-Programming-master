package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.auto.AutoBase;

/**
 * Created by wardp on 2/18/2018.
 */

public abstract class AutoCommand {
    private int index = -1; // The order in which the command should be performed in an Auto opmode.
    protected AutoBase opMode;

    public AutoCommand(AutoBase opMode, int commandIndex) {
        this.index = commandIndex;
        this.opMode = opMode;
    }

    public int getIndex() {
        return index;
    }

    public abstract void setup();
    public abstract void update();
    public abstract boolean complete();
    public abstract void finish();
}
