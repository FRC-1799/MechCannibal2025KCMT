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
  public boolean isActive =false;
  final MotorControllerGroup armMotors = new MotorControllerGroup(armOne, armTwo);


  public void move() {
      SmartDashboard.putBoolean("isup", isUp);
      SmartDashboard.putBoolean("topSwitch", topSwitch.get());
      SmartDashboard.putBoolean("bottomSwitch", bottomSwitch.get());
      SmartDashboard.putBoolean("bottomSwitch", isActive);

      if (isActive){
        if (!topSwitch.get()) {
            isUp=true;
            
            // We are going up and top limit is tripped so stop
            armMotors.set(0);
        } 
        if(!isUp) {
          // We are going up but top limit is not tripped so go at commanded speed
          
          armMotors.set(Constants.arm.ArmUp);
        }
      }
    

        
      
      if (!bottomSwitch.get() && isUp) {
          // We are going down and bottom limit is tripped so stop
          armMotors.set(0);
          isUp=false;
          isActive=false;
      }
      else if(isUp){
        armMotors.set(Constants.arm.ArmDown);
    }

  }
  public void setActive(){
    isActive=true;
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


 


