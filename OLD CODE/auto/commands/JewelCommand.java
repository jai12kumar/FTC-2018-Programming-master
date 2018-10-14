package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.auto.AutoBase;

/**
 * Created by wardp on 2/19/2018.
 */

public class JewelCommand extends AutoCommand {

    private Jewel rightJewel = Jewel.UNKNOWN;
    private JewelCommandState state = JewelCommandState.DROP_ARM;
    private double initialTime; // Initial time, milliseconds. Used to calculate which state the command should be in.
    private boolean isRedBase = false;

    public JewelCommand(AutoBase opMode, int commandIndex, boolean isRedBase) {
        super(opMode, commandIndex);
        this.isRedBase = isRedBase;
    }

    @Override
    public void setup() {
        initialTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if (state == JewelCommandState.DROP_ARM) {
            opMode.jewel_arm.setPosition(-0.73);
            // Wait 1.5 seconds for the jewel arm to be dropped.
            if (initialTime - System.currentTimeMillis() > 1500) {
                state = JewelCommandState.READ_COLOR;
                initialTime = System.currentTimeMillis();
            }
        } else if (state == JewelCommandState.READ_COLOR) {

            // Read color
            int redValue = opMode.colorSensor.red();
            int blueValue = opMode.colorSensor.blue();

            if(redValue > blueValue) {
                // Red jewel is on the right
                rightJewel = Jewel.RED;
            }
            else {
                // Blue jewel is on the right
                rightJewel = Jewel.BLUE;
            }

            state = JewelCommandState.MOVE_1;
            initialTime = System.currentTimeMillis();
        } else if (state == JewelCommandState.MOVE_1) {

            // TODO check which values should move which way
            if((rightJewel == Jewel.RED && isRedBase) || (rightJewel == Jewel.BLUE && !isRedBase)) {
                opMode.jewel_servo.setPosition(1);
            }
            else {
                opMode.jewel_servo.setPosition(-1);
            }

            // Wait 500 milliseconds (.5 seconds) for the jewel to be knocked off
            if (initialTime - System.currentTimeMillis() > 500) {
                state = JewelCommandState.RAISE_ARM;
                initialTime = System.currentTimeMillis();
            }
        } else if (state == JewelCommandState.RAISE_ARM) {

            opMode.jewel_arm.setPosition(-0.73);

            // Wait 1.5 seconds for the jewel arm to be raised
            if (initialTime - System.currentTimeMillis() > 1500) {
                state = JewelCommandState.COMPLETE;
            }
        }
    }

    @Override
    public boolean complete() {
        return state == JewelCommandState.COMPLETE;
    }

    @Override
    public void finish() {
        // No-op
    }

    enum JewelCommandState {
        DROP_ARM, // Drop the jewel arm between the jewels
        READ_COLOR, // Read the jewel color with the color sensor
        MOVE_1, // Move a small amount to knock the proper jewel off (but stay on the balancing stone)
        RAISE_ARM, // Raise jewel arm back into robot
        MOVE_2, // Move back to initial position
        COMPLETE
    }

    enum Jewel {
        RED,
        BLUE,
        UNKNOWN
    }
}
