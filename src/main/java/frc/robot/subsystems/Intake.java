package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;;
public class Intake extends SubsystemBase{
    protected enum intakeState{
        Resting, Intaking, Outtaking
    }

    protected intakeState state=intakeState.Resting;
    
    protected SparkMax intakeMotor = new SparkMax(IntakeConstants.motorID, MotorType.kBrushed);
    protected DigitalInput pieceSensor = new DigitalInput(IntakeConstants.limitSwitchID);


    @Override
    public void periodic(){
        SmartDashboard.putBoolean("Intake/isIntaking", state==intakeState.Intaking);
        SmartDashboard.putBoolean("Intake/isOuttaking", state==intakeState.Outtaking);
        SmartDashboard.putBoolean("Intake/hasPiece", hasPiece());
        
        if (state==intakeState.Intaking){
            intakeMotor.set(IntakeConstants.intakeSpeed);
            if (hasPiece()){
                state=intakeState.Resting;
            }
        }

        else if(state==intakeState.Outtaking){
            intakeMotor.set(IntakeConstants.outtakeSpeed);
            if (!hasPiece()){
                state=intakeState.Outtaking;
            }
        }
        else{
            intakeMotor.set(0);
        }

        
    }


    /**
     * Sets the intake to intake mode.
     * @return will return wether or not the state was successfully changes. The state change will fail if there is a piece in the intake. 
     */
    public boolean startIntake(){
        if (hasPiece())return false;

        state=intakeState.Intaking;
        return true;
    }

    /**
     * Sets the intake to outtake mode.
     * @return will return wether or not the state was successfully changes. The state change will fail if there is no piece in the intake. 
     */
    public boolean startOuttake(){
        if (!hasPiece()) return false;
        state=intakeState.Outtaking;
        return false;
    }

    /**Sets the intake to resting(off) mode. */
    public void rest(){
        state=intakeState.Resting;
    }

    /**@return returns wether the intake detects a piece.*/
    public boolean hasPiece(){
        return pieceSensor.get();
    }
}
