package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Conveyor;

public class DriveIntakeConveyor extends CommandBase {
  
  Conveyor intakeConveyor = null;

  public DriveIntakeConveyor() {
    intakeConveyor = Robot.intakeConveyor;

    addRequirements(intakeConveyor);
  }

  @Override
  public void initialize() {
    intakeConveyor.fullStop();
  }

  @Override
  public void execute() {
    intakeConveyor.setOutput(Robot.driver.getDriverLTrigger());
  }

  @Override
  public void end(boolean interrupted) {
    intakeConveyor.fullStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
