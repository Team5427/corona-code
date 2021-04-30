package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class ShootAll extends CommandBase
{
    private double timeBetweenCells, endTime;
    private double startTime;
    private boolean timerStarted, timerComplete, exitTimerStarted, exitTimerComplete;

    public ShootAll(double timeBetweenCells, double endTime)
    {
        this.timeBetweenCells = timeBetweenCells;
        this.endTime = endTime;
        startTime = 0;
        timerStarted = false;
        timerComplete = false;
        exitTimerComplete = false;
        exitTimerStarted = false;
        addRequirements(RobotContainer.getTransport(), RobotContainer.getShooter());
    }

    @Override
    public void initialize()
    {
        RobotContainer.getShooter().moveShooter(Constants.SHOOTER_TELEOP_SPEED);
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_TELEOP_SPEED);
    }

    @Override
    public void execute()
    {
        if(!timerStarted && RobotContainer.getTransport().getTransportCovered())
        {
            startTime = Timer.getFPGATimestamp();
            timerStarted = true;
        }

        if(timerStarted)
        {
            timerComplete = Timer.getFPGATimestamp() - startTime >= timeBetweenCells;
            if(!timerComplete && RobotContainer.getPulley().getPulleyCovered())
            {
                RobotContainer.getTransport().stop();
            }
            else if(timerComplete && RobotContainer.getPulley().getPulleyCovered())
            {
                RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_TELEOP_SPEED);
                RobotContainer.getPulley().movePulley(Constants.PULLEY_TELEOP_SPEED);
                timerComplete = false;
                timerStarted = false;
                exitTimerStarted = true;
                startTime = Timer.getFPGATimestamp();
            }
        }
    }

    @Override
    public boolean isFinished()
    {
        if(exitTimerStarted)
        {
            exitTimerComplete = Timer.getFPGATimestamp() - startTime >= endTime;
            return exitTimerComplete;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        RobotContainer.getTransport().stop();
        RobotContainer.getShooter().stop();
        RobotContainer.getPulley().stop();
    }
}
