package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.commands.drivetrain.ProfileFollower;
import frc.robot.subsystems.KitDrivetrain;
import viking.Controller;
import viking.OI;
import viking.controllers.rev.VikingMAX;

public class Robot extends TimedRobot implements RobotMap {

  public static Controller driver = null;
  public static VikingMAX spark = null;

  private static KitDrivetrain drivetrain = null;

  @Override
  public void robotInit() {
    drivetrain = KitDrivetrain.getInstance();
    driver = new Controller(CONTROLLER_DRIVER);
    
    // CAN ID, Inverted, kF, kP, kI, kD, velocity, acceleration
    spark = new VikingMAX(CAN_SPARK_MAX, false, 0.0, 0.05, 0.0, 0.0, 0.0, 0.0);
  
    OI.getInstance();
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Current Output", spark.getOutput());
    SmartDashboard.putNumber("Position", spark.getPosition());
    SmartDashboard.putNumber("Velocity", spark.getVelocity());
  }

  @Override
  public void autonomousInit() {
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
