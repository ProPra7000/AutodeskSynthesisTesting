package frc.robot;

//import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.drivetrain.*;
import frc.robot.subsystems.*;
import frc.robot.controls.ControlMap;

public class RobotContainer {
  
  // Subsystem objects
  private final Drivetrain m_drivetrain = new Drivetrain();


  // Default command objects
  private final DefaultDrive c_drive = new DefaultDrive(m_drivetrain);

  
  
  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {

    // Drivetrain gear shift controls
    ControlMap.driver_button_RB.whenPressed(new SetHighGear(m_drivetrain));
    ControlMap.driver_button_LB.whenPressed(new SetLowGear(m_drivetrain));    
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_drive);
  }

}
