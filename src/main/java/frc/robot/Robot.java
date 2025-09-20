// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.PubSubOption;
import edu.wpi.first.networktables.StructArrayPublisher;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArcadeDrive;

import static edu.wpi.first.units.Units.Seconds;

import java.io.File;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import com.pathplanner.lib.commands.FollowPathCommand;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the TimedRobot documentation. If you change the name of this class or the package after creating this
 * project, you must also update the build.gradle file in the project.
 */
public class Robot extends TimedRobot{

    private static Robot   instance;
    ControlChooser controlChooser;
    int heartBeat=0;
    private Timer disabledTimer;
    SendableChooser<Command> autoChooser=new SendableChooser<>();


    


    public Robot(){
      instance = this;
      SystemManager.SystemManagerInit(instance);


      autoChooser.setDefaultOption("You spin me right round like a record baby right round right round", new ArcadeDrive(SystemManager.drive, ()->0, ()->0, ()->1, ()->false).withTimeout(Seconds.of(15)));
      autoChooser.addOption("Its Mobilitin Time", new ArcadeDrive(SystemManager.drive, ()->0.2, ()->0, ()->0, ()->false).withTimeout(1));
      autoChooser.addOption("THE ONE PIECE IS REAL", 
        new ArcadeDrive(SystemManager.drive, ()->0.5, ()->0, ()->1, ()->false).withTimeout(1)
        .andThen(new InstantCommand(()->SystemManager.wrist.setGoal(Constants.wristConstants.l1EncoderVal)))
        .andThen(new WaitCommand(3))
        .andThen(new InstantCommand(()->SystemManager.intake.startOuttake())));

        SmartDashboard.putData(autoChooser);

        if (!RobotBase.isReal()){
            //for schemes too unsafe to run on the real bot
        }


    }

    public static Robot getInstance(){
      return instance;
    }

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    @Override
    public void robotInit(){
      // Create a timer to disable motor brake a few seconds after disable.  This will let the robot stop
      // immediately when disabled, but then also let it be pushed more 
      disabledTimer = new Timer();
      FollowPathCommand.warmupCommand().schedule();
      this.controlChooser=new ControlChooser();
      DriverStation.silenceJoystickConnectionWarning(true);
      DataLogManager.start();
      
    }

    /**
     * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics that you want ran
     * during disabled, autonomous, telioperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic()
    {
      // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
      // commands, running already-scheduled commands, removing finished or interrupted commands,
      // and running subsystem periodic() methods.  This must be called from the robot's periodic
      // block in order for anything in the Command-based framework to work.
      CommandScheduler.getInstance().run();
      SystemManager.periodic();
      heartBeat++;
      SmartDashboard.putNumber("heartbeat", heartBeat);  

    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit(){
      disabledTimer.reset();
      disabledTimer.start();

    }

    @Override
    public void disabledPeriodic()
    {

    }

    /**
     * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit()
    {

      autoChooser.getSelected().schedule();
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic()
    {
    }

    @Override
    public void teleopInit()
    {
     

      controlChooser.restart();
      

    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic()
    {
    }

    @Override
    public void testInit()
    {
      // Cancels all running commands at the start of test mode.
      CommandScheduler.getInstance().cancelAll();

    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic()
    {
    }



    /**
     * This function is called once when the robot is first started up.
     */


    /**
     * This function is called periodically whilst in simulation.
     */



}