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
    climber.zeroLift();
    climber.fullStop();
  }

  @Override
  public void execute() {
    double liftOutput = Robot.operator.getControllerRTrigger();
    double winchOutput = liftOutput;

    climber.setShifterOutput(Robot.operator.getControllerRightStickX());

    if (Math.abs(liftOutput) > 0.25) {
      liftOutput = 0.25;
    }

    winchOutput *= 0.75;

    if (Robot.operator.getControllerBButton() == true) {
      if (winchOutput > 0) {
        if (climber.getLiftTicks() <= 0.50) {
          climber.setWinchOutput(0);
        }
        else {
          climber.setWinchOutput(winchOutput);
        }
      }
      else {
        climber.setWinchOutput(winchOutput);
      }
    }
    else {
      climber.setLiftOutput(liftOutput);
      climber.setWinchOutput(0);
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
