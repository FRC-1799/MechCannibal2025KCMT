package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;

/**
 * Have the robot drive tank style.
 */
public class ArmMove extends RunCommand {
  /**
   * Creates a new ArcadeDrive command.
   *
   * @param left       The control input for the left side of the drive
   * @param right      The control input for the right sight of the drive
   * @param driveSubsystem The driveSubsystem subsystem to drive
   */
  public ArmMove(final ArmSubsystem arm, final DoubleSupplier output) {
      super(
        ()->{
          
            arm.move(MathUtil.applyDeadband(output.getAsDouble(), 0.1));
        },
        arm
        );
    }
  }
