package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotBase;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Strafe Test")
@Disabled
public class StrafeTest extends RobotBase {
    @Override

    public void runOpMode() throws InterruptedException {

        setup();

        waitForStart();

        left_f.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_f.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        while (opModeIsActive()) {

            //strafe
            double leftFront_Power = -Math.cos(gamepad1.left_stick_y) * Math.sin(gamepad1.left_stick_x);
            double rightFront_Power = -leftFront_Power;
            double leftRear_Power = -leftFront_Power;
            double rightRear_Power = leftFront_Power;

            //tank
            double frontRight = gamepad1.right_stick_y;
            double rearRight = gamepad1.right_stick_y;
            double frontLeft = gamepad1.left_stick_y;
            double rearLeft = gamepad1.left_stick_y;

            telemetry.addData("leftfrontpower: ", leftFront_Power);
            telemetry.addData("leftrearpower: ", leftRear_Power);
            telemetry.addData("rightfrontpower: ", rightFront_Power);
            telemetry.addData("rightrearpower: ", rightRear_Power);

            strafe(leftFront_Power, rightFront_Power, leftRear_Power, rightRear_Power);
            drive(frontLeft, frontRight, rearLeft, rearRight);
            telemetry.update();
            idle();

        }
    }


        private void drive(double lf, double rf, double lr, double rr1){

            left_f.setPower(lf);
            right_f.setPower(rf);
            left_r.setPower(lr);
            right_r.setPower(rr1);

        }

    private void strafe(double lf, double rf, double lr, double rr1){

        left_f.setPower(lf);
        right_f.setPower(rf);
        left_r.setPower(lr);
        right_r.setPower(rr1);

    }

    }

