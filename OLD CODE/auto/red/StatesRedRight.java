package org.firstinspires.ftc.teamcode.auto.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.auto.AutoBase;
import org.firstinspires.ftc.teamcode.auto.commands.AutoCommand;
import org.firstinspires.ftc.teamcode.auto.commands.DriveDistanceCommand;

/**
 * Created by wardp on 2/23/2018.
 */

@Autonomous
public class StatesRedRight extends AutoBase {

    double startTime = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        setup();

        waitForStart();

        // Lower jewel arm between the jewels
        jewel_arm.setPosition(0.73);

        // Wait one and a half second for arm to drop and color sensor to get settled
        sleep(1500);

        // Color is read on the left side of the jewel arm, aka backwards
        int redReading = colorSensor.red(), blueReading = colorSensor.blue();

        telemetry.addData("Jewel Arm: ", jewel_arm.getPosition());
        telemetry.addData("Red: ", redReading);
        telemetry.addData("Blue: ", blueReading);
        telemetry.update();

        double driveDistance;

        if(blueReading > redReading) {
            // We're red, so go backwards to knock off the blue jewel (blue is backwards).
            driveDistance = 3;
        }
        else {
            // We're red, so go forwards to knock off the blue jewel (blue is forwards).
            driveDistance = -3;
        }

        startTime = System.currentTimeMillis();

        // Move for one second in correct direction
        while((System.currentTimeMillis() - startTime) < 500) {
            setDrivetrainPower(Math.copySign(.3, driveDistance), Math.copySign(.3, driveDistance));
        }

        setDrivetrainPower(0,0);

        // Lift up the jewel arm
        jewel_arm.setPosition(-.73);
        sleep(2000);

        // If we drove in the negative direction, add a positive second
        double duration = 2000 +  Math.copySign(500, -driveDistance);

        startTime = System.currentTimeMillis();

        while((System.currentTimeMillis() - startTime) < duration) {
            setDrivetrainPower(-.3, -.3);
        }

        setDrivetrainPower(0,0);

//
//        DriveDistanceCommand drive1 = new DriveDistanceCommand(this, 0, driveDistance, .25);
//        drive1.setup();
//        startTime = System.currentTimeMillis();
//
//        while(!drive1.complete() && (System.currentTimeMillis() - startTime) < 2000) {
//            drive1.update();
//        }
//        drive1.finish();


        // Drive to the center of the middle cryptobox
        DriveDistanceCommand drive2 = new DriveDistanceCommand(this, 0, 36 - driveDistance, .5);
        drive2.setup();
        startTime = System.currentTimeMillis();

        while(!drive2.complete() && (System.currentTimeMillis() - startTime) < 10000) {
            drive2.update();
        }
        drive2.finish();

        // Turn 90 degrees clockwise

        // Lift up glyph
    }


    // Do nothing because we're overriding runOpMode
    @Override
    protected AutoCommand getCommand(int commandIndex) {
        return null;
    }
}
