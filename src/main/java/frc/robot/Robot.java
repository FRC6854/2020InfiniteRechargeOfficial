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

  public static Conveyor intakeConveyor = null;
  public static Conveyor upperConveyor = null;

  public static Shooter topShooter = null;
  public static Shooter bottomShooter = null;

  private static KitDrivetrain drivetrain = null;

  @Override
  public void robotInit() {
    driver = new Controller(CONTROLLER_DRIVER);

    drivetrain = KitDrivetrain.getInstance();

    intakeConveyor = new Conveyor(CAN_INTAKE_CONVEYOR, false);
    upperConveyor = new Conveyor(CAN_UPPER_CONVEYOR, false);
    topShooter = new Shooter(CAN_TOP_SHOOTER, false);
    bottomShooter = new Shooter(CAN_BOTTOM_SHOOTER, true);

    intakeConveyor.setDefaultCommand(new DriveConveyor(intakeConveyor));
    upperConveyor.setDefaultCommand(new DriveConveyor(upperConveyor));
    topShooter.setDefaultCommand(new DriveShooter(topShooter));
    bottomShooter.setDefaultCommand(new DriveShooter(bottomShooter));

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
