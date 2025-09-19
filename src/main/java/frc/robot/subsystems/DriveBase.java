package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants;
import com.revrobotics.spark.SparkMax;
import com.studica.frc.AHRS;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Radians;

import com.revrobotics.spark.SparkLowLevel.MotorType;


public class DriveBase extends SubsystemBase {
  // new CANSparkMax(Constats.drive.lt, MotorType.kBrushless);

  // left side
  public final SparkMax LeftFront = new SparkMax(Constants.drive.LF, MotorType.kBrushless);
  public final SparkMax LeftBack = new SparkMax(Constants.drive.LB,  MotorType.kBrushless);

  private final AHRS gyro = new AHRS(AHRS.NavXComType.kMXP_SPI);
  //right side
  public final SparkMax RightFront = new SparkMax(Constants.drive.RF,  MotorType.kBrushless);
  public final SparkMax RightBack = new SparkMax(Constants.drive.RB,  MotorType.kBrushless);

  

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

  public void driveFeild(double xSpeed, double ySpeed, double rotateValue){
    final Angle yaw = Degrees.of(gyro.getYaw());
    drive(Math.cos(yaw.in(Radians))*xSpeed, Math.sin(yaw.in(Radians))*ySpeed, rotateValue);
  }
}
