package org.firstinspires.ftc.teamcode.tele.drive.tank

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@Disabled
@TeleOp(name = "Lift_Test", group = "Drive")
class LiftTest : LinearOpMode() {

    private var liftOne: DcMotor? = null
    private var liftTwo: DcMotor? = null


    override fun runOpMode() {

        liftOne = hardwareMap.dcMotor.get("lift_one")
        liftTwo = hardwareMap.dcMotor.get("lift_two")


        liftOne?.direction = DcMotorSimple.Direction.REVERSE

        waitForStart()

        while (opModeIsActive()) {
            liftOne?.power = gamepad1.left_stick_y.toDouble()
            liftTwo?.power = gamepad1.left_stick_y.toDouble()

        }
    }

}
