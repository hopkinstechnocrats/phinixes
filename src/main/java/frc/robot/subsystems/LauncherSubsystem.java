package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSubsystem extends SubsystemBase{

    // Initialize all the variables so we can use them later

    WPI_TalonSRX feederMotorLeft;
    WPI_TalonSRX feederMotorRight;

    WPI_TalonSRX shooterMotorLeft;
    WPI_TalonSRX shooterMotorRight;

    public LauncherSubsystem(){
		// Create the motors in code
		feederMotorLeft  = new WPI_TalonSRX(Constants.feederMotorLeft);
        feederMotorRight = new WPI_TalonSRX(Constants.feederMotorRight);
        shooterMotorLeft  = new WPI_TalonSRX(Constants.shooterMotorLeft);
        shooterMotorRight = new WPI_TalonSRX(Constants.shooterMotorRight);

		// Set motors to default settings
        feederMotorLeft. configFactoryDefault();
        feederMotorRight.configFactoryDefault();
        shooterMotorLeft. configFactoryDefault();
        shooterMotorRight.configFactoryDefault();

		// Tell motors to brake when not given any other command
        feederMotorLeft. setNeutralMode(NeutralMode.Brake);
        feederMotorRight.setNeutralMode(NeutralMode.Brake);
        shooterMotorLeft. setNeutralMode(NeutralMode.Brake);
        shooterMotorRight.setNeutralMode(NeutralMode.Brake);
    }

    public void rotateFeeder(double motorSpeed) {
        feederMotorLeft. set(motorSpeed);
        feederMotorRight.set(motorSpeed);
    }

    public void brake() {
        feederMotorLeft. stopMotor();
        feederMotorRight.stopMotor();
        shooterMotorLeft. stopMotor();
        shooterMotorRight.stopMotor();
    }

    public void shoot(double motorSpeed) {
        shooterMotorLeft.set(motorSpeed);
        shooterMotorRight.set(motorSpeed);
    }

    /**public void fire(double feederSpeed, double shooterSpeed) {

        feederMotorLeft. stopMotor();
        feederMotorRight.stopMotor();
    } **/
}
