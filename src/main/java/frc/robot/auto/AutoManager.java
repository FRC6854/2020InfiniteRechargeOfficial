package frc.robot.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auto.auto_commands.AimShoot;
import frc.robot.commands.drivetrain.*;

public class AutoManager {

    private static AutoManager instance = null;

    private static SendableChooser<Integer> autoChooser = new SendableChooser<Integer>();

    private AutoManager () {
      autoChooser.setDefaultOption("90 Hatch", 1);
      autoChooser.addOption("Auto Shoot", 2);
    }

    public SendableChooser<Integer> getAutoChooser() {
      return autoChooser;
    }

    public Command getAutoChooserCommand() {
      switch (autoChooser.getSelected()) {
        case 2:
          return new AimShoot().withTimeout(15.0);
        default: 
          return null;
      }
    }

    public static AutoManager getInstance() {
      if (instance == null) {
          instance = new AutoManager();
      }
      return instance;
    }
}
