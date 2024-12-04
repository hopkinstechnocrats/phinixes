package frc.robot.commands;

import frc.robot.subsystems.LauncherSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsytem;



public class LauncherCommands {
    public static Command rotateFeeder(IntakeSubsytem intakeSubsystem, double direction) {
        return Commands.run(
            () -> { 
            intakeSubsystem.spinFeeder(Constants.feederOutput * direction);
        }, intakeSubsystem);
    }

    public static Command intake(IntakeSubsytem intakeSubsystem, double direction) {
        return Commands.run(
            () -> { 
            intakeSubsystem.intake(Constants.feederOutput * direction, Constants.feederOutput*-direction);
        }, intakeSubsystem);
    }

    public static Command spinUpLauncher(LauncherSubsystem launcherSubsystem) {
        return Commands.run(
            () -> { 
            launcherSubsystem.shoot(Constants.shooterOutput);
        }, launcherSubsystem);
    }

    //TODO public static Command doNothing() {return Commands.run(() -> {});};
    public static Command brakeLauncher(LauncherSubsystem launcherSubsystem){
        return Commands.run(() -> {
            launcherSubsystem.brake();
        }, launcherSubsystem);
    }


    public static Command fire(LauncherSubsystem launcherSubsystem, IntakeSubsytem intakeSubsytem) {
        return new ParallelCommandGroup(
            rotateFeeder(intakeSubsytem, 1).withTimeout(1),
            brakeLauncher(launcherSubsystem).withTimeout(.5).andThen(spinUpLauncher(launcherSubsystem).withTimeout(.5))
        );
    }
    
}