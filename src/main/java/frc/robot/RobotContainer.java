// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;

import frc.robot.commands.conveyorCommands;
import frc.robot.autos.Autos;
import frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    //variables for which auto to use
    private static final String kRotateClockwise = "Default";
    private static final String kRotateCounterclockwise = "My Auto";
    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();



    // The robot's subsystems and commands are defined here...
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();

    private final Autos autos = new Autos();

    private final XboxController driveController = new XboxController(Constants.driverXboxControllerPort);
  
    private final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();

    private final CommandXboxController operatorController = new CommandXboxController(Constants.operatorXboxControllerPort);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {


    // Configure the button bindings
    configureButtonBindings();

    driveSubsystem.setDefaultCommand(
            new RunCommand(
                () -> {
                  driveSubsystem.drive(Constants.maxMotorOutput*driveController.getLeftY(),
                  Constants.maxMotorOutput*driveController.getRightY());
                }
        , driveSubsystem)
    );

    conveyorSubsystem.setDefaultCommand(
    new RunCommand(
            () -> {
                conveyorSubsystem.brake();
            }
            , conveyorSubsystem
        )
    );
  
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    operatorController.a().whileTrue(conveyorCommands.rotateConveyor(conveyorSubsystem));
    
}
   
  

  public DriveSubsystem getDriveSubsystem() {
    return driveSubsystem;
  }
  
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return Void;//TODO return auto chooser
  }
}