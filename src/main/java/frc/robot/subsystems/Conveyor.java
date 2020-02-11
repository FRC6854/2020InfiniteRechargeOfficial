package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import viking.controllers.rev.VikingMAX;

public class Conveyor extends SubsystemBase implements Constants, RobotMap {

  VikingMAX lowerMotor;
  VikingMAX upperMotor;

  public Conveyor(int lowerMotorID, boolean lowerInverted, int upperMotorID, boolean upperInverted) {
    lowerMotor = new VikingMAX(lowerMotorID, lowerInverted);
    upperMotor = new VikingMAX(upperMotorID, upperInverted);

    lowerMotor.setPIDF(INTAKE_KP, INTAKE_kI, INTAKE_KD, INTAKE_KF);
    lowerMotor.setSmartMotion(INTAKE_MAX_VELOCITY, INTAKE_ACCELERATION);

    upperMotor.setPIDF(INTAKE_KP, INTAKE_kI, INTAKE_KD, INTAKE_KF);
    upperMotor.setSmartMotion(INTAKE_MAX_VELOCITY, INTAKE_ACCELERATION);
  }

  public void setOutput(double speed) {
    lowerMotor.percentOutput(speed);
    upperMotor.percentOutput(speed); 
  }

  public void setVelocity(double velocity) {
    lowerMotor.smartVelocityControl(velocity);
  }

  public void fullStop() {
    lowerMotor.getSparkMAX().disable();
    upperMotor.getSparkMAX().disable();
  }

  public VikingMAX getLowerMotor() {
    return lowerMotor;
  }

  public VikingMAX getUpperMotor() {
    return upperMotor;
  }

  @Override
  public void periodic() {
  }
}
