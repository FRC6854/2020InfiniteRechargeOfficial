package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Conveyor;

public class DriveConveyor extends CommandBase {

  Conveyor conveyor = null;

  public DriveConveyor() {
    conveyor = Conveyor.getInstance();
    addRequirements(conveyor);
  }

  @Override
  public void initialize() {
    conveyor.fullStopIntake();
    conveyor.fullStopUpper();
  }

  @Override
  public void execute() {
    double output = Robot.driver.getControllerRTrigger() - Robot.driver.getControllerLTrigger();

    if (Robot.driver.getControllerLBumper() == true || Robot.driver.getControllerRBumper() == true) {
      conveyor.setOutputIntake(output);
      conveyor.setOutputUpper(output);
    } 
    else {
      if (output > 0) {
        conveyor.setOutputIntake(output);
        conveyor.setOutputUpper(output / 10);
      }
      else if (output < 0) {
        conveyor.setOutputIntake(output / 10);
        conveyor.setOutputUpper(output);
      }
      else {
        conveyor.fullStopIntake();
        conveyor.fullStopUpper();
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    conveyor.fullStopIntake();
    conveyor.fullStopUpper();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
