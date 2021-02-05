package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Pulley extends SubsystemBase
{
    public static double pulleyVoltage;
    private SpeedController pulleyMotor;
    private AnalogInput pulleyProximity;
    public static boolean sensorThree = false;
    private boolean timerStarted = false;
    private double startTime = 0;
    private double currTime = 0;
    private double thirdCount = 0;

    public Pulley(SpeedController pulleyMotor, AnalogInput pulleyProximity)
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