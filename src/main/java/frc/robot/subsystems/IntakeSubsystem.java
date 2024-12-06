package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    WPI_TalonSRX intakeMotor;
    WPI_TalonSRX feedMotor;

    public IntakeSubsystem(){
        intakeMotor  = new WPI_TalonSRX(Constants.intake);
        feedMotor = new WPI_TalonSRX(Constants.feed);

        intakeMotor.configFactoryDefault();
        feedMotor.configFactoryDefault();

        intakeMotor.setNeutralMode(NeutralMode.Brake);
        feedMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void intake(double intakeSpeed, double feedSpeed) {
        intakeMotor.set(intakeSpeed);
        feedMotor.set(feedSpeed);
    }

    public void brake(){
        intakeMotor.stopMotor();
        feedMotor.stopMotor();
    }

    public void spinFeeder(double motorSpeed){
        feedMotor.set(motorSpeed);
    }

}
