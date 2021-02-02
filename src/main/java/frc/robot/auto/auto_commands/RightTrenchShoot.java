package frc.robot.auto.auto_commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveAngle;
import frc.robot.commands.drivetrain.ProfileFollower;
import frc.robot.commands.drivetrain.ZeroGyro;
import viking.ProfileBuffer;

public class RightTrenchShoot extends SequentialCommandGroup {

  private static ProfileBuffer ballShoot = new ProfileBuffer("ball-shoot");
  private static ProfileBuffer driveStraight = new ProfileBuffer("drive_straight_right_trench");

  public RightTrenchShoot() {
    super(
      new ZeroGyro(),
      new DriveAngle(-25, 0.5).withTimeout(1.0),
      new AimShoot().withTimeout(3.0),
      new DriveAngle(0, 0.5).withTimeout(1.0),
      new ProfileFollower(driveStraight).raceWith(
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
