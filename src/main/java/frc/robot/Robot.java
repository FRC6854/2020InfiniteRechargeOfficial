package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;

public class Robot extends TimedRobot implements RobotMap {

  
  ColorSensorV3 colourSensor = new ColorSensorV3(I2C_COLOUR_SENSOR);

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  public Color getColourSensor(){
      return colourSensor.getColor();
  }
}
