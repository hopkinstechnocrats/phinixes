package frc.robot.commands;

import frc.robot.subsystems.LauncherSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;



public class LauncherCommands {
    public static Command rotateFeeder(IntakeSubsystem intakeSubsystem, double direction) {
        return Commands.run(
            () -> { 
            intakeSubsystem.spinFeeder(Constants.feederOutput * direction);
        }, intakeSubsystem);
    }

    public static Command intake(IntakeSubsystem intakeSubsystem, double direction) {
        return Commands.run(
            () -> { 
            intakeSubsystem.intake(Constants.intakeOutput * direction, Constants.feederOutput * direction);
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

    public static Command brakeFeeder(IntakeSubsystem intakeSubsystem){
        return Commands.run(() -> {
            intakeSubsystem.brake();
        }, intakeSubsystem);
    }


    public static Command fire(LauncherSubsystem launcherSubsystem, IntakeSubsystem intakeSubsystem) {
        return new ParallelCommandGroup(
            spinUpLauncher(launcherSubsystem).withTimeout(1.5),
            brakeFeeder(intakeSubsystem).withTimeout(1).andThen(rotateFeeder(intakeSubsystem,1).withTimeout(.5))
        );
    }
    
}