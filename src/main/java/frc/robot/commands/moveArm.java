package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.arm;
import frc.robot.subsystems.ArmSubsystem;
//import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;

public class moveArm extends RunCommand {

  private final ArmSubsystem arm;

  public moveArm(ArmSubsystem arm, double speed) {
    super(
      ()->{
        arm.move(speed);
      },
     arm
     );
     this.arm = arm;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    arm.move(0);
  }
}