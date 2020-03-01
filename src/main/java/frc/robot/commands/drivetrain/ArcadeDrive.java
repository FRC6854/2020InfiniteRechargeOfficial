package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
import frc.robot.subsystems.Constants;
import frc.robot.subsystems.KitDrivetrain;

import viking.controllers.PIDController;

import viking.Limelight;
import viking.Limelight.LightMode;

import viking.led.LEDController;
import viking.led.LEDController.LEDMode;

public class ArcadeDrive extends CommandBase {

  private Limelight limelight = null;
  private PIDController aimPIDController = null;
  private KitDrivetrain drivetrain = null;

  public ArcadeDrive() {
    limelight = Limelight.getInstance();
    drivetrain = KitDrivetrain.getInstance();
    aimPIDController = new PIDController(Constants.AIM_kP, Constants.AIM_kI, Constants.AIM_kD);
    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    if (Robot.driver.getControllerAButton() == true) {
      // Setup Limelight for targeting
      limelight.setLEDMode(LightMode.ON);
      limelight.setDriverMode(false);

      // Calculate the PID output using a threshold and make the target 0 (center of the crosshair)
      double pidAim = aimPIDController.calcPID(0, limelight.targetX(), Constants.AIM_kThreshold);

      // Set the min and max values for the output
      if (Math.abs(pidAim) < Constants.AIM_kMinCommand) {
        pidAim = 0;
      }

      if (Math.abs(pidAim) > Constants.AIM_kMaxCommand) {
        if (pidAim > 0) pidAim = Constants.AIM_kMaxCommand;
        if (pidAim < 0) pidAim = -Constants.AIM_kMaxCommand;
      }

      // Set the LEDMode
      LEDController.getInstance().setMode(LEDMode.VISION);

      // Drive using the output values
      drivetrain.arcadeDrive(Robot.driver.getControllerLeftStickY(), pidAim);
    }
    else {
      limelight.setLEDMode(LightMode.OFF);
      limelight.setDriverMode(true);

      LEDController.getInstance().setMode(LEDMode.TELEOP);
      drivetrain.arcadeDrive(Robot.driver.getControllerLeftStickY(), Robot.driver.getControllerRightStickX());
    }
  }
}
