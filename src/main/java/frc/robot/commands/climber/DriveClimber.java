package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.Robot;

public class DriveClimber extends CommandBase {

  private Climber climber = null;

  public DriveClimber() {
    climber = Climber.getInstance();

    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.fullStop();
  }

  @Override
  public void execute() {
    // TODO: Revise Controls
    
    if(Robot.driver.getControllerAButtonPressed()) {
      climber.setLiftAngle(90);
    }

    if(Robot.driver.getControllerBButtonPressed()) {
      climber.setLiftAngle(0);
    }

    if(Robot.driver.getControllerXButtonPressed()) {
      climber.setLiftAngle(5);
    }

    if (Robot.driver.getControllerYButtonPressed()) {
      climber.setWinchOutput(1);
    }

    if (Robot.driver.getControllerStartButtonPressed()) {
      climber.setWinchOutput(-1);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
