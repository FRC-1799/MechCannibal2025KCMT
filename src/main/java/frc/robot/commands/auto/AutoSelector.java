package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Gyro;
// import frc.robot.commands.auto.AutoRoutines.*;
public class AutoSelector {

  SendableChooser<Command> m_chooser = new SendableChooser<Command>();

  public AutoSelector(DriveBase Drive){
    SmartDashboard.putData("autos: ", m_chooser);
  }

  public Command getSelected(){
    return m_chooser.getSelected();
  }


}
