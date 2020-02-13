package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.commands.conveyor.DriveIntakeConveyor;
import frc.robot.subsystems.Conveyor;
// import frc.robot.subsystems.KitDrivetrain;
import viking.Controller;
import viking.OI;

public class Robot extends TimedRobot implements RobotMap {

  public static Controller driver = null;
  public static Conveyor intakeConveyor = null;

  // private static KitDrivetrain drivetrain = null;

  @Override
  public void robotInit() {
    driver = new Controller(CONTROLLER_DRIVER);

    // drivetrain = KitDrivetrain.getInstance();

    intakeConveyor = new Conveyor(CAN_INTAKE, false);
    intakeConveyor.setDefaultCommand(new DriveIntakeConveyor());

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
