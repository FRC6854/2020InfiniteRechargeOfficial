package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.auto.AutoManager;
import frc.robot.commands.conveyor.DriveConveyor;
import frc.robot.commands.shooter.DriveShooter;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.KitDrivetrain;
import frc.robot.subsystems.Shooter;
import io.github.oblarg.oblog.Logger;
import viking.Controller;
import viking.OI;

public class Robot extends TimedRobot implements RobotMap {

  public static Controller driver = null;
  public static Controller operator = null;

  private static AutoManager autoManager = null;

  private static Conveyor conveyor = null;
  private static Shooter shooter = null;

  private static KitDrivetrain drivetrain = null;

  @Override
  public void robotInit() {
    // Logger Setup
    Logger.configureLoggingAndConfig(this, false);
    Logger.setCycleWarningsEnabled(false);

    autoManager = AutoManager.getInstance();

    driver = new Controller(CONTROLLER_DRIVER);
    operator = new Controller(CONTROLLER_OPERATOR);
    
    drivetrain = KitDrivetrain.getInstance();

    conveyor = Conveyor.getInstance();
    shooter = Shooter.getInstance();
    
    conveyor.setDefaultCommand(new DriveConveyor());
    shooter.setDefaultCommand(new DriveShooter());

    OI.getInstance();
  }

  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().schedule(autoManager.getAutoChooserCommand());
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
