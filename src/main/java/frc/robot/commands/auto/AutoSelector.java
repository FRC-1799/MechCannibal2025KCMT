package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.auto.AutoRoutines.Auto_DumpandGo;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveBase;


public class AutoSelector {

  SendableChooser<Command> m_chooser = new SendableChooser<Command>();

  public AutoSelector(DriveBase Drive, ArmSubsystem arm){

    SmartDashboard.putData("autos: ", m_chooser);
    m_chooser.setDefaultOption("Dump go out", new Auto_DumpandGo(arm, Drive));
  }

  public Command getSelected() {
    return m_chooser.getSelected();
  }


}
