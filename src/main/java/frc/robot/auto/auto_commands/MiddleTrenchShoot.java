package frc.robot.auto.auto_commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.ProfileFollower;
import viking.ProfileBuffer;

public class MiddleTrenchShoot extends SequentialCommandGroup {
  
  private static ProfileBuffer lineTrench = new ProfileBuffer("line-trench");
  private static ProfileBuffer ballShoot = new ProfileBuffer("ball-shoot");

  public MiddleTrenchShoot() {
    super(
      new AimShoot().withTimeout(4), 
      new ProfileFollower(lineTrench).raceWith(
        new RunConveyorTime(
          new double[][] {
            {1.0, 1.0, 0.25},
            {6.5, 0.0, 0.0}
          }
        )
      ), 
      new ProfileFollower(ballShoot), 
      new AimShoot().withTimeout(4)
    );
  }
}
