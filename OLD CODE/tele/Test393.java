package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;


@Disabled
@TeleOp
public class Test393 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        CRServo servo1 = hardwareMap.crservo.get("servo1");
        CRServo servo2 = hardwareMap.crservo.get("servo2");

        waitForStart();

        while(opModeIsActive()){

            double power = gamepad1.right_stick_y * .8;

            telemetry.addData("Power", power);
            servo1.setPower(power);
            servo2.setPower(-power);

        }
    }
}
