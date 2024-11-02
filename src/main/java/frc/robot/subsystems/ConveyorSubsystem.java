package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyorSubsystem extends SubsystemBase{

    // Initialize all the variables so we can use them later

    WPI_TalonSRX conveyorMotor;

    public ConveyorSubsystem(){
		// Create the motor in code
		conveyorMotor = new WPI_TalonSRX(Constants.conveyorMotor);

		// Set motor to default settings
        conveyorMotor.configFactoryDefault();

		// Tell motor to brake when not given any other command
        conveyorMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void rotateConveyor(double motorSpeed) {
        conveyorMotor.set(motorSpeed);
    }
}
