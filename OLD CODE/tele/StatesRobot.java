package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotBase;

/**
 * Created by user on 12/9/17.
 */

@TeleOp(name = "States Teleop")
public class StatesRobot extends RobotBase {

    @Override
    public void runOpMode() throws InterruptedException {
        setup();

        // Initialize glyph arm
        glyph_arm.setPosition(1.0);

        waitForStart();

        setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        flip_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (shouldContinueRunning()) {

            float intake_power = gamepad2.left_stick_y;
            float flip_power = gamepad2.right_stick_y;
            float left_power = gamepad1.left_stick_y;
            float right_power = gamepad1.right_stick_y;

            setDrivetrainPower(left_power, right_power);

            intake_left.setPower(intake_power * .8);
            intake_right.setPower(intake_power * -.8);

            flip_motor.setPower(flip_power);
            jewel_arm.setPosition(0);

            if (gamepad2.b){
                glyph_arm.setPosition(0);
            }
            else{
                glyph_arm.setPosition(.5);
            }

            telemetry.update();
        }
    }

    private void leftIntake(float left1) {
        left_in.setPower(left1);
    }

    private void rightIntake(float right1) {
        right_in.setPower(right1);
    }

    private void flip(float flipper) {

    }
}

