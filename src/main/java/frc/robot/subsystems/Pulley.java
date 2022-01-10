package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pulley extends SubsystemBase
{
    public static double pulleyVoltage;
    private MotorController pulleyMotor;
    private AnalogInput pulleyProximity;
    public static boolean sensorThree = false;
    public static double startTime = 0;
    public static double currTime = 0;

    public Pulley(MotorController pulleyMotor, AnalogInput pulleyProximity)
    {
        this.pulleyMotor = pulleyMotor;
        this.pulleyProximity = pulleyProximity;
        pulleyVoltage = getDistance();
    }

    public void movePulley(double speed)
    {
        pulleyMotor.set(speed);
    }

    public void stop()
    {
        pulleyMotor.stopMotor();
    }

    public double getDistance()
    {
        double distance = (1/pulleyProximity.getVoltage())*6.1111126 * 1/2.54;
        return distance;
    }

    public boolean getPulleyCovered()
    {
        return pulleyVoltage < 3.0;
    }

    @Override
    public void periodic()
    {
        pulleyVoltage = getDistance();
    }
}