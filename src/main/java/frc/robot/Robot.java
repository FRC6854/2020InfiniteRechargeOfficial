package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.drivetrain.DriveIntakeConveyor;
import frc.robot.commands.drivetrain.ProfileFollower;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.KitDrivetrain;
import viking.Controller;
import viking.OI;
import viking.controllers.rev.VikingMAX;

public class Robot extends TimedRobot implements RobotMap {

  public static Controller driver = null;
  public static Conveyor intakeConveyor = null;

  private static KitDrivetrain drivetrain = null;

  @Override
  public void robotInit() {
    drivetrain = KitDrivetrain.getInstance();
    driver = new Controller(CONTROLLER_DRIVER);

    intakeConveyor = new Conveyor(CAN_INTAKE_LOWER, false, CAN_CONVEYOR_UPPER, false);

    OI.getInstance();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().schedule(new DriveIntakeConveyor());
    CommandScheduler.getInstance().schedule(new ProfileFollower("testing"));
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    //spark.percentOutput(0.5);
  }
}
