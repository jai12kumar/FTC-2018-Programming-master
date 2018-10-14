package org.firstinspires.ftc.teamcode.auto.commands

import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix
import org.firstinspires.ftc.robotcore.external.matrices.VectorF
import org.firstinspires.ftc.robotcore.external.navigation.*
import org.firstinspires.ftc.teamcode.auto.AutoBase


class VuforiaCommand(opMode: AutoBase, commandIndex: Int) : AutoCommand(opMode, commandIndex) {

    var lastLocation: OpenGLMatrix? = null

    val cameraMonitorViewId: Int = opMode.hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId", "id", opMode.hardwareMap.appContext.packageName
    )
    val parameters: VuforiaLocalizer.Parameters = VuforiaLocalizer.Parameters(cameraMonitorViewId)

    var template: VuforiaTrackable? = null

    override fun setup() {

        parameters.vuforiaLicenseKey =
                "AR28rTb/////AAAAGdmyxbWoaEUfpgbw+HH9dAR6pd2GE0zLPpsObm9c3iyHvFxLGIrvMEkriYpEMFXybIOF1ng9sKMrJr1He8aXsUQ+7zxItkmGs69z3vTyLgRRD0eUrIJXViYt+tk6IzPYE+4Z9v5hWKteebG3TfzVmT/H/kg6vMLzQblDYNcz4JJZYrCq2axfHBhrDp6ljJQv0esY5DacKVrFLn1H6Jkaxe0vuZFsOveYpTzRdY4v4UuXqEwUxz+NdM/++RZncWkbftEpcLaf1tMFkTZBCOdQ5Tx+HXoT1bpepy1hHF1E6+cwxiUxZAx1ZxbsH5IJ+TfVtk2GjGD1R9CqSqvDE+8fWY12BOZP3PTSdHLaVgCmw/hq"

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK

        val vuforia: VuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters)
        val trackables: VuforiaTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark")

        template = trackables[0]

        opMode.telemetry.addData(">", "Press Play To Start")
        opMode.telemetry.update()
        opMode.waitForStart()

        trackables.activate()
    }

    override fun update() {
        val mark: RelicRecoveryVuMark = RelicRecoveryVuMark.from(template)
        if (mark !== RelicRecoveryVuMark.UNKNOWN) {

            opMode.telemetry.addData("VuMark", "%JoyDrive visible", mark)

            val pose: OpenGLMatrix? = (template?.listener as VuforiaTrackableDefaultListener).pose
            opMode.telemetry.addData("Pose", pose)

            if (pose != null) {
                val translation: VectorF = pose.translation
                val rotation: Orientation = Orientation.getOrientation(
                        pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES
                )

                val tX: Double = translation.get(0).toDouble()
                val tY: Double = translation.get(1).toDouble()
                val tZ: Double = translation.get(2).toDouble()

                val rX: Double = rotation.firstAngle.toDouble()
                val rY: Double = rotation.secondAngle.toDouble()
                val rZ: Double = rotation.thirdAngle.toDouble()
            }
        } else {
            opMode.telemetry.addData("VuMark", "not visible")
        }

        opMode.telemetry.update()
    }

    override fun complete(): Boolean {
        return true
    }

    override fun finish() {
        // No-op
    }
}
