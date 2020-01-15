package frc.robot.subsystems;

public interface Constants {
	
	/**
	 * --------------------
	 * 	DRIVETRAIN CONSTANTS
	 * --------------------
	 */
	public final int dt_kSlotIdx = 0;

	public final int dt_kPIDLoopIdx = 0;

    public final double dt_kP = 1.0;
    public final double dt_kI = 0.0; 
    public final double dt_kD = 0.0;
	public final double dt_kF = 0.5;
	
	public final double metersPerRevolution = 2 * Math.PI * 0.0762;

    public final double dt_kDeadband = 0.02;

	//Drive Speed Scalar
	public final double jukeSpeedScale = 0.85;
	public final double slowSpeedScale = 0.5;
	public final double speedScale = 1;

	public final double minSpeedScale = 0.015;

	/**
	 * --------------------
	 * 	GYRO CONSTANTS
	 * --------------------
	 */
	public final double gyro_kP = 0.025;
	public final double gyro_kI = 0.0;
	public final double gyro_kD = 0.2;
}
