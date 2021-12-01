package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveTiltAuto extends CommandBase
{
    private double speed;
    private double startTime;
    private double currTime;
    private boolean isDown = false;

    public MoveTiltAuto(double speed)
    {
        addRequirements(RobotContainer.getTilt());
        this.speed = speed;
    }

    @Override
    public void initialize()
    {
        if(RobotContainer.getTilt().getLimit())
        {
            RobotContainer.getTilt().moveTilt(speed);
            isDown = true;
            startTime = currTime = Timer.getFPGATimestamp();
        }
        else
        {
            RobotContainer.getTilt().moveTilt(speed);
            isDown = false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getTilt().stop();
    }

    @Override
    public void execute() 
    {
        if(!isDown)
        {
            if(RobotContainer.getTilt().getLimit())
            {
               RobotContainer.getTilt().moveTilt(0);
            }
        }
        else
        {
            currTime = Timer.getFPGATimestamp();
        }
    }

    @Override
    public boolean isFinished()
    {
        if(!isDown)
        {
            return RobotContainer.getTilt().getLimit();
        }
        else
        {
            return (currTime - startTime) >= 3.25;
        }
    }
}