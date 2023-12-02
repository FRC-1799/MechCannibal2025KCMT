
package frc.robot.commands.auto.AutoRoutines;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.commands.auto.AutoFunctions.DriveStraight;
import frc.robot.Constants;
import frc.robot.commands.ArmMove;
import frc.robot.subsystems.ArmSubsystem;

public class Auto_DumpandGo extends SequentialCommandGroup {
  /*
   * pseudoCode:
   * humans will position robot
   * milk crate will tip, releasing cube into lowest zone
   * robot drives forward, getting more auto points
   */

  // Subsystem to Dump Cargo then go forward over charge station
  // and then back up onto charge system to attempt balance
  public Auto_DumpandGo(ArmSubsystem arm, DriveBase drive, DriveStraight execute) {
    super(
      new WaitCommand(1),
      //dump game piece
    new ArmMove(arm, null),
        /*place holder for now 
         *Owen will fix ArmMove once he does make sure to come back
         *fix line 28 so its not bolded
         */
      
      new WaitCommand(5),

      new DriveStraight(drive, 3.0, Constants.auto.fwdSpeed, false)
      
      
    );
  }
}