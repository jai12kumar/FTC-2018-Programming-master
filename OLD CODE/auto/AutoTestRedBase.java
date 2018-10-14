package org.firstinspires.ftc.teamcode.auto;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.commands.AutoCommand;
import org.firstinspires.ftc.teamcode.auto.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.auto.commands.JewelCommand;
import org.firstinspires.ftc.teamcode.auto.commands.NullCommand;
import org.firstinspires.ftc.teamcode.auto.commands.TurnAngleCommand;

/**
 * Created by wardp on 2/18/2018.
 */

@Autonomous
public class AutoTestRedBase extends AutoBase {
    boolean isRedBase = true;

    @Override
    protected AutoCommand getCommand(int commandIndex) {
        switch (commandIndex) {
            case 0:
                return new JewelCommand(this, 1, isRedBase);
            case 1:
                return new DriveDistanceCommand(this, 2, -12.0, .75);
            case 2:
                return new TurnAngleCommand(this, 3, 90.0, .5);
            default:
                return new NullCommand(this, -1);
        }
    }
}
