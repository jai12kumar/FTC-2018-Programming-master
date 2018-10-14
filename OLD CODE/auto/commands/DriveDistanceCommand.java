package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.auto.AutoBase;

/*
 * Created by Benjamin Ward on 2/18/2018.
 */
public class DriveDistanceCommand extends AutoCommand {

    private double countsPerRev = 1120;    // Andymark NeveRest 40 encoder counts per revolution
    private double gearRatio = 1.0;
    private double wheelCircumference = 4.0 * Math.PI;
    private double countsPerInch = ((countsPerRev / wheelCircumference) / gearRatio);

    private PIDController leftPIDController, rightPIDController;

    private PIDController.Parameters params;

    public DriveDistanceCommand(AutoBase opMode, int index, double inches, double maxPower) {
        super(opMode, index);

        double targetCount = inches * countsPerInch;
        double toleranceCount = .5 * countsPerInch;

        params = new PIDController.Parameters(.0015, .00002, 0, targetCount, toleranceCount, maxPower);

        leftPIDController = new PIDController(opMode, params);
        rightPIDController = new PIDController(opMode, params);
    }

    @Override
    public void setup() {
        opMode.resetEncoders();
    }

    @Override
    public void update() {
        double leftPower = leftPIDController.update(opMode.getLeftDistance());
        double rightPower = rightPIDController.update(opMode.getRightDistance());

        opMode.telemetry.addData("Left Position", opMode.getLeftDistance());
        opMode.telemetry.addData("Left Position", opMode.getRightDistance());
        opMode.telemetry.addData("Left Power", leftPower);
        opMode.telemetry.addData("Right Power", rightPower);

        opMode.setDrivetrainPower(leftPower, rightPower);
    }

    @Override
    public boolean complete() {
        opMode.telemetry.addData("DriveDistance Finished", leftPIDController.complete() && rightPIDController.complete());
        opMode.telemetry.addData("LeftPIDController Finished", leftPIDController.complete());
        opMode.telemetry.addData("RightPIDController Finished", rightPIDController.complete());

        return leftPIDController.complete() && rightPIDController.complete();
    }

    @Override
    public void finish() {
        opMode.setDrivetrainPower(0, 0);
    }
}
