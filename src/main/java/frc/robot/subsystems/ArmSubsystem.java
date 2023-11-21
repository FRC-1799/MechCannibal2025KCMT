package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class ArmSubsystem extends SubsystemBase {

  public final WPI_VictorSPX armOne = new WPI_VictorSPX(Constants.arm.ArmOnePort);
  public final WPI_VictorSPX armTwo = new WPI_VictorSPX(Constants.arm.ArmTwoPort);
  public final DigitalInput topSwitch = new DigitalInput(Constants.arm.topSwitch);
  public final DigitalInput bottomSwitch = new DigitalInput(Constants.arm.bottomSwitch);
  public boolean isUp = false;
  final MotorControllerGroup armMotors = new MotorControllerGroup(armOne, armTwo);

  public void move() {
    SmartDashboard.putBoolean("isUp", isUp);
    if (topSwitch.get() || bottomSwitch.get()){
        armMotors.set(0);
    }
    else if (isUp){
        armMotors.set(Constants.arm.ArmDown);
        isUp=false;

    }
    else{
      armMotors.set(Constants.arm.ArmUp);
      isUp=true;

    }

  }


}