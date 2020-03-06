package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Constants;
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
    double winchOutput = liftOutput - Robot.operator.getControllerLTrigger();

    climber.setWinchOutput(winchOutput);
    /*double liftOutput = Robot.operator.getControllerRTrigger();
    double winchOutput = liftOutput - Robot.operator.getControllerLTrigger();

    climber.setShifterOutput(Robot.operator.getControllerRightStickX());

    if (Math.abs(liftOutput) > 0.25) {
      liftOutput = 0.25;
    }

    winchOutput *= 0.75;

    if (Math.abs(winchOutput) >= 1) {
      if      (winchOutput > 0) winchOutput = 1;
      else if (winchOutput < 0) winchOutput = -1;
    }

    if (climber.getLiftTicks() <= 0.1) {
      climber.setWinchOutput(0);
    }
    else {
      if (winchOutput > 0) {
        if (climber.getLiftTicks() >= Constants.LIFT_MAX_ROTATIONS - 0.05) {
          climber.setWinchOutput(0);
        }
        else {
          climber.setWinchOutput(winchOutput);
        }
      }
      else if (winchOutput < 0) {
        winchOutput *= 2;
        climber.setWinchOutput(winchOutput);
        System.out.println(winchOutput);
      }
      else {
        climber.setWinchOutput(winchOutput);
      }
    }

    if (winchOutput <= -0.05) {
      if (Robot.operator.getControllerBButton() == true) {
        climber.setLiftOutput(-0.5);
      }
      else {
        climber.setLiftOutput(liftOutput);
      }
    }
    else {
      climber.setLiftOutput(liftOutput);
    }*/
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
