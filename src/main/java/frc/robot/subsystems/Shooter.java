package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import viking.controllers.rev.VikingMAX;

public class Shooter extends SubsystemBase implements Constants, RobotMap {

  private static Shooter instance = null;

  private VikingMAX topShooter = null;
  private VikingMAX bottomShooter = null;

  private Shooter() {
    topShooter = new VikingMAX(CAN_TOP_SHOOTER, false);
    bottomShooter = new VikingMAX(CAN_BOTTOM_SHOOTER, true);

    topShooter.setPIDF(CONVEYOR_kP, CONVEYOR_kI, CONVEYOR_kD, CONVEYOR_kF);
    topShooter.setSmartMotion(CONVEYOR_MAX_VELOCITY, CONVEYOR_ACCELERATION);

    bottomShooter.setPIDF(CONVEYOR_kP, CONVEYOR_kI, CONVEYOR_kD, CONVEYOR_kF);
    bottomShooter.setSmartMotion(CONVEYOR_MAX_VELOCITY, CONVEYOR_ACCELERATION);
  }

  public void setOutputTop(double speed) {
    topShooter.percentOutput(speed);
  }

  public void setOutputBottom(double speed) {
    bottomShooter.percentOutput(speed);
  }

  public void setVelocityTop(double velocity) {
    topShooter.velocityControl(velocity);
  }

  public void setVelocityBottom(double velocity) {
    bottomShooter.velocityControl(velocity);
  }

  public double getVelocityTop() {
    return topShooter.getVelocity();
  }

  public double getVelocityBottom() {
    return bottomShooter.getVelocity();
  }

  public double getOutputCurrentTop() {
    return topShooter.getOutput();
  }

  public double getOutputCurrentBottom() {
    return bottomShooter.getOutput();
  }

  public void fullStop() {
    fullStopTop();
    fullStopBottom();
  }

  public void fullStopTop() {
    topShooter.getSparkMAX().disable();
  }

  public void fullStopBottom() {
    bottomShooter.getSparkMAX().disable();
  }

  public VikingMAX getTopMotor() {
    return topShooter;
  }

  public VikingMAX getBottomMotor() {
    return bottomShooter;
  }

  public static Shooter getInstance() {
    if (instance == null) {
      instance = new Shooter();
    }
    return instance;
  }
}
