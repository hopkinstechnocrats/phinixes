package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSubsystem extends SubsystemBase{

    // Initialize all the variables so we can use them later

   
    WPI_TalonSRX shooterMotorTop;
    WPI_TalonSRX shooterMotorBottom;

    public LauncherSubsystem(){
		// Create the motors in code
        shooterMotorTop  = new WPI_TalonSRX(Constants.shooterMotorTop);
        shooterMotorBottom = new WPI_TalonSRX(Constants.shooterMotorBottom);

		// Set motors to default settings
        shooterMotorTop. configFactoryDefault();
        shooterMotorBottom.configFactoryDefault();

		// Tell motors to brake when not given any other command
        shooterMotorTop. setNeutralMode(NeutralMode.Brake);
        shooterMotorBottom.setNeutralMode(NeutralMode.Brake);
    }


    public void brake() {
        shooterMotorTop. stopMotor();
        shooterMotorBottom.stopMotor();
    }

    public void shoot(double motorSpeed) {
        shooterMotorTop.set(motorSpeed);
        shooterMotorBottom.set(motorSpeed);
    }

}
