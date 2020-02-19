package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import viking.controllers.rev.VikingMAX;
import frc.robot.commands.climber.DriveClimber;

public class Climber extends SubsystemBase implements Constants, RobotMap {
  VikingMAX motor;

  private static Climber instance = null;

  public Climber() {
    motor = new VikingMAX(CAN_CLIMBER, false);

    motor.setPIDF(CLIMBER_kP, CLIMBER_KI, CLIMBER_kD, CLIMBER_kF);
    motor.setSmartMotion(CLIMBER_MAX_VELOCITY, CLIMBER_ACCELERATION);
  }

  public void setOutput(double speed) {
    motor.percentOutput(speed);
  }

  public void setVelocity(double velocity) {
    motor.smartVelocityControl(velocity);
  }

  public void setPosition(double position) {
    motor.smartPositionControl(position);
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

  public static Climber getInstance(){
    if (instance == null) {
      instance = new Climber();
      instance.setDefaultCommand(new DriveClimber());
    }
    return instance;
  }
}
