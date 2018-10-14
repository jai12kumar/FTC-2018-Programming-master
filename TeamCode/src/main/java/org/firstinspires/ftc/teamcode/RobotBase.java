package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by wardp on 1/13/2018.
 */

public abstract class RobotBase extends LinearOpMode {
    public ColorSensor colorSensor;
    //protected DcMotor upwards;
    //protected DcMotor forwards;

    protected DcMotor left_f, right_r, left_r, right_f;

    //public Servo latch, front, back;

    protected void setup() {

        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        /* Drivetrain setup block */
        left_f = hardwareMap.dcMotor.get("left_front");
        left_r = hardwareMap.dcMotor.get("left_rear");
        right_f = hardwareMap.dcMotor.get("right_front");
        right_r = hardwareMap.dcMotor.get("right_rear");


        left_f.setDirection(DcMotor.Direction.FORWARD);
        left_r.setDirection(DcMotor.Direction.FORWARD);
        right_f.setDirection(DcMotor.Direction.REVERSE);
        right_r.setDirection(DcMotor.Direction.REVERSE);

        left_f.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_r.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_f.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_r.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left_f.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left_r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_f.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        /* Intake setup block */
        //upwards = hardwareMap.dcMotor.get("screw");
        //forwards = hardwareMap.dcMotor.get("extend");


        //upwards.setDirection(DcMotor.Direction.REVERSE);

        //left_in.setDrivetrainMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //right_in.setDrivetrainMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        /* Servo setup block
        front = hardwareMap.servo.get("front");
        back = hardwareMap.servo.get("back");
        latch = hardwareMap.servo.get("latch");
//        jewel_servo = hardwareMap.servo.get("jewel_servo");
        */

        /* Sensor setup block */
        colorSensor = hardwareMap.colorSensor.get("color_sensor");



    }

    /**
     * Replaces opModeIsActive, with no idle() call.
     * @return Whether the opmode is active and should continue looping
     */

    public boolean shouldContinueRunning() {
        return (!this.isStopRequested() && this.isStarted());
    }

    protected void setPosition(int position) {
        left_f.setTargetPosition(position);
        left_r.setTargetPosition(position);
        right_f.setTargetPosition(position);
        right_r.setTargetPosition(position);
    }

    public void setDrivetrainMode(DcMotor.RunMode mode) {
        left_f.setMode(mode);
        left_r.setMode(mode);
        right_f.setMode(mode);
        right_r.setMode(mode);
    }

    public void setDrivetrainPower(double left, double right) {
        setDrivetrainPower((float)left, (float)right);
    }

    public void setDrivetrainPower(float left, float right) {
        left_f.setPower(left);
        left_r.setPower(left);
        right_f.setPower(right);
        right_r.setPower(right);
    }


}
