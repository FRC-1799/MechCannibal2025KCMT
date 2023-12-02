// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // drive Constants
    public static final class drive {

        // F: front
        // B: back

        // left
        public static final int LF = 2;
        public static final int LB = 4;

        // right
        public static final int RF = 3;
        public static final int RB = 5;
        
        public static double rotationSpeedRatio= 0.6;
        public static final double driveSpeedRatio= 1;

        // public static double rampspeed= .25;
    }

    public static final class arm {

        public static final int ArmOnePort = 0;
        public static final int ArmTwoPort = 1;

        public static final double ArmUp = 0.34;
        public static final double ArmDown = -0.2;

        public static final int topSwitch = 1;
        public static final int bottomSwitch = 0;
        
    }

    // Changing Solenoid Values idk 50/50 this'll work


    public static final class auto{
        public static final double fwdSpeed = 0.6;
        public static final double revSpeed = -0.4;

    }

    public static final int MOVEMENT_JOYSTICK = 0;
    public static final int MANIPULATOR_JOYSTICK = 1;

}
