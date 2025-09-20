package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  SendableChooser<Angle> rotationChooser=new SendableChooser<>();


  public DriveBase() {


    //leftMotors.setInverted(true);
    //m_RobotDrive = new DifferentialDrive(rightMotors, leftMotors)
    
    m_RobotDrive = new MecanumDrive(LeftFront, LeftBack, RightFront, RightBack);

    rotationChooser.setDefaultOption("0", Degrees.of(0));
    rotationChooser.addOption("45", Degrees.of(45));
    rotationChooser.addOption("90", Degrees.of(90));
    rotationChooser.addOption("135", Degrees.of(135));
    rotationChooser.addOption("180", Degrees.of(180));
    rotationChooser.addOption("225", Degrees.of(225));
    rotationChooser.addOption("270", Degrees.of(270));
    rotationChooser.addOption("305", Degrees.of(305));
    rotationChooser.onChange(this::setAngle);
    addChild("Drive", m_RobotDrive);
    SmartDashboard.putData("AngleOffset", rotationChooser);
    
  }

  public void drive(final double xSpeed, final double ySpeed, final double rotateValue) {
    m_RobotDrive.driveCartesian(xSpeed, ySpeed, rotateValue);
  }

  protected void setAngle(Angle toSet){
    gyro.setAngleAdjustment(toSet.in(Degrees));
  }

  public void driveField(double xSpeed, double ySpeed, double rotateValue){
    final Angle yaw = Degrees.of(gyro.getYaw());
    drive(Math.cos(yaw.in(Radians))*xSpeed, Math.sin(yaw.in(Radians))*ySpeed, rotateValue);
  }
}
