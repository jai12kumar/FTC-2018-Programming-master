package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.auto.AutoBase;

/**
 * Created by wardp on 2/18/2018.
 */

public class ParallelCommand extends AutoCommand {
    private AutoCommand command1, command2;

    public ParallelCommand(AutoBase opMode, int index, AutoCommand command1, AutoCommand command2) {
        super(opMode, index);
        this.command1 = command1;
        this.command2 = command2;
    }

    @Override
    public void setup() {
        command1.setup();
        command2.setup();
    }

    @Override
    public void update() {
        command1.update();
        command2.update();
    }

    @Override
    public boolean complete() {
        return command1.complete() && command2.complete();
    }


    @Override
    public void finish() {
        command1.finish();
        command2.finish();
    }
}
