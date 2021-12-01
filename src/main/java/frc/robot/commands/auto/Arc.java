package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

/**
 * Command that uses arcadeDrive() for a given arc, speed, and amount of time. 
 */
public class Arc extends CommandBase
{
    /**
     * The linear speed that the arc will be run at. 
     */
    private double speed;

    /**
     * The arc value that the arc will be run at. 
     */
    private double angle;

    /**
     * The timer (given by Timer.getFPGATimestamp()) that the command starts at. 
     */
    private double startTime;

    /**
     * The total intended amount of time that the command should run for. 
     */
    private double time;

    /**
     * initializes the passed parameters of an Arc instance
     * @param speed the linear speed that the arc will run at. 
     * @param angle the arc value that the arc will run at. 
     * @param time the amount of time that the arc will run for. 
     */
    public Arc(double speed, double angle, double time)
    {
        addRequirements(RobotContainer.getDriveTrain());
        this.speed = speed;
        this.angle = angle;
        this.time = time;
    }

    /**
     * Runs once at the beginning of the Arc command. 
     */
    @Override
    public void initialize()
    {
        //uses arcadeDrive() method with given parameters
        RobotContainer.getDriveTrain().getDriveBase().arcadeDrive(speed, angle);
        startTime = Timer.getFPGATimestamp();
    }

    /**
     * Runs every 20 ms of the command. 
     */
    @Override
    public void execute()
    {
        RobotContainer.getDriveTrain().getDriveBase().arcadeDrive(speed, angle);
    }

    /**
     * Runs every 20 ms to access if the command should be stopped. 
     */
    @Override
    public boolean isFinished()
    {
        //stops if the intended amount of time has elapsed since the start of the command. 
        return Timer.getFPGATimestamp() - startTime >= time;
    }

    /**
     * Runs after isFinished() returned true
     */
    @Override
    public void end(boolean interrupted)
    {
        //stops the drive train, stopping the arc
        RobotContainer.getDriveTrain().stop();
    }


    
}
