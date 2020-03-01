package frc.robot.auto.auto_commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Constants;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.KitDrivetrain;
import frc.robot.subsystems.Shooter;
import viking.Limelight;
import viking.Limelight.LightMode;
import viking.controllers.PIDController;

public class AimShoot extends CommandBase {

  private PIDController aimPIDController = null;

  private Limelight limelight = null;
  private KitDrivetrain drivetrain = null;
  private Conveyor conveyor = null;
  private Shooter shooter = null;

  public AimShoot() {
    drivetrain = KitDrivetrain.getInstance();
    shooter = Shooter.getInstance();
    limelight = Limelight.getInstance();
    conveyor = Conveyor.getInstance();
    aimPIDController = new PIDController(Constants.AIM_kP, Constants.AIM_kI, Constants.AIM_kD);

    addRequirements(drivetrain, shooter, conveyor);
  }

  @Override
  public void initialize() {
    drivetrain.arcadeDrive(0, 0);
    limelight.setLEDMode(LightMode.ON);
  }

  @Override
  public void execute() {
    if (limelight.validTargets() == true) {
      double horizontalOutput = aimPIDController.calcPID(0, limelight.targetX(), Constants.AIM_kThreshold);

      drivetrain.arcadeDrive(0, horizontalOutput);

      if (aimPIDController.isDone() == true) {
        conveyor.setOutputUpper(1.0);
        conveyor.setOutputIntake(1.0);

        shooter.setOutputTop(0.75);
        shooter.setOutputBottom(0.75);
      }
      else {
        conveyor.fullStop();
        shooter.fullStop();
      }
    }
    else {
      shooter.fullStop();
      conveyor.fullStop();
    }
  }

  @Override
  public void end(boolean interrupted) {
    limelight.setLEDMode(LightMode.OFF);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
