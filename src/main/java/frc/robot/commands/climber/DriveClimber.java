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
    double liftOutput = Robot.operator.getControllerRTrigger() - Robot.operator.getControllerLTrigger();
    climber.setLiftOutput(liftOutput);
    climber.setWinchOutput(Robot.operator.getControllerLeftStickY());
    climber.setShifterOutput(Robot.operator.getControllerRightStickY());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
