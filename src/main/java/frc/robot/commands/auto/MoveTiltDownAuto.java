package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveTiltDownAuto extends CommandBase
{
    private double startTime;

    public MoveTiltDownAuto()
    {
        addRequirements(RobotContainer.getTilt());
    }

    @Override
    public void initialize()
    {
        RobotContainer.getTilt().moveTilt(1.0);
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished()
    {
        return Timer.getFPGATimestamp() - startTime >= 3;
    }
    
    @Override
    public void end(boolean interrupted)
    {
        RobotContainer.getTilt().stop();
    }
}
