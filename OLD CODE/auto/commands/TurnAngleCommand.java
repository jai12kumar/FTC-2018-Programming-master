package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.auto.AutoBase;

/*
 * Created by Benjamin Ward on 2/18/2018.
 */
public class TurnAngleCommand extends AutoCommand {

    private PIDController rotationPIDController;

    private PIDController.Parameters params;

    public TurnAngleCommand (AutoBase opMode, int index, double angleTarget, double maxPower) {
        super(opMode, index);

        params = new PIDController.Parameters(.002, 0.00001, 0, angleTarget, 2.0, maxPower);

        rotationPIDController = new PIDController(opMode, params);
    }

    @Override
    public void setup() {
        opMode.zeroGyro();
    }

    @Override
    public void update() {
        double power = rotationPIDController.update(opMode.getAngle());

        opMode.telemetry.addData("Rotation Power", power);

        opMode.setDrivetrainPower(power, -power);
    }

    @Override
    public boolean complete() {
        opMode.telemetry.addData("TurnAngle Finished", rotationPIDController.complete() );

        return rotationPIDController.complete();
    }

    @Override
    public void finish() {
        opMode.setDrivetrainPower(0, 0);
    }
}
