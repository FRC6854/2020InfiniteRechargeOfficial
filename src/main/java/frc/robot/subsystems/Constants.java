package frc.robot.subsystems;

public interface Constants {
	
	/**
	 * --------------------
	 * 	DRIVETRAIN CONSTANTS
	 * --------------------
	 */
    public final double DRIVETRAIN_kP = 1.0;
    public final double DRIVETRAIN_kI = 0.0; 
    public final double DRIVETRAIN_kD = 0.0;
	public final double DRIVETRAIN_kF = 0.5;
	
	public final double DRIVETRAIN_kMetersPerRevolution = 2 * Math.PI * 0.0762;

	public final double DRIVETRAIN_kDeadband = 0.02; 

	/**
	 * --------------------
	 * 	GYRO CONSTANTS
	 * --------------------
	 */
	public final double GYRO_kP = 0.025;
	public final double GYRO_kI = 0.0;
	public final double GYRO_kD = 0.2;

	/**
	 * -------------------------
	 * 	VISION AIMING CONSTANTS
	 * -------------------------
	 */
	public final double AIM_kP = 0.05;
	public final double AIM_kI = 0.0;
	public final double AIM_kD = 0.0;

	public final double AIM_DISTANCE_kP = 0.05;
	public final double AIM_DISTANCE_kI = 0.0;
	public final double AIM_DISTANCE_kD = 0.0;

	public final double AIM_kMinCommand = 0.05;
	public final double AIM_kMaxCommand = 0.75;

	public final double AIM_kThreshold = 0.5;

	/**
	 * --------------------
	 *  CONVEYOR CONSTANTS
	 * --------------------
	 */
	public final double CONVEYOR_kP = 0;
	public final double CONVEYOR_kI = 0;
	public final double CONVEYOR_kD = 0;
	public final double CONVEYOR_kF = 0;
	public final double CONVEYOR_ACCELERATION = 0;
	public final double CONVEYOR_MAX_VELOCITY = 0;

	/**
	 * --------------------
	 *   SHOOTER CONSTANTS
	 * --------------------
	 */

	
	public final double SHOOTER_kP = 0.001;
	public final double SHOOTER_kI = 0;
	public final double SHOOTER_kD = 0.005;
	public final double SHOOTER_kF = 0;
	public final double SHOOTER_ACCELERATION = 0;
	public final double SHOOTER_MAX_VELOCITY = 0;

	/**
	 * --------------------
	 *   CLIMBER CONSTANTS
	 * --------------------
	 */
	public final double CLIMBER_kP = 0;
	public final double CLIMBER_KI = 0;
	public final double CLIMBER_kD = 0;
	public final double CLIMBER_kF = 0;
	public final double CLIMBER_ACCELERATION = 0;
	public final double CLIMBER_MAX_VELOCITY = 0;
}
