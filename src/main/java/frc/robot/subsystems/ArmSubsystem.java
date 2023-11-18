package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class ArmSubsystem extends SubsystemBase {
 // new CANSparkMax(Constats.drive.lt, MotorType.kBrushless);

// Arm motor one 
public final WPI_VictorSPX ArmMoterOne = new WPI_VictorSPX(Constants.drive.ArmMotorOne);

//Arm motor two
public final WPI_VictorSPX ArmMotorTwo = new WPI_VictorSPX(Constants.drive.ArmMotorTwo);

  final MecanumDrive m_RobotDrive;

  public ArmSubsystem() {


    //leftMotors.setInverted(true);
    //m_RobotDrive = new DifferentialDrive(rightMotors, leftMotors)
    m_RobotDrive = new MecanumDrive (ArmMoterOne, ArmMotorTwo);

    addChild("Drive", m_RobotDrive);
    
  }

  public void drive(final double xSpeed, final double ySpeed, final double rotateValue) {
    m_RobotDrive.driveCartesian(xSpeed, ySpeed, rotateValue);
  }
}
