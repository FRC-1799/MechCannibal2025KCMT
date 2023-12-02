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
  public boolean isGoingUp = false;
  public boolean isActive = false;
  final MotorControllerGroup armMotors = new MotorControllerGroup(armOne, armTwo);


  public void move() {
    printInfo();
      if(isActive){
        if (!isGoingUp){
          if (!topSwitch.get()) {
            isGoingUp = true;
            armMotors.set(Constants.arm.ArmDown);
            
            // We are going up and top limit is tripped so stop
          }
          else{
            armMotors.set(Constants.arm.ArmUp);
          }
        }
        else{
          if (!bottomSwitch.get() && isGoingUp) {
            // We are going down and bottom limit is tripped so stop
          
            armMotors.set(0);
            isGoingUp = false;
            isActive = false;
            
        }
          else{
            armMotors.set(Constants.arm.ArmDown);
        }
      }
    }
  }
  // public void setActive() {
  //   isActive = true;
  // }
  private void printInfo(){
    SmartDashboard.putBoolean("isup", isGoingUp);
    SmartDashboard.putBoolean("topSwitch", topSwitch.get());
    SmartDashboard.putBoolean("botttomSwitch", bottomSwitch.get());
    SmartDashboard.putBoolean("isActive", isActive);
  }

}
  // public void move2(DigitalInput endSwitch, MotorControllerGroup motor){
  //   while (endSwitch.get()){
  //     motor.set(constants.arm.)
  //   }
  // }
    // SmartDashboard.putBoolean("isUp", isUp);
    // while ( topSwitch.get() || bottomSwitch.get()){
    //   if (isUp){
    //       armMotors.set(Constants.arm.ArmDown);
          
    //   }
    //   else{
    //     armMotors.set(Constants.arm.ArmUp);
        

    //   }                                                       
    // }
    // armMotors.set(0);
    // if (isUp){
    //   isUp=false;
    // }
    // else{
    //   isUp=true;
    // }


 


