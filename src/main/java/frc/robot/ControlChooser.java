package frc.robot;


import java.util.function.Consumer;


import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.BetterTrigger;

public class ControlChooser {

    SendableChooser<EventLoop> chooser=new SendableChooser<>();
    Consumer<ControlChooser>current;
    
    CommandXboxController xbox1;
    CommandXboxController xbox2;
    
    EventLoop controlLoop=CommandScheduler.getInstance().getDefaultButtonLoop();
    

    
    /**creates a control chooser */
    ControlChooser(){
        
        xbox1=new CommandXboxController(Constants.MOVEMENT_JOYSTICK);
        xbox2=new CommandXboxController(Constants.MANIPULATOR_JOYSTICK);

        chooser.setDefaultOption("default", CommandScheduler.getInstance().getDefaultButtonLoop());

        if (!RobotBase.isReal()){
            //for schemes too unsafe to run on the real bot
        }


        chooser.addOption("testControl", getTestControl());

        
        
        chooser.onChange((EventLoop scheme)->{changeControl(scheme);});
        changeControl(chooser.getSelected());
        
        SmartDashboard.putData("Control chooser", chooser);
       
    }


    /**
     * changes the control scheme to the scheme specified
     * @param scheme the scheme to change too
     */
    public void changeControl(EventLoop scheme){
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().setActiveButtonLoop(scheme);

    }

    /**restarts the control chooser */
    public void restart(){
        changeControl(chooser.getSelected());
    }

    

    //returns an xbox controllers pov buttons in terms of degrees
    public static int getPOVForTest(CommandXboxController controller){
        for (int pov: Constants.OperatorConstants.supportedPOV){
            if (controller.pov(pov).getAsBoolean()){
                return pov;
            }
        } 
        return 0;

    }

    /**
     * configures a default command that can run on a loop.
     * @param defaultCommand the command to make the default
     * @param subsystem the subsystem this command is the default for
     * @param loop the loop to attach the default command too
     */
    public static void setDefaultCommand(Command defaultCommand, Subsystem subsystem, EventLoop loop){
        new BetterTrigger(loop, ()->((CommandScheduler.getInstance().requiring(subsystem)==null||CommandScheduler.getInstance().requiring(subsystem)==defaultCommand))).whileTrue(defaultCommand);
    }


    /**@return a new test control loop*/
    private EventLoop getTestControl(){
        EventLoop toReturn = new EventLoop();

        return toReturn;
    }



}