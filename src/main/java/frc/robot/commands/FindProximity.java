package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class FindProximity extends CommandBase
{
    double lowest;
    double highest;
    double startTime;
    double runTime = 10;
    public FindProximity()
    {
    startTime = Timer.getFPGATimestamp();
    runTime = 10;
    lowest = RobotContainer.getPulley().getDistance(); 
    highest = 0;  
    }

    public void initialize()
    {
        startTime = Timer.getFPGATimestamp();
    }

    public void execute()
    {
        if(RobotContainer.getPulley().getDistance() < lowest){
            lowest = RobotContainer.getPulley().getDistance();
        }
        if(RobotContainer.getPulley().getDistance() > highest){
            highest = RobotContainer.getPulley().getDistance();
        }
    }

    public boolean isFinished()
    {
        return(Timer.getFPGATimestamp() > startTime + runTime);
    }
    public void end(boolean interrupted)
    {
        Robot.lowest = this.lowest;
        Robot.highest = this.highest;
    }
}


