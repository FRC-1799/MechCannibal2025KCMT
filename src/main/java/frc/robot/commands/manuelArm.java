package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.arm;
import frc.robot.subsystems.ArmSubsystem;
//import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;

public class manuelArm extends RunCommand {

  private final ArmSubsystem arm;

  public manuelArm(ArmSubsystem arm, double speed) {
    super(
      ()->{
        arm.moveManuel(speed);
      },
     arm
     );
     this.arm = arm;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    arm.moveManuel(0);
  }
}