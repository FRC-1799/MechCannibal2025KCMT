package frc.robot;

import java.io.File;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.wrist;


public class SystemManager{
    public static DriveBase drivebase=null;
    public static wrist wrist = new wrist();
    /** Initializes the system manager along with all the systems on the robot */
    public static void SystemManagerInit(Robot robotIn){
        drivebase= new DriveBase();
        
    }

    /** Calls periodic on all the systems that do not inherit subsystem base. This function should be called in robot periodic */
    public static void periodic(){

    }

}