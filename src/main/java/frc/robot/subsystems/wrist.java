package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.wristConstants;

import static edu.wpi.first.units.Units.Degrees;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;


public class wrist extends SubsystemBase{


    
    protected double goal;

    protected boolean isOff=false;
    protected SparkFlex wristMotor = new SparkFlex(wristConstants.motorID, MotorType.kBrushless);
    //SparkMax emotionalSupportSparkMax= new SparkMax(wristConstants.throughBoreID, MotorType.kBrushless);
    protected SparkAbsoluteEncoder wristEncoder = wristMotor.getAbsoluteEncoder();
    protected PIDController wristPID = new PIDController(wristConstants.wristPID.kP, wristConstants.wristPID.kI, wristConstants.wristPID.kD);
    

    public wrist(){
        wristPID.setTolerance(wristConstants.tolerance);
        
        
        
    }

    @Override
    public void periodic(){

  

        

        wristPID.setSetpoint(goal);
        SmartDashboard.putNumber("Wrist goal", goal);
        SmartDashboard.putNumber("Wrist location", getCurrentLocation());

        double speed = wristPID.calculate(getCurrentLocation());
        speed = speed + wristConstants.g;

        SmartDashboard.putNumber("wristSpeed", speed);
        SmartDashboard.putNumber("wristError", Math.abs(goal-getCurrentLocation()));

        SmartDashboard.putNumber("wristEncoderRaw", wristEncoder.getPosition());
        SmartDashboard.putBoolean("WristIsOn", !isOff);

        if (!isOff){
            wristMotor.set(speed);
        }
        
    }

    public double getCurrentLocation() {
        return (wristEncoder.getPosition()*360 + wristConstants.encoderOffset)%360;
    }

    public void setGoal(Angle goalToSet){
        if (goalToSet.in(Degrees)>wristConstants.minAngle.in(Degrees) && goalToSet.in(Degrees)<wristConstants.maxAngle.in(Degrees)){
            goal=goalToSet.in(Degrees);
        }
        else{
            throw new Error(
                "Attempted to set a wrist goal of " + goal 
                + "Which is not within the allowed range " 
                + wristConstants.minAngle.in(Degrees) + " to " 
                + wristConstants.maxAngle.in(Degrees));
        }
    }

    public Angle getGoal(){
        return Degrees.of(goal);
    }

    public double getGoalDegrees(){
        return goal;
    }

    public Angle getPose(){
        return Degrees.of(getCurrentLocation());
    }
    
    public void turnOff(){
        isOff=true;
    }

    public void turnOn(){
        isOff=false;
    }


}

