package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.auto.AutoBase;

/*
 * Created by Benjamin Ward on 2/18/2018.
 */

/**
 * Simpler turning implementation - gets close enough by setting a
 * simple power (no PID controller) and undershooting.
 */
public class TurnAngleSimpleCommand extends AutoCommand {

    double angleTarget;
    double power;


    public TurnAngleSimpleCommand(AutoBase opMode, int index, double angleTarget, double power) {
        super(opMode, index);

        this.angleTarget = angleTarget;
        this.power = power;
    }

    @Override
    public void setup() {
        opMode.zeroGyro();
    }

    @Override
    public void update() {
        opMode.telemetry.addData("Rotation Power", power);

        opMode.setDrivetrainPower(power, -power);
    }

    @Override
    public boolean complete() {
        boolean finished = opMode.getAngle() < (angleTarget - 5) && opMode.getAngle() > 0;
        opMode.telemetry.addData("TurnAngle Finished", finished);

        return finished;
    }

    @Override
    public void finish() {
        opMode.setDrivetrainPower(0, 0);
    }
}
