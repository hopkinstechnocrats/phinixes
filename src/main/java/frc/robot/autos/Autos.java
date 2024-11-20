package frc.robot.autos;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.LauncherCommands;
import frc.robot.subsystems.DriveSubsystem;

public class Autos {
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();
    SendableChooser<Command> m_autoChooser = new SendableChooser<>();

    public Command rotateClockwise =        DriveCommands.drive(driveSubsystem, -1, 1).withTimeout(1);
    public Command rotateCounterclockwise = DriveCommands.drive(driveSubsystem, 1, -1).withTimeout(1);
    public Command moveForward(double time) {return DriveCommands.drive(driveSubsystem, 1, 1).withTimeout(time);};
    public Command m_rotationCommand;

    public Command auto_left = new SequentialCommandGroup(
        moveForward(1),
        rotateCounterclockwise,
        moveForward(1)
    );
    
    
    public Command auto_right = new SequentialCommandGroup(
        moveForward(1),
        rotateClockwise,
        moveForward(1)
    );

    public Command selectAuto(){
        m_autoChooser.setDefaultOption("Left", auto_left);
        m_autoChooser.addOption("Right", auto_right);
        SmartDashboard.putData("Are you starting on the left or right side of the field?", m_autoChooser);

        return m_autoChooser.getSelected();
    }
    
    /*public void runAuto () {
        m_autoChooser.setDefaultOption("Left", rotateClockwise);
        m_autoChooser.addOption("Right", rotateCounterclockwise);
        SmartDashboard.putData("Are you starting on the left or right side of the field?", m_autoChooser);
        m_rotationCommand = m_autoChooser.getSelected();

        DriveCommands.drive(driveSubsystem, 1, 1).withTimeout(1);
    }
    */
}