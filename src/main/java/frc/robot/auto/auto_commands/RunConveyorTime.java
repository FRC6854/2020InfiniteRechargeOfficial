package frc.robot.auto.auto_commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class RunConveyorTime extends CommandBase {

  private Conveyor conveyor = null;

  private double[][] points;
  private Timer timer;

  private int lastPoint = 0;

  /**
   * Run the conveyor at certain times at certain speeds
   * @param points [i][0] is the time, and [i][1] is the speed of the intake conveyor at that time, and [i][2] is the speed of the upper conveyor
   */
  public RunConveyorTime(double[][] points) {
    conveyor = Conveyor.getInstance();

    this.points = points;
    timer = new Timer();
  }

  @Override
  public void initialize() {
    timer.start();
  }

  @Override
  public void execute() {
    double lastOutputIntake = 0;
    double lastOutputUpper = 0;

    for (int i = 0; i < points.length; i++) {
      if (timer.get() >= points[i][0]) {
        lastPoint = i;
        lastOutputIntake = points[i][1];
        lastOutputUpper = points[i][2];
      }
    }

    conveyor.setOutputIntake(lastOutputIntake);
    conveyor.setOutputUpper(lastOutputUpper);
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
    conveyor.fullStop();
  }

  @Override
  public boolean isFinished() {
    if (lastPoint == points.length) {
      return true;
    }
    return false;
  }
}
