package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motion.MotionProfileStatus;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Constants;
import frc.robot.subsystems.KitDrivetrain;

import viking.ProfileBuffer;

public class ProfileFollower extends CommandBase {

  private KitDrivetrain drivetrain = null;

  private ProfileBuffer profile;
  private MotionProfileStatus status;

  StringBuilder output = new StringBuilder();

  public ProfileFollower(String path) {
    drivetrain = KitDrivetrain.getInstance();

    profile = new ProfileBuffer("/home/lvuser/paths/" + path);

    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    drivetrain.zeroSensors();
    drivetrain.resetMotionProfile();

    System.out.println("Filling Talons...");

    drivetrain.initMotionBuffers(profile.getLeftProfile(), profile.getRightProfile());

    System.out.println("Executing the Profile");

    drivetrain.motionProfile();
  }

  @Override
  public void execute() {
    drivetrain.getLeftMaster().getTalonSRX().getMotionProfileStatus(status);

    output.append("Number of points: ");
    output.append(status.topBufferCnt);
    output.append(" Output type: ");
    output.append(status.outputEnable.name());
    output.append(" Has underrun: ");
    output.append(status.hasUnderrun);

    System.out.println(output.toString());

    output = new StringBuilder();
  }

  @Override
  public boolean isFinished() {
    return (drivetrain.getLeftMaster().isMotionProfileFinished() && drivetrain.getRightMaster().isMotionProfileFinished());
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted == true) {
      System.out.println("Interrupted");
    }
    System.out.println("Done MP driving!");
    drivetrain.resetMotionProfile();
  }
}
