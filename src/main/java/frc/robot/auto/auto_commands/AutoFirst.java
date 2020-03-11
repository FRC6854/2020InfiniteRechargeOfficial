package frc.robot.auto.auto_commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.ProfileFollower;
import viking.ProfileBuffer;

public class AutoFirst extends SequentialCommandGroup {
  
  private static ProfileBuffer lineTrench = new ProfileBuffer("line-trench");
  private static ProfileBuffer ballShoot = new ProfileBuffer("ball-shoot");

  public AutoFirst() {
    super(new AimShoot().withTimeout(4), 
      new ProfileFollower(lineTrench).raceWith(new RunConveyorTime(
        new double[][] {
          {1, 0.5, 0.5},
          {3.0, 0.0, 0.0}
        })), 
        new ProfileFollower(ballShoot), 
        new AimShoot().withTimeout(4));
  }
}
