package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import viking.controllers.rev.VikingMAX;

public class Climber extends SubsystemBase implements Constants, RobotMap {
  
  VikingMAX winch;
  VikingMAX lift;
  VikingMAX massShifter;
  
  static Climber instance = null;

  public Climber() {
    lift = new VikingMAX(CAN_LIFT, false);
    winch = new VikingMAX(CAN_WINCH, false);
    massShifter = new VikingMAX(CAN_MASS_SHIFTER, false);

    lift.setPIDF(LIFT_kP, LIFT_kI, LIFT_kD, LIFT_kF);
    lift.setSmartMotion(LIFT_MAX_VELOCITY, LIFT_ACCELERATION);

    winch.setPIDF(WINCH_kP, WINCH_kI, WINCH_kD, WINCH_kF);
    winch.setSmartMotion(WINCH_MAX_VELOCITY, WINCH_ACCELERATION);

    massShifter.setPIDF(SHIFT_kP, SHIFT_kI, SHIFT_kD, SHIFT_kF);
    massShifter.setSmartMotion(SHIFT_MAX_VELOCITY, SHIFT_ACCELERATION);
  }

  public void setLiftAngle(double angle) {
    lift.positionControl(angleToTicks(angle));
  }

  public void setLiftOutput(double output) {
    lift.percentOutput(output);
  }

  public void setWinchOutput(double output) {
    winch.percentOutput(output);
  }

  public void setShifterOutput(double output) {
    massShifter.percentOutput(output);
  }

  public void fullStop() {
    lift.getSparkMAX().disable();
    winch.getSparkMAX().disable();
    massShifter.getSparkMAX().disable();
  }

  public double getLiftOutput() {
    return lift.getOutput();
  }

  public double getWinchOutput() {
    return winch.getOutput();
  }

  public double getLiftAngle() {
    return ticksToAngle(lift.getPosition());
  }

  public double getShifterOutput() {
    return massShifter.getOutput();
  }

  private double angleToTicks(double angle) {
    return ((LIFT_TICKS_PER_REV * angle) / 360);
  }

  private double ticksToAngle(double ticks) {
    return (360 * ticks) / LIFT_TICKS_PER_REV;
  }

  @Override
  public void periodic() {
  }

  public static Climber getInstance() {
    if(instance == null) {
      instance = new Climber();
      //instance.setDefaultCommand();
    }
		return instance;
  }
}
