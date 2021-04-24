package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

/**
 * Command that uses arcadeDrive() for a given arc and speed until it reaches a specified angle. 
 */
public class ArcStraight extends CommandBase
{
    /**
     * The linear speed at which the arc will run for. 
     */
    private double speed; 
    private double angle;
    private double endAngle;

    public ArcStraight(double speed, double angle, double endAngle)
    {
        addRequirements(RobotContainer.getDriveTrain());
        this.speed = speed;
        this.angle = angle;
        this.endAngle = endAngle;
    }

    @Override
    public void initialize()
    {
        RobotContainer.getDriveTrain().getDriveBase().arcadeDrive(speed, angle);
    }

    @Override
    public void execute()
    {
        RobotContainer.getDriveTrain().getDriveBase().arcadeDrive(speed, angle);
    }

    @Override
    public boolean isFinished()
    {
        return Math.abs(RobotContainer.getAHRS().getYaw() - endAngle) <= 7.4;
    }

    @Override
    public void end(boolean interrupted)
    {
        SmartDashboard.putNumber("Angle b4 straight", RobotContainer.getAHRS().getYaw());
        RobotContainer.getDriveTrain().stop();
    }


    
}
