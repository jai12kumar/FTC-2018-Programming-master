package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotBase;

/**
 * Created by user on 12/9/17.
 */

@Disabled
@TeleOp(name = "NewRobot Test")
public class NewRobotTest extends RobotBase {

    @Override
    public void runOpMode() throws InterruptedException {
        setup();

        // Initialize glyph arm
        glyph_arm.setPosition(1.0);

        waitForStart();

        setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //left_in.setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //right_in .setDrivetrainMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flip_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()) {
            // update

            float intake_power = gamepad2.left_stick_y;
            float flip_power = gamepad2.right_stick_y;
            float left_power = gamepad1.left_stick_y;
            float right_power = gamepad1.right_stick_y;
            //float arm_power = gamepad2.right_trigger;
            //float reverse_arm = gamepad2.left_trigger;

            setDrivetrainPower(left_power, right_power);
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

            if (gamepad2.b){
                glyph_arm.setPosition(0);
            }
            else{
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
}

