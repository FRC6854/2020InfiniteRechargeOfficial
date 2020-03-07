package frc.robot.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auto.auto_commands.AimShoot;
import frc.robot.auto.auto_commands.RunConveyorTime;
import frc.robot.commands.debug.LimelightCalibration;
import frc.robot.commands.drivetrain.*;

public class AutoManager {

    private static AutoManager instance = null;

    private static SendableChooser<Integer> autoChooser = new SendableChooser<Integer>();

    private AutoManager () {
      autoChooser.setDefaultOption("Limelight Calibration", 1);
      autoChooser.addOption("Auto Shoot", 2);
      autoChooser.addOption("Conveyor Time", 3);
    }

    public SendableChooser<Integer> getAutoChooser() {
      return autoChooser;
    }

    public Command getAutoChooserCommand() {
      switch (autoChooser.getSelected()) {
        case 1:
          return new LimelightCalibration();
        case 2:
          return new AimShoot().withTimeout(2.0);
        case 3:
          // Run at 50% speed at start and stop after 1 second then at 2 seconds go at 50% speed
          return new RunConveyorTime(
            new double[][] {
              {0.0, 0.5}, 
              {1.0, 0.0},
              {2.0, 0.5}
            }
          );
      }

      return null;
    }

    public static AutoManager getInstance() {
      if (instance == null) {
          instance = new AutoManager();
      }
      return instance;
    }
}
