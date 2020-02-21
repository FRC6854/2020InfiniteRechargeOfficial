package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import viking.controllers.rev.VikingMAX;

public class Conveyor extends SubsystemBase implements Constants, RobotMap {

  private static Conveyor instance = null;

  VikingMAX intakeConveyor;
  VikingMAX upperConveyor;

  private Conveyor() {
    intakeConveyor = new VikingMAX(CAN_INTAKE_CONVEYOR, false);
    upperConveyor = new VikingMAX(CAN_UPPER_CONVEYOR, true);
  }

  public void setOutputIntake(double speed) {
    intakeConveyor.percentOutput(speed);
  }

  public void setOutputUpper(double speed) {
    upperConveyor.percentOutput(speed);
  }

  public void fullStopIntake() {
    intakeConveyor.getSparkMAX().disable();
  }

  public void fullStopUpper() {
    upperConveyor.getSparkMAX().disable();
  }

  public VikingMAX getIntakeMotor() {
    return intakeConveyor;
  }

  public VikingMAX getUpperMotor() {
    return upperConveyor;
  }

  public static Conveyor getInstance() {
    if (instance == null) {
      instance = new Conveyor();
    }
    return instance;
  }
}
