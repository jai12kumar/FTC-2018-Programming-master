package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotBase;

/**
 * Created by user on 12/9/17.
 */

@TeleOp(name = "StateTele")
@Disabled
public class StateTele extends RobotBase {
    @Override
    public void runOpMode() throws InterruptedException {
        float tolerance = 0.01F;

        setup();

        // Initialize glyph arm
        glyph_arm.setPosition(1.0);

        waitForStart();

        setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //left_in.setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //right_in.setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flip_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()) {
            // update

            float intake_power = gamepad2.left_stick_y;
            float flip_power = gamepad2.right_stick_y;
            //float arm_power = gamepad2.right_trigger;
            //float reverse_arm = gamepad2.left_trigger;

            //strafe

            float strafe = gamepad1.right_stick_x;
            float speed = gamepad1.left_stick_y;
            float turn = gamepad1.left_stick_x;

            double frontRight = 0;
            double rearRight = 0;
            double frontLeft = 0;
            double rearLeft = 0;

            frontLeft = speed + turn - strafe;
            rearLeft = speed + turn + strafe;
            frontRight = speed - turn + strafe;
            rearRight = speed - turn - strafe;
            float normalize = Math.abs(speed)+Math.abs(turn)+Math.abs(strafe);
            if (normalize > 1) {
                frontLeft /= normalize;
                rearLeft /= normalize;
                frontRight /= normalize;
                rearRight /= normalize;
            }

            /* code that separtes strafe from now - leaving for now in case it's needed later
            float strafeLeft = gamepad1.left_trigger;
            float strafeRight = gamepad1.right_trigger;

            // Check if we should do tank or strafe

            //if (Math.abs(strafeLeft) < tolerance && Math.abs(strafeRight) < tolerance) {
                // If neither trigger is pressed beyond tolerance, do tank
//                frontRight = gamepad1.right_stick_y;
//                rearRight = gamepad1.right_stick_y;
//                frontLeft = gamepad1.left_stick_y;
//                rearLeft = gamepad1.left_stick_y;
                float turn = gamepad1.left_stick_x;
                float speed = gamepad1.left_stick_y;
                frontLeft = speed + turn;
                rearLeft = speed + turn;
                frontRight = speed - turn;
                rearRight = speed - turn;
                float normalize = Math.abs(speed)+Math.abs(turn);
                if (normalize > 1) {
                    frontLeft /= normalize;
                    rearLeft /= normalize;
                    frontRight /= normalize;
                    rearRight /= normalize;
                }
            }
            else {
                // If either trigger is pressed beyond tolerance, use the one that's pressed more
//                frontLeft = -2 * Math.cos(gamepad1.left_stick_y) * Math.sin(gamepad1.left_stick_x);
//                frontRight = -frontLeft;
//                rearLeft = -frontLeft;
//                rearRight = frontLeft;
            }
            */

            telemetry.addData("leftfrontpower: ", frontLeft);
            telemetry.addData("leftrearpower: ", rearLeft);
            telemetry.addData("rightfrontpower: ", frontRight);
            telemetry.addData("rightrearpower: ", rearRight);

            drive(frontLeft, frontRight, rearLeft, rearRight);

            leftIntake(intake_power);
            rightIntake(intake_power);
            flip(flip_power);
            jewel_arm.setPosition(0);
            //relic_arm.setDrivetrainPower(arm_power/5);
            //relic_arm.setDrivetrainPower(reverse_arm/5);

            /*if (gamepad2.a){
                relic_elbow.setPosition(1);
            }
            else{
                relic_elbow.setPosition(0);
            }

            if (gamepad2.left_bumper){
                hand_left.setPosition(0);
            }
            else{
                hand_left.setPosition(1);
            }

            if (gamepad2.right_bumper){
                hand_right.setPosition(0);
            }
            else{
                hand_right.setPosition(1);
            }*/

            if (gamepad2.b) {
                glyph_arm.setPosition(0);
            } else {
                glyph_arm.setPosition(.5);
            }

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


    private void drive(double lf, double rf, double lr, double rr1) {

        left_f.setPower(lf);
        right_f.setPower(rf);
        left_r.setPower(lr);
        right_r.setPower(rr1);

    }


}

