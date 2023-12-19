package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimitSwitch extends SubsystemBase{

    private DigitalInput limit;

    public LimitSwitch(int index){
        limit = new DigitalInput(index);
    }

    public Boolean getVal(){
        return limit.get();
    }
    public boolean isOk(){
        //needs to be added later. should use the fact that the input pins will always have one active
        return true;
    }
}