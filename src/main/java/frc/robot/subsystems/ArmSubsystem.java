package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class ArmSubsystem extends SubsystemBase {

  public final WPI_VictorSPX armOne = new WPI_VictorSPX(Constants.arm.ArmOnePort);
  public final WPI_VictorSPX armTwo = new WPI_VictorSPX(Constants.arm.ArmTwoPort);

  final MotorControllerGroup armMotors = new MotorControllerGroup(armOne, armTwo);

  public void move(final boolean isUp) {

    if (isUp){
        armMotors.set(Constants.arm.ArmUp);
    }
    else{
        armMotors.set(Constants.arm.ArmDown);
    }

  }
}