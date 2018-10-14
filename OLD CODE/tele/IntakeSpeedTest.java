package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotBase;

/**
 * Created by user on 12/9/17.
 */

@Disabled
@TeleOp
public class IntakeSpeedTest extends RobotBase {

    @Override
    public void runOpMode() throws InterruptedException {
        setup();

        // Initialize glyph arm
        glyph_arm.setPosition(1.0);

        waitForStart();

        setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        flip_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        while (opModeIsActive()) {
            if(gamepad2.a) {

            }

            float intake_left = gamepad2.left_stick_y;
            float intake_right = gamepad2.right_stick_y;

            float flip_power = gamepad2.right_trigger;


            float left_power = gamepad1.left_stick_y;
            float right_power = gamepad1.right_stick_y;
            //float arm_power = gamepad2.right_trigger;
            //float reverse_arm = gamepad2.left_trigger;

            setDrivetrainPower(left_power, right_power);

            left_in.setPower(intake_left);
            right_in.setPower(intake_right);

//            flip(flip_power);
            jewel_arm.setPosition(0);

            telemetry.update();
            idle();
        }
    }

    private void leftIntake(float left1) {
        left_in.setPower(left1);
    }

    private void rightIntake(float right1) {
        right_in.setPower(right1);
    }

    private void flip(float flipper) {
        flip_motor.setPower(flipper);
    }
}

