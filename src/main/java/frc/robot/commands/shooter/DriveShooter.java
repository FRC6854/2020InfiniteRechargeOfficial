package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class DriveShooter extends CommandBase {

  private Shooter motor = null;
  
  public DriveShooter(Shooter motor) {
    this.motor = motor;

    addRequirements(motor);
  }

  @Override
  public void execute() {
    if (Robot.driver.getControllerRBumper() == true) {
      motor.setOutput(0.75);
    }
    else {
      motor.fullStop();
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
