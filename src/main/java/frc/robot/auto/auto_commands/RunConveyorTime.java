package frc.robot.auto.auto_commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class RunConveyorTime extends CommandBase {

  private Conveyor conveyor = null;

  private double[][] points;
  private Timer timer;

  /**
   * Run the conveyor at certain times at certain speeds
   * @param points [i][0] is the time, and [i][1] is the speed of the conveyor at that time
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
    for (int i = 0; i < points.length; i++) {
      if (timer.get() >= points[i][0]) {
        conveyor.setOutputIntake(points[i][1]);
        conveyor.setOutputUpper(points[i][1]);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
