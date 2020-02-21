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
    conveyor.setOutputIntake(output);

    if (Robot.driver.getControllerLBumper() == true || Robot.driver.getControllerRBumper() == true) {
      conveyor.setOutputUpper(output);
    } 
    else {
      if (output == 0) {
        conveyor.fullStopUpper();
      }
      else {
        conveyor.setOutputUpper(output / 10);
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
