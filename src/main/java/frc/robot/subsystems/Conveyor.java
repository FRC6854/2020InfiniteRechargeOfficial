package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import viking.controllers.rev.VikingMAX;

public class Conveyor extends SubsystemBase implements Constants, RobotMap {

  VikingMAX motor;

  public Conveyor(int motorID, boolean inverted) {
    motor = new VikingMAX(motorID, inverted);

    motor.setPIDF(CONVEYOR_KP, CONVEYOR_kI, CONVEYOR_KD, CONVEYOR_KF);
    motor.setSmartMotion(CONVEYOR_MAX_VELOCITY, CONVEYOR_ACCELERATION);
  }

  public void setOutput(double speed) {
    motor.percentOutput(speed);
  }

  public void setVelocity(double velocity) {
    motor.smartVelocityControl(velocity);
  }

  public void fullStop() {
    motor.getSparkMAX().disable();
  }

  public VikingMAX getMotor() {
    return motor;
  }

  @Override
  public void periodic() {
  }
}
