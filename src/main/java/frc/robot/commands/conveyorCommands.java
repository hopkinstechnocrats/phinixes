package frc.robot.commands;

import frc.robot.subsystems.ConveyorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.Constants;


public class conveyorCommands {
    public static Command rotateConveyor(ConveyorSubsystem conveyorSubsystem) {
        return Commands.run(
            () -> { 
            conveyorSubsystem.rotateConveyor(Constants.conveyorOutput);
        }, conveyorSubsystem);
    }
}