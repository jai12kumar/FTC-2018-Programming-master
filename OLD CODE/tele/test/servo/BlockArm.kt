package org.firstinspires.ftc.teamcode.tele.test.servo

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo

@Disabled
@TeleOp(name = "BlockArm", group = "Test")
class BlockArm : LinearOpMode() {

    private var blockArm: Servo? = null

    override fun runOpMode() {

        blockArm = hardwareMap.servo.get("block_arm")

        waitForStart()

        blockArm?.position = 0.5

        while (opModeIsActive()) {
            if (gamepad1.b) {
                blockArm?.position = 1.0
            } else {
                blockArm?.position = 0.5
            }
        }

    }



}