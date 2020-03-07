package frc.robot.auto.auto_commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Constants;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.KitDrivetrain;
import frc.robot.subsystems.Shooter;
import viking.Limelight;
import viking.Limelight.LightMode;
import viking.controllers.PIDController;
import viking.led.LEDController;
import viking.led.LEDController.LEDMode;

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
    limelight.setDriverMode(false);
    LEDController.getInstance().setMode(LEDMode.VISION);
  }

  @Override
  public void execute() {
    if (limelight.validTargets() == true) {
      double pidAim = aimPIDController.calcPID(0, -limelight.targetX(), Constants.AIM_kThreshold);

      if (Math.abs(pidAim) > Constants.AIM_kMaxCommand) {
        if      (pidAim > 0) pidAim = Constants.AIM_kMaxCommand;
        else if (pidAim < 0) pidAim = -Constants.AIM_kMaxCommand;
      }

      drivetrain.arcadeDrive(0, pidAim);

      if (aimPIDController.isDone() == true) {
        conveyor.setOutputUpper(0.65);
        conveyor.setOutputIntake(0.65);

        shooter.setOutputTop(0.55);
        shooter.setOutputBottom(0.55);
      }
      else {
        conveyor.fullStop();
        shooter.fullStop();
      }
    }
    else {
      shooter.fullStop();
      conveyor.fullStop();
      drivetrain.arcadeDrive(0, 0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    LEDController.getInstance().setMode(LEDMode.DEFAULT);
    limelight.setDriverMode(true);
    limelight.setLEDMode(LightMode.OFF);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
