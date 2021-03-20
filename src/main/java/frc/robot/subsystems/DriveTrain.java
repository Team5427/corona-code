package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase
{
    private SpeedControllerGroup left, right;

    //ramping up 
    public static double rightSpeed, leftSpeed = 0;

    //ramping down
    public static double rightSpeedHigh, leftSpeedHigh = Constants.AUTONOMOUS_SPEED;

    private DifferentialDrive driveBase;

    public DriveTrain(SpeedControllerGroup left, SpeedControllerGroup right, DifferentialDrive driveBase)
    {
        this.left = left;
        this.right = right;
        this.driveBase = driveBase;
    }

    public SpeedControllerGroup getLeft()
    {
        return left;
    }

    public SpeedControllerGroup getRight()
    {
        return right;
    }

    public void tankDrive(double leftSpeed, double rightSpeed)
    {
        driveBase.tankDrive(leftSpeed, rightSpeed);
    }

    public void rampLeft(double speed)
    {
        int multiplier = (speed < 0)? -1: 1;
        left.set((leftSpeed) * multiplier);

        if(leftSpeed >= Math.abs(speed))
        {
            
        }
        else
        {
            leftSpeed += 0.0035;
        }
    }

    public void rampRight(double speed)
    {
        int multiplier = (speed < 0)? -1: 1;
        left.set((rightSpeed) * multiplier);

        if(rightSpeed >= Math.abs(speed))
        {
            
        }
        else
        {
            rightSpeed += 0.0035;
        }
    }

    public void rampDownLeft(double speed)
    {
        int multiplier = (speed < 0)? -1: 1;
        left.set((leftSpeedHigh) * multiplier);

        if(leftSpeedHigh == 0)
        {
            
        }
        else
        {
            leftSpeedHigh -= 0.05;
        }
    }

    public void rampDownRight(double speed)
    {
        int multiplier = (speed < 0)? -1: 1;
        right.set((rightSpeedHigh) * multiplier);

        if(rightSpeedHigh == 0)
        {
            
        }
        else
        {
            rightSpeedHigh -= 0.05;
        }
    }

    public void stop()
    {
        left.stopMotor();
        right.stopMotor();
    }

    public void takeJoystickInputs(Joystick joy)
    {
        driveBase.arcadeDrive(joy.getY(), -joy.getZ() * 0.65);
    }

    public double getAvgDistance()
    {
        return (RobotContainer.getEncLeft().getDistance() + RobotContainer.getEncRight().getDistance()) / 2;
    }

    public double getAvgRate()
    {
        return (RobotContainer.getEncLeft().getRate() + RobotContainer.getEncRight().getRate()) / 2;
    }

    @Override
    public void periodic()
    {}
    
}