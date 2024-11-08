package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.Constants;


public class DriveCommands {
    public static Command drive(DriveSubsystem m_driveSubsystem, double left, double right) {
        return Commands.run(
            () -> { 
            m_driveSubsystem.drive(Constants.maxMotorOutput*left, Constants.maxMotorOutput*right);
        }, m_driveSubsystem);
    }
}