package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveBase extends SubsystemBase {
 // new CANSparkMax(Constats.drive.lt, MotorType.kBrushless);

// left side
public final WPI_VictorSPX LF = new WPI_VictorSPX(Constants.drive.LF);
public final WPI_VictorSPX LB = new WPI_VictorSPX(Constants.drive.LB);


//right side
public final WPI_VictorSPX RF = new WPI_VictorSPX(Constants.drive.RF);
public final WPI_VictorSPX RB = new WPI_VictorSPX(Constants.drive.RB);



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
