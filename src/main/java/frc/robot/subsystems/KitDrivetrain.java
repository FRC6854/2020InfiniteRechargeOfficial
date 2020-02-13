package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import viking.controllers.PIDController;
import viking.controllers.ctre.VikingSPX;
import viking.controllers.ctre.VikingSRX;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.ArcadeDrive;

public class KitDrivetrain extends SubsystemBase implements Constants, RobotMap {
  private static KitDrivetrain instance = null;

  private VikingSRX leftMaster;
  private VikingSPX leftSlave;

  private VikingSRX rightMaster;
  private VikingSPX rightSlave;

  private AHRS gyro;

  private PIDController gyroPID;
  
  private double leftOutput = 0;
  private double rightOutput = 0;

  public KitDrivetrain() {
    leftMaster = new VikingSRX(CAN_LEFT_FRONT, false, true, FeedbackDevice.CTRE_MagEncoder_Relative, dt_kF, dt_kP, dt_kI, dt_kD, 1250, 1250, metersPerRevolution);
    leftSlave = new VikingSPX(CAN_LEFT_BACK, leftMaster, false);
    rightMaster = new VikingSRX(CAN_RIGHT_FRONT, true, true, FeedbackDevice.CTRE_MagEncoder_Relative, dt_kF, dt_kP, dt_kI, dt_kD, 1250, 1250, metersPerRevolution);
    rightSlave = new VikingSPX(CAN_RIGHT_BACK, rightMaster, true);
  
    gyro = new AHRS(Port.kMXP);

    gyroPID = new PIDController(gyro_kP, gyro_kI, gyro_kD);
  }

  public void arcadeDrive(double xSpeed, double zRotation) {
    zRotation = limit(zRotation);
    zRotation = applyDeadband(zRotation, dt_kDeadband);

    xSpeed = limit(xSpeed);
    xSpeed = applyDeadband(xSpeed, dt_kDeadband);

    // Square the inputs (while preserving the sign) to increase fine control
    // while permitting full power.
    zRotation = Math.copySign(zRotation * zRotation, zRotation);
    xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);

    double leftMotorOutput;
    double rightMotorOutput;

    double maxInput = Math.copySign(Math.max(Math.abs(zRotation), Math.abs(xSpeed)), zRotation);

    if (zRotation >= 0.0) {
      // First quadrant, else second quadrant
      if (xSpeed >= 0.0) {
        leftMotorOutput = maxInput;
        rightMotorOutput = zRotation - xSpeed;
      } else {
        leftMotorOutput = zRotation + xSpeed;
        rightMotorOutput = maxInput;
      }
    } else {
      // Third quadrant, else fourth quadrant
      if (xSpeed >= 0.0) {
        leftMotorOutput = zRotation + xSpeed;
        rightMotorOutput = maxInput;
      } else {
        leftMotorOutput = maxInput;
        rightMotorOutput = zRotation - xSpeed;
      }
    }

    driveLeft(limit(leftMotorOutput));
    driveRight(limit(rightMotorOutput) * -1);
  }

  public VikingSRX getLeftMaster() {
    return leftMaster;
  }

  public VikingSRX getRightMaster() {
    return rightMaster;
  }

  public void motionProfile() {
    leftMaster.motionProfileStart();
    rightMaster.motionProfileStart();
  }

  public void resetMotionProfile() {
    leftMaster.resetMotionProfile();
    rightMaster.resetMotionProfile();
  }

  public void driveMeters(double meters) {
    leftMaster.motionMagic(metersToTicks(meters));
    rightMaster.motionMagic(metersToTicks(meters));
  }

  public void driveRotations(double rotations) {
    leftMaster.motionMagic(rotationsToTicks(rotations));
    rightMaster.motionMagic(rotationsToTicks(rotations));
  }

  public void driveLeft(double value) {
    leftOutput = value;
    leftMaster.percentOutput(value);
  }

  public void driveRight(double value) {
    rightOutput = value;
    rightMaster.percentOutput(value);
  }

  public void tankDrive(double left, double right) {
    driveLeft(left);
    driveRight(right);
  }

  public void zeroSensors() {
    leftMaster.zeroSensor();
    rightMaster.zeroSensor();
  }

  public int rotationsToTicks(double rotations) {
    return (int) rotations * 4096;
  }

  public double metersToTicks(double meters) {
    return rotationsToTicks(meters / (2 * Math.PI * 0.0762));
  }

  public int ticksToRotations(int ticks) {
    return ticks / 4096;
  }

  private double limit(double value) {
    if (value > 1.0) {
      return 1.0;
    }
    if (value < -1.0) {
      return -1.0;
    }
    return value;
  }

  private double applyDeadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  public void turn(double setAngle, double speed) {
		turn(setAngle, speed, 1);
	}

	public void turn(double setAngle, double speed, double tolerance) {
    double angle = gyroPID.calcPID(setAngle, getGyroAngle(), tolerance);

		if(Math.abs(setAngle-getGyroAngle()) < tolerance){ 
			driveLeft(0); 
			driveRight(0);
		}
		else{ 
			driveLeft(angle * speed);
			driveRight(-angle * speed); 
		}
	}
  
  public boolean gyroPIDDone() {
    return gyroPID.isDone();
  }

  public int getLeftVelocity() {
    return leftMaster.getVelocity();
  }

  public int getRightVelocity() {
    return rightMaster.getVelocity();
  }

  public double getLeftOutput() {
    return leftOutput;
  }

  public double getRightOutput() {
    return rightOutput;
  }

  public double getAverageOutput() {
    return (leftOutput + rightOutput) / 2;
  }

  public int getLeftTicks() {
    return leftMaster.getTicks();
  }

  public int getRightTicks() {
    return rightMaster.getTicks();
  }

  public double getGyroAngle() {
    return gyro.getAngle();
  }

  public void resetGyro() {
    gyro.reset();
  }

  public void reset() {
    zeroSensors();
    resetGyro();
  }

	public void changeGyroPID(double pGyro, double iGyro, double dGyro) {
		gyroPID.changePIDGains(pGyro, iGyro, dGyro);
  }
  
  public static KitDrivetrain getInstance() {
    if(instance == null) {
      instance = new KitDrivetrain();
      instance.setDefaultCommand(new ArcadeDrive());
    }

		return instance;
  }
}
