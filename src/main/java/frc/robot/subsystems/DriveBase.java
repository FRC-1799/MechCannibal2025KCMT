package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveBase extends SubsystemBase {
 // new CANSparkMax(Constats.drive.lt, MotorType.kBrushless);

// left side
public final WPI_TalonSRX LF = new WPI_TalonSRX(Constants.drive.LF);
public final WPI_TalonSRX LB = new WPI_TalonSRX(Constants.drive.LB);


//right side
public final WPI_TalonSRX RF = new WPI_TalonSRX(Constants.drive.RF);
public final WPI_TalonSRX RB = new WPI_TalonSRX(Constants.drive.RB);



  final MecanumDrive m_RobotDrive;

  public DriveBase() {


    //leftMotors.setInverted(true);
    //m_RobotDrive = new DifferentialDrive(rightMotors, leftMotors)
    m_RobotDrive = new MecanumDrive(LF, LB, RF, RB);

    addChild("Drive", m_RobotDrive);
    
  }

  public void drive(final double xSpeed, final double ySpeed, final double rotateValue) {
    m_RobotDrive.driveCartesian(xSpeed, ySpeed, rotateValue);
  }
}
