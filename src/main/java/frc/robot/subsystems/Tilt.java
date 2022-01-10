package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Tilt extends SubsystemBase
{ 
    private MotorController tiltMotor;  
    private DigitalInput tiltLimit;

    public Tilt(MotorController tiltMotor, DigitalInput tiltLimit) 
    {
         this.tiltMotor = tiltMotor;
         this.tiltLimit = tiltLimit;
    }
    public void moveTilt(double speed)
    {
        tiltMotor.set(speed);
    }
    public void stop()
    {
       tiltMotor.stopMotor();
    }

    public boolean getLimit() 
    {
        return !tiltLimit.get();
    }

    @Override
    public void periodic() 
    {
        
    }
}