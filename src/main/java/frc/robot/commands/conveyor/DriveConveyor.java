package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Conveyor;

public class DriveConveyor extends CommandBase {
  
  Conveyor conveyor = null;

  public DriveConveyor(Conveyor motor) {
    this.conveyor = motor;

    addRequirements(conveyor);
  }

  @Override
  public void initialize() {
    conveyor.fullStop();
  }

  @Override
  public void execute() {
    double output = Robot.driver.getControllerRTrigger() - Robot.driver.getControllerLTrigger();
    
    conveyor.setOutput(output);
  }

  @Override
  public void end(boolean interrupted) {
    conveyor.fullStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
