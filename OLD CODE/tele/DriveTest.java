package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by user on 12/9/17.
 */


@TeleOp(name="Drive Test")
public class DriveTest extends LinearOpMode {
    private DcMotor left_f, right_r;

    @Override
    public void runOpMode() throws InterruptedException {
        left_f = hardwareMap.dcMotor.get("left_front");
        right_r = hardwareMap.dcMotor.get("right_rear");

//        left_f.setDirection(DcMotor.Direction.REVERSE);
//        left_r.setDirection(DcMotor.Direction.REVERSE);

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(opModeIsActive())
        {
            float left_power = gamepad1.left_stick_y;
            float right_power = gamepad1.right_stick_y;

            setPower(left_power, right_power);

            telemetry.update();
            idle();
        }
    }

    private void setMode(DcMotor.RunMode mode)
    {
        left_f.setMode(mode);
        right_r.setMode(mode);
    }

    private void setPower(float left, float right) {
        left_f.setPower(left);
        right_r.setPower(right);
    }
}
