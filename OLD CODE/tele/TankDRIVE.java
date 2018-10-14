//NEVER FORGET SEMICOLONS IN JAVA

package org.firstinspires.ftc.teamcode.tele; //tells you what folder its under

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; //imports LinearOpMode commands
import com.qualcomm.robotcore.hardware.DcMotor; //importd DcMotor commands

@Disabled
public class TankDRIVE extends LinearOpMode {

    private DcMotor left_f, left_r, right_f, right_r; //initialize each motor

    @Override
    public void runOpMode() throws InterruptedException {

        //declare names of each motor
        left_f = hardwareMap.dcMotor.get("left_front");
        left_r = hardwareMap.dcMotor.get("left_rear");
        right_f = hardwareMap.dcMotor.get("right_front");
        right_r = hardwareMap.dcMotor.get("right_rear");

        //set directions of each motor
        left_f.setDirection(DcMotor.Direction.FORWARD);
        left_r.setDirection(DcMotor.Direction.FORWARD);
        right_f.setDirection(DcMotor.Direction.REVERSE);
        right_r.setDirection(DcMotor.Direction.REVERSE);
        //set mode of each motor to stop and reset the encoder
        left_f.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_r.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_f.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_r.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set joystick values to each motor
        double frontLeft = gamepad1.left_stick_y;
        double rearLeft = gamepad1.left_stick_y;
        double frontRight = gamepad1.right_stick_y;
        double rearRight = gamepad1.right_stick_y;

        //call the setDrivetrainPower method for the four values above
        setPower(frontLeft, rearLeft, frontRight, rearRight);

    }

    //this method sets the input powers to each motor
    private void setPower(double lf, double rl, double fr, double rr) {
        left_f.setPower(lf);
        left_r.setPower(rl);
        right_f.setPower(fr);
        right_r.setPower(rr);
    }
}
