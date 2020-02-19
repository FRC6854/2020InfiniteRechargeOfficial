package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.KitDrivetrain;

public class ArcadeDrive extends CommandBase {
  private KitDrivetrain drivetrain = null;

  public ArcadeDrive() {
    drivetrain = KitDrivetrain.getInstance();

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    drivetrain.arcadeDrive(Robot.driver.getControllerLeftStickY(), Robot.driver.getControllerRightStickX());
  }
}
