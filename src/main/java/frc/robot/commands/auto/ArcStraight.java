package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class ArcStraight extends CommandBase
{

    private double speed, angle, endAngle;

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
