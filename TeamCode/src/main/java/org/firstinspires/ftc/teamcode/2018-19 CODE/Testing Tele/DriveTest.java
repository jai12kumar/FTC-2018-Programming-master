package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotBase;

/**
 * Created by user on 12/9/17.
 */

@TeleOp(name = "DriveTest")
public class DriveTest extends RobotBase {

    @Override
    public void runOpMode() throws InterruptedException {
        setup();

        // Initialize glyph arm


        waitForStart();

        left_f.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_f.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //left_in.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //right_in.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //flip_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()) {
            // update

            float intake_power = gamepad2.left_stick_y;
            float flip_power = gamepad2.right_stick_y;
            //float arm_power = gamepad2.right_trigger;
            //float reverse_arm = gamepad2.left_trigger;

            //strafe
            double leftFront_Power = -2 * Math.cos(gamepad1.left_stick_y) * Math.sin(gamepad1.left_stick_x);
            double rightFront_Power = -leftFront_Power;
            double leftRear_Power = -leftFront_Power;
            double rightRear_Power = leftFront_Power;

            //drive
            double frontRight = gamepad1.right_stick_y;
            double rearRight = gamepad1.right_stick_y;
            double frontLeft = gamepad1.left_stick_y;
            double rearLeft = gamepad1.left_stick_y;

            telemetry.addData("leftfrontpower: ", leftFront_Power);
            telemetry.addData("leftrearpower: ", leftRear_Power);
            telemetry.addData("rightfrontpower: ", rightFront_Power);
            telemetry.addData("rightrearpower: ", rightRear_Power);

            drive(leftFront_Power, rightFront_Power, leftRear_Power, rightRear_Power);
            drive(frontLeft, frontRight, rearLeft, rearRight);
            telemetry.update();
            idle();
            //relic_arm.setPower(arm_power/5);
            //relic_arm.setPower(reverse_arm/5);

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


            telemetry.update();
            idle();
        }
    }

    /*
    private void leftIntake(float left1) {
        left_in.setPower(left1);
    }

    private void rightIntake(float right1) {
        right_in.setPower(right1);
    }

    private void flip(float flipper) {
        flip_motor.setPower(flipper);
    }
    */

    private void drive(double lf, double rf, double lr, double rr1){

        left_f.setPower(lf);
        right_f.setPower(rf);
        left_r.setPower(lr);
        right_r.setPower(rr1);

    }


}

