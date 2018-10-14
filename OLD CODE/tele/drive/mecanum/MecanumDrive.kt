package org.firstinspires.ftc.teamcode.tele.drive.mecanum

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.robotcore.external.navigation.*
import java.util.*


@Disabled
@TeleOp(name = "MecanumDrive", group = "Drive")
class MecanumDrive : LinearOpMode() {

    private var leftFront: DcMotor? = null
    private var leftRear: DcMotor? = null
    private var rightFront: DcMotor? = null
    private var rightRear: DcMotor? = null

    override fun runOpMode() {

        leftFront = hardwareMap.dcMotor.get("left_front")
        leftRear = hardwareMap.dcMotor.get("left_rear")
        rightFront = hardwareMap.dcMotor.get("right_front")
        rightRear = hardwareMap.dcMotor.get("right_rear")

        val imu = hardwareMap.get(BNO055IMU::class.java, "imu")

        val parameters = BNO055IMU.Parameters()
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC

        imu.initialize(parameters)

        waitForStart()

        while (opModeIsActive()) {

            val angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES)

            val x = gamepad1.left_stick_x.toDouble()
            val y = gamepad1.left_stick_y.toDouble()
            val c = gamepad1.right_stick_x.toDouble()

            val heading = angles.firstAngle.toDouble()

            telemetry.addData("X", x)
            telemetry.addData("Y", y)
            telemetry.addData("C", c)

            telemetry.addData("HEADING", heading)

            telemetry.update()

            orientation(x, y, c, heading)
        }

    }

    private fun move(y: Double, x: Double, c: Double) {

        var leftFrontPower = y + x + c
        var rightFrontPower = y - x - c
        var leftRearPower = y - x + c
        var rightRearPower = y + x - c

        val powers = doubleArrayOf(rightFrontPower, leftFrontPower, leftRearPower, rightRearPower)

        Arrays.sort(powers)

        if (powers[3] > 1) {
            leftFrontPower /= powers[3]
            rightFrontPower /= powers[3]
            leftRearPower /= powers[3]
            rightRearPower /= powers[3]
        }

        leftFront?.power = leftFrontPower
        leftRear?.power = leftRearPower
        rightFront?.power = rightFrontPower
        rightRear?.power = rightRearPower

    }

    private fun orientation(x: Double, y: Double, c: Double, heading: Double) {

        val cosA = Math.cos(Math.toRadians(heading))
        val sinA = Math.sin(Math.toRadians(heading))

        val xOut = x * cosA - y * sinA
        val yOut = x * sinA + y * cosA

        move(yOut, xOut, c)
    }

}
