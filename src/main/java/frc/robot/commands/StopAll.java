package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class StopAll extends CommandBase
{
    public StopAll()
    {
        addRequirements(RobotContainer.getShooter(), RobotContainer.getTransport(), RobotContainer.getPulley());
    }

    @Override
    public void initialize()
    {
        RobotContainer.getShooter().stop();
        RobotContainer.getTransport().stop();
        RobotContainer.getPulley().stop();
    }

    @Override
    public void execute()
    {

    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

    @Override
    public void end(boolean interrupted)
    {
        
    }
    
    
}
