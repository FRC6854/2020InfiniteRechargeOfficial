package frc.robot.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.drivetrain.*;
import io.github.oblarg.oblog.annotations.Log;

public class AutoManager {

    private static AutoManager instance = null;

    private static SendableChooser<Integer> autoChooser = new SendableChooser<Integer>();

    private AutoManager () {
      autoChooser.setDefaultOption("90 Hatch", 1);
      autoChooser.addOption("Drive Profile Hatch", 2);
      autoChooser.addOption("45 Hatch", 3);
      autoChooser.addOption("Testing", 4);
      autoChooser.addOption("Profile Follow", 5);
    }

    @Log(name = "Auto Mode")
    public SendableChooser<Integer> getAutoChooser() {
      return autoChooser;
    }

    public Command getAutoChooserCommand() {
      switch (autoChooser.getSelected()) {
        default: return null;
      }
    }

    public static AutoManager getInstance() {
      if (instance == null) {
          instance = new AutoManager();
      }

      return instance;
    }
}
