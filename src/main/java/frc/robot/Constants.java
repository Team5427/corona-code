/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    //****** NUMBERS *******/
    public static final double Z_ROT_DAMPENING = 0.75;
    public static final double TURN_TOLERANCE =  2;
    public static final double DRIVE_TOLERANCE = 0.5;
    public static final double DRIVETRAIN_WHEEL_DIAMETER = 4; //in inches
    public static final double DISTANCE_PER_PULSE = (Math.PI * DRIVETRAIN_WHEEL_DIAMETER/256)/12.63;
    public static final double SHOOTER_DIST_PER_PULSE = 1;
    public static final double INTAKE_PROXIMITY_DIFFERENCE = 2.0;
    public static final double PROXIMITY_COVERED = 2.7;
    public static final double PROXIMITY_UNCOVERED = 3.7;
    public static final int FIRST_PROXIMITY_UNCOVERED = 0;
    public static final int PROXIMITY_TOLERANCE = 0;
    public static final double TILT_UP_TIMEOUT = 0.5;
    public static final double SLALOM_SPEED = 0.7;
    public static final double ELEVATOR_UPPER_LIMIT = 15;
    public static final double TIME_BETWEEN_CELLS = 1;
    public static final double TIME_AFTER_CELLS = 5;

    //Joystick buttons
    public static final int INTAKE_BUTTON = 2;
    public static final int TRANSPORT_BUTTON = 7;
    public static final int PULLEY_BUTTON = 8;
    public static final int SHOOTER_BUTTON = 11;
    public static final int TILT_BUTTON_UP = 6;
    public static final int TILT_BUTTON_DOWN = 4;
    public static final int SHOOTER_TELEOP = 1;
    public static final int ELEVATOR_UP_BUTTON = 5;
    public static final int ELEVATOR_DOWN_BUTTON = 3;
    public static final int TILT_AUTO_BUTTON = 10;
    public static final int SHOOT_ALL_BUTTON = 12;

    //Speeds
    public static final double INTAKE_TELEOP_SPEED = 0.7;
    public static final double TRANSPORT_TELEOP_SPEED = -0.7;
    public static final double PULLEY_TELEOP_SPEED = 1.0;
    public static final double TILT_SPEED = 1.0;
    public static final double SHOOTER_TELEOP_SPEED = 1.0;
    public static final double AUTONOMOUS_SPEED = 0.4;
    public static final double ELEVATOR_SPEED = 0.6;

    /*****************Motor ports*****************/
    public static final int LEFT_TOP_MOTOR = 14; 
	public static final int LEFT_BOTTOM_MOTOR = 15;
	public static final int RIGHT_TOP_MOTOR = 0;
    public static final int RIGHT_BOTTOM_MOTOR = 1;
    public static final int INTAKE_MOTOR = 10; 
    public static final int TRANSPORT_MOTOR = 12;
    public static final int PULLEY_MOTOR = 11;
    public static final int TILT_MOTOR = 3;
    public static final int SHOOTER_MOTOR_TOP = 9;
    public static final int SHOOTER_MOTOR_BOTTOM = 6;
    public static final int ELEVATOR_LEFT_MOTOR = 13;
    public static final int ELEVATOR_RIGHT_MOTOR = 2;
    public static final int CLIMB_MANIPULATOR = 4;

    /*******************Sensors*******************/
    public static final int TRANSPORT_PROXIMITY_ONE_SENSOR_PORT = 0;
    public static final int TRANSPORT_PROXIMITY_TWO_SENSOR_PORT = 2;
    public static final int PULLEY_PROXIMITY_SENSOR_PORT = 1;
    public static final int ULTRASONIC_PING = 22;
    public static final int ULTRASONIC_ECHO = 23;

    public static final int ELEVATOR_LEFT_PORT_1 = 0;
    public static final int ELEVATOR_LEFT_PORT_2 = 1;
    public static final int ELEVATOR_RIGHT_PORT_1 = 6;
    public static final int ELEVATOR_RIGHT_PORT_2 = 7;
    public static final int ELEVATOR_LIMIT_RIGHT = 9;
    public static final int ELEVATOR_LIMIT_LEFT = 8;
    public static final int TILT_SWITCH_PORT = 12;
    public static final int SHOOTER_TOP_ENC_PORT_1 = 4;
    public static final int SHOOTER_TOP_ENC_PORT_2 = 5;
    public static final int SHOOTER_BOTTOM_ENC_PORT_1 = 2;
    public static final int SHOOTER_BOTTOM_ENC_PORT_2 = 3;
}
