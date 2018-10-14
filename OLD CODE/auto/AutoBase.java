package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ExpansionHubIMU;
import org.firstinspires.ftc.teamcode.RobotBase;
import org.firstinspires.ftc.teamcode.auto.commands.AutoCommand;
import org.firstinspires.ftc.teamcode.auto.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.auto.commands.NullCommand;
import org.firstinspires.ftc.teamcode.auto.commands.TurnAngleCommand;

/**
 * Created by wardp on 2/18/2018.
 */

public abstract class AutoBase extends RobotBase {
    private ExpansionHubIMU imu;

    protected void setup() {
        super.setup();

        imu = new ExpansionHubIMU(hardwareMap);
        imu.initialize();
    }

    private AutoCommand currentCommand = new NullCommand(this, 0);

    @Override
    public void runOpMode() throws InterruptedException {
        setup();

        double lastTime = System.currentTimeMillis();

        waitForStart();

        while (shouldContinueRunning()) {
            double currentTime = System.currentTimeMillis();
            update();

            if (currentCommand.complete()) {
                currentCommand = getNextCommand();
            }

            currentCommand.update();

            telemetry.addData("Current Index", "Getting next command. Current index: " + currentCommand.getIndex());

            telemetry.addData("Loop Delta (ms)", currentTime - lastTime);
            telemetry.update();
            lastTime = currentTime;
        }
    }

    private AutoCommand getNextCommand() {
        currentCommand.finish();

        AutoCommand command = getCommand(currentCommand.getIndex());
        command.setup();

        return command;
    }

    protected abstract AutoCommand getCommand(int commandIndex);

    public void resetEncoders() {
        setDrivetrainMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public double getLeftDistance() {
        return (left_f.getCurrentPosition() + left_r.getCurrentPosition()) / 2.0;
    }

    public double getRightDistance() {
        return (right_f.getCurrentPosition() + right_r.getCurrentPosition()) / 2.0;
    }

    public void zeroGyro() {
        imu.zero();
    }

    public double getAngle() {
        return imu.getHeading();
    }

    protected void update() {
        imu.update(telemetry);


    }
}
