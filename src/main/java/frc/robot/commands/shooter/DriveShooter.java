package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Constants;
import frc.robot.subsystems.Shooter;

public class DriveShooter extends CommandBase {

  private Shooter shooter = null;
  
  public DriveShooter() {
    shooter = Shooter.getInstance();
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    shooter.setPIDF(Constants.SHOOTER_kP, Constants.SHOOTER_kI, Constants.SHOOTER_kD, Constants.SHOOTER_kF);
  }

  @Override
  public void execute() {
    if (Robot.driver.getControllerRBumper() == true) {
      shooter.setOutputTop(0.55);
      shooter.setOutputBottom(0.55);
    }
    else if (Robot.driver.getControllerLBumper() == true) {
      shooter.setOutputTop(0.3);
      shooter.setOutputBottom(-0.35);
    }
    else {
      shooter.fullStopTop();
      shooter.fullStopBottom();
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
