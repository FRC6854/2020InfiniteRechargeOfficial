package frc.robot;

public interface RobotMap {
    /**
     * ROBOT MAP
     * 
     * Constants for physical conections on robot
     * 
     * FORMAT: (data type can be changed as needed)
     * public static final int CONECTOR_LOCATION/SUBSYSTEM = NUMBER
     * 
     * to reference data from RobotMap include
     * 
     * "implements RobotMap" on the class declaration ex. Public class CoolRobot
     * implements RobotMap
     * 
     * DO NOT HARDCODE NUMBERS IN CODE. ALWAYS UPDATE & REFERENCE THIS FILE. 
     * MAGIC NUMBERS SUCK!!!!
     * 
     */

    /**
     * Controllers: Number in drivestation NOTE: Position in ds list can be locked
     * inside drivestation Make sure this is done before each match and check before
     * each match that the order is correct.
     */

    // Driver Controller
    public static final int CONTROLLER_DRIVER = 0;

    /**
     * Motor Controllers: Specify the connection type (CAN, PWM) and
     * location/subsystem in the constant
     */
    public static final int CAN_LEFT_FRONT = 11;
    public static final int CAN_RIGHT_FRONT = 13;
    public static final int CAN_LEFT_BACK = 12;
    public static final int CAN_RIGHT_BACK = 10;

    public static final int CAN_SPARK_MAX = 1;
}