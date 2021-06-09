package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class ShootAll extends CommandBase
{
    private double timeBetweenCells, endTime;
    private double startTime;
    private boolean timerStarted, timerComplete, exitTimerStarted, exitTimerComplete, shooting;
    private int pulleyStopCount;
    private double shooterSpeed;

    public ShootAll(double timeBetweenCells, double endTime, double shooterSpeed)
    {
        this.shooterSpeed = shooterSpeed;
        this.timeBetweenCells = timeBetweenCells;
        this.endTime = endTime;
        addRequirements(RobotContainer.getTransport(), RobotContainer.getShooter());
    }

    @Override
    public void initialize()
    {
        
        RobotContainer.getShooter().moveShooter(shooterSpeed);
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_TELEOP_SPEED);
        startTime = 0;
        timerStarted = false;
        timerComplete = false;
        exitTimerComplete = false;
        exitTimerStarted = false;
        pulleyStopCount = 0;
    }

    @Override
    public void execute()
    {
        SmartDashboard.putBoolean("Timer started", timerStarted);
        if(!timerStarted && (RobotContainer.getTransport().getTransportCovered() || RobotContainer.getPulley().getPulleyCovered()))
        {
            startTime = Timer.getFPGATimestamp();
            timerStarted = true;
            exitTimerStarted = false;
        }
        else
        {
            RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_TELEOP_SPEED);
        }

        if(timerStarted)
        {
            timerComplete = Timer.getFPGATimestamp() - startTime >= timeBetweenCells;
            // if(!shooting && !timerComplete && RobotContainer.getPulley().getPulleyCovered())
            // {
            //     RobotContainer.getTransport().stop();
            //     System.out.println("Waiting");
            // }
            if(timerComplete && RobotContainer.getPulley().getPulleyCovered())
            {
                System.out.println("Timer Complete");
                RobotContainer.getPulley().movePulley(Constants.PULLEY_TELEOP_SPEED);
                RobotContainer.getTransport().stop();
            }
            else if(timerComplete && !RobotContainer.getPulley().getPulleyCovered()){
                System.out.println("Shooting");
                timerStarted = false;
                timerComplete = false;
                exitTimerComplete = false;
                exitTimerStarted = true;
                startTime = Timer.getFPGATimestamp();
                RobotContainer.getPulley().stop();
            }
        }
    }

    @Override
    public boolean isFinished()
    {
        // System.out.println("Exit Timer started: " + exitTimerStarted);
        // System.out.println("Timer started: " + timerStarted);
        if(RobotContainer.getTransport().getTransportCovered() || RobotContainer.getPulley().getPulleyCovered())
        {
            return false;
        }
        if(exitTimerStarted && !timerStarted)
        {
            System.out.println("Stopping systems");
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
        System.out.println("done:)");
    }
}
