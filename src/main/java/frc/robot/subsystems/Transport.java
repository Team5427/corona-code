package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.MoveTransportIntake;
import edu.wpi.first.wpilibj.AnalogInput;

public class Transport extends SubsystemBase
{
    public static double intakeVoltage;
    private AnalogInput intakeProximity;
    public static double transportVoltage;
    private AnalogInput transportProximity;

    private SpeedController transportMotor;  
    public static boolean firstSensor = false;
    public static boolean secondSensor = false;
    public static boolean shooterPressed = false;
   
    
    public Transport (SpeedController transportMotor, AnalogInput intakeProximity, AnalogInput transportProximity) 
    {
        this.intakeProximity =  intakeProximity;
        this.transportProximity = transportProximity;
        intakeVoltage = getDistance();
        transportVoltage = getDistanceTwo();
        this.transportMotor = transportMotor;

    }

    public void stop()
    {
        transportMotor.stopMotor();
    }

    public void moveTransport(double speed)
    {
        transportMotor.set(speed);
    }

    public double getDistance(){
        
        double distance = (1/intakeProximity.getVoltage())*6.1111126 * 1/2.54;
        return distance;
    }

    public double getDistanceTwo()
    {
        double distance = (1/transportProximity.getVoltage())*6.1111126 * 1/2.54;
        return distance;
    }

    public boolean getIntakeCovered()
    {
        return intakeVoltage < 1.7;
    }

    public boolean getTransportCovered()
    {
        return transportVoltage < 1.5;
    }
    
    @Override
    public void periodic()
    {
        intakeVoltage = getDistance();
        transportVoltage = getDistanceTwo();

        if(getIntakeCovered())
        {
            new MoveTransportIntake();            
        }

    }

}