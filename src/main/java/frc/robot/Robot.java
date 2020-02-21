package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.conveyor.DriveConveyor;
import frc.robot.commands.shooter.DriveShooter;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.KitDrivetrain;
import frc.robot.subsystems.Shooter;
import viking.Controller;
import viking.OI;

public class Robot extends TimedRobot implements RobotMap {

  public static Controller driver = null;

  public static Conveyor conveyor = null;
  public static Shooter shooter = null;

  private static KitDrivetrain drivetrain = null;

  @Override
  public void robotInit() {
    driver = new Controller(CONTROLLER_DRIVER);

    drivetrain = KitDrivetrain.getInstance();

    conveyor = Conveyor.getInstance();
    shooter = Shooter.getInstance();
    
    conveyor.setDefaultCommand(new DriveConveyor());
    shooter.setDefaultCommand(new DriveShooter());

    OI.getInstance();
  }

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
