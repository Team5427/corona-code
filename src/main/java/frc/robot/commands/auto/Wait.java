package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Wait extends CommandBase
{
    private double startTime;
    private double time;

    public Wait(double time)
    {
        startTime = 0;
        this.time = time;
    }

    @Override
    public void initialize()
    {
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute()
    {

    }

    @Override
    public boolean isFinished()
    {
        return Timer.getFPGATimestamp() - startTime >= time;
    }

    @Override
    public void end(boolean interrupted)
    {
        startTime = Timer.getFPGATimestamp();
        time = 0;
    }
    
}
