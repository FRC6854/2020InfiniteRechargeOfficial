package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class DriveShooter extends CommandBase {

  private Shooter shooter = null;
  
  public DriveShooter() {
    shooter = Shooter.getInstance();
    addRequirements(shooter);
  }

  @Override
  public void execute() {
    if (Robot.driver.getControllerRBumper() == true) {
      shooter.setOutputTop(0.75);
      shooter.setOutputBottom(0.75);
    }
    else if (Robot.driver.getControllerLBumper() == true) {
      shooter.setOutputTop(-0.25);
      shooter.setOutputBottom(0.3);
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
