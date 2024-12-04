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

import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsytem;
import frc.robot.commands.LauncherCommands;
import frc.robot.autos.Autos;
//import frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Commands;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {



    // The robot's subsystems and commands are defined here...
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();

    private final IntakeSubsytem intakeSubsystem = new IntakeSubsytem();

    private final Autos autos = new Autos();

    private final XboxController driveController = new XboxController(Constants.driverXboxControllerPort);
  
    private final LauncherSubsystem launcherSubsystem = new LauncherSubsystem();

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

    launcherSubsystem.setDefaultCommand(
    new RunCommand(
            () -> {
                launcherSubsystem.brake();
            }
            , launcherSubsystem
        )
    );

    intakeSubsystem.setDefaultCommand(
      new RunCommand(
            () -> {
                intakeSubsystem.brake();
            }
            , intakeSubsystem
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
        operatorController.a().whileTrue(LauncherCommands.intake(intakeSubsystem, -1));
        operatorController.b().whileTrue(LauncherCommands.intake(intakeSubsystem, 1));

        //operatorController.x().onTrue(LauncherCommands.fire(launcherSubsystem));
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
    return autos.selectAuto();
  }
}