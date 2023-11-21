package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class DriveBase extends SubsystemBase {
  // new CANSparkMax(Constats.drive.lt, MotorType.kBrushless);

  // left side
  public final WPI_VictorSPX LeftFront = new WPI_VictorSPX(Constants.drive.LF);
  public final WPI_VictorSPX LeftBack = new WPI_VictorSPX(Constants.drive.LB);


  //right side
  public final WPI_VictorSPX RightFront = new WPI_VictorSPX(Constants.drive.RF);
  public final WPI_VictorSPX RightBack = new WPI_VictorSPX(Constants.drive.RB);



  final MecanumDrive m_RobotDrive;

  public DriveBase() {


    //leftMotors.setInverted(true);
    //m_RobotDrive = new DifferentialDrive(rightMotors, leftMotors)
    m_RobotDrive = new MecanumDrive(LeftFront, LeftBack, RightFront, RightBack);

    addChild("Drive", m_RobotDrive);
    
  }

  public void drive(final double xSpeed, final double ySpeed, final double rotateValue) {
    m_RobotDrive.driveCartesian(xSpeed, ySpeed, rotateValue);
  }
}
