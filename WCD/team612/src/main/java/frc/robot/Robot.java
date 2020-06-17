/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

  private Command m_autonomousCommand;

  // Initializes an encoder on DIO pins 0 and 1
  // Defaults to 4X decoding and non-inverted
  Encoder encoder = new Encoder(0, 1);
    // Talons for drivetrain
    private CANSparkMax spark_fr_drive = new CANSparkMax(Constants.SPARK_FR_DRIVE, MotorType.kBrushless);
    private CANSparkMax spark_fl_drive = new CANSparkMax(Constants.SPARK_FL_DRIVE, MotorType.kBrushless);
    private CANSparkMax spark_br_drive = new CANSparkMax(Constants.SPARK_BR_DRIVE, MotorType.kBrushless);
    private CANSparkMax spark_bl_drive = new CANSparkMax(Constants.SPARK_BL_DRIVE, MotorType.kBrushless);
  
    SpeedControllerGroup leftMotors = new SpeedControllerGroup(spark_fl_drive, spark_bl_drive);
    SpeedControllerGroup rightMotors = new SpeedControllerGroup(spark_fr_drive, spark_br_drive);
    
    DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);
     // Controller deadzone constant
    private final double DEADZONE = 0.1;

  //private RobotContainer m_robotContainer;
  public void arcadeDrive(double x_axis, double y_axis) {  
    //sets up deadzones
    x_axis = Math.abs(x_axis) < DEADZONE ? 0.0 : x_axis;
    y_axis = Math.abs(y_axis) < DEADZONE ? 0.0 : y_axis;

    //WPI_Talon SRX Caps voltage at 1.0
    double leftCommand = y_axis - x_axis;
    double rightCommand = y_axis + x_axis;
    
    // right side motor controls
    spark_fr_drive.set(-rightCommand);
    spark_br_drive.set(-rightCommand);

    //left side motor controls
    spark_fl_drive.set(leftCommand);
    spark_bl_drive.set(leftCommand);
  }

  // Basic tank drive function
  public void tankDrive(double left_command, double right_command) {
    left_command = Math.abs(left_command) < DEADZONE ? 0.0 : left_command;
    right_command = Math.abs(right_command) < DEADZONE ? 0.0 : right_command;

    spark_fr_drive.set(-right_command);
    spark_br_drive.set(-right_command);

    spark_fl_drive.set(left_command);
    spark_bl_drive.set(left_command);
  }

  public double getEncoderValue(){
    return encoder.getDistance();
  }
  @Override
  public void robotInit() {
   // Configures the encoder's distance-per-pulse
    // The robot moves forward 1 foot per encoder rotation
    // There are 256 pulses per encoder rotation
    encoder.setDistancePerPulse(1./256.);
  }
  
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Double: ", getEncoderValue());
  }
  
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
  
  }

  @Override
  public void autonomousPeriodic(){
    // Drives forward at half speed until the robot has moved 5 feet, then stops:
    if(encoder.getDistance() < 5) {
      drive.tankDrive(.5, .5);
  } else {
      drive.tankDrive(0, 0);
  }
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
  
}
