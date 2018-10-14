package org.firstinspires.ftc.teamcode.tele.test.servo

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.DcMotorSimple

@Disabled
@Autonomous(name = "ServoRamp", group = "Sensor")
class ServoRamp : LinearOpMode() {
    private var servo: Servo? = null
    override fun runOpMode() {
        servo = hardwareMap.servo.get("servo")
        waitForStart()
        servo?.position = 0.75
        while (opModeIsActive()) {
        }
    }
}
