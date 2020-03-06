package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.auto.AutoManager;
import frc.robot.commands.conveyor.DriveConveyor;
import frc.robot.commands.shooter.DriveShooter;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.KitDrivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Climber;
import viking.Controller;
import viking.OI;

public class Robot extends TimedRobot implements RobotMap {

  public static Controller driver = null;
  public static Controller operator = null;

  private static AutoManager autoManager = null;

  private static Conveyor conveyor = null;
  private static Shooter shooter = null;
  private static Climber climber = null;

  private static KitDrivetrain drivetrain = null;

  @Override
  public void robotInit() {
    autoManager = AutoManager.getInstance();

    driver = new Controller(CONTROLLER_DRIVER);
    operator = new Controller(CONTROLLER_OPERATOR);

    operator.setControllerLeftStickYDeadband(0.05);
    
    drivetrain = KitDrivetrain.getInstance();

    conveyor = Conveyor.getInstance();
    shooter = Shooter.getInstance();
    climber = Climber.getInstance();

    conveyor.setDefaultCommand(new DriveConveyor());
    shooter.setDefaultCommand(new DriveShooter());

    OI.getInstance();
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Lift Ticks", climber.getLiftTicks());
    SmartDashboard.putData(autoManager.getAutoChooser());
  }

  @Override
  public void disabledInit() {
    System.out.println("Disabled");
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void autonomousInit() {
    System.out.println("Autonomous");
    CommandScheduler.getInstance().schedule(autoManager.getAutoChooserCommand());
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    System.out.println("Tele-op");
  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
