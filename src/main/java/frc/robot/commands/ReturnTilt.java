package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ReturnTilt extends CommandBase
{
    private double speed;
    private double startTime;
    private double currTime;
    private boolean isUp = false;

    public ReturnTilt(double speed)
    {
        addRequirements(RobotContainer.getTilt());
        this.speed = speed;
    }

    @Override
    public void initialize()
    {
        if(!RobotContainer.getTilt().getLimit())
        {
            RobotContainer.getTilt().moveTilt(0);
            isUp = true;
            startTime = currTime = Timer.getFPGATimestamp();
        }
        else
        {
            RobotContainer.getTilt().moveTilt(-speed);
            isUp = false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getTilt().stop();
    }

    @Override
    public void execute() 
    {
        if(!RobotContainer.getTilt().getLimit())
        {
            RobotContainer.getTilt().moveTilt(0);
            isUp = true;
            //startTime = currTime = Timer.getFPGATimestamp();
        }
        else
        {
            RobotContainer.getTilt().moveTilt(-speed);
            isUp = false;
        }
    }
    

    @Override
    public boolean isFinished()
    {
        if(!isUp)
        {
            return !RobotContainer.getTilt().getLimit();
        }
        else
        {
            return (currTime - startTime) >= 4.25;
        }
    }
}
