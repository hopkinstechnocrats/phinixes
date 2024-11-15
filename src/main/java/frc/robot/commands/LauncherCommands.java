package frc.robot.commands;

import frc.robot.subsystems.LauncherSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;


public class LauncherCommands {
    public static Command rotateFeeder(LauncherSubsystem launcherSubsystem, double direction) {
        return Commands.run(
            () -> { 
            launcherSubsystem.rotateFeeder(Constants.feederOutput * direction);
        }, launcherSubsystem);
    }

    public static Command shoot(LauncherSubsystem launcherSubsystem) {
        return Commands.run(
            () -> { 
            launcherSubsystem.shoot(Constants.shooterOutput);
        }, launcherSubsystem);
    }

    public static Command doNothing() {return Commands.run(() -> {});};

    public static Command fire(LauncherSubsystem launcherSubsystem) {
        return new ParallelCommandGroup(
            rotateFeeder(launcherSubsystem, 1).withTimeout(1),
            doNothing().withTimeout(.5).andThen(shoot(launcherSubsystem).withTimeout(.5))
        );
    }
    
}