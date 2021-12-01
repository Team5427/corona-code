package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveTransport extends CommandBase
{
    private double speed;

    public MoveTransport(double speed)
    {
        addRequirements(RobotContainer.getTransport());
        this.speed = speed;

    }

    @Override
    public void initialize() {
        RobotContainer.getTransport().moveTransport(speed);
    }

    @Override
    public void execute() 
    {
        RobotContainer.getTransport().moveTransport(speed);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getTransport().stop();
    }

    @Override
    public boolean isFinished() 
    {
        return !RobotContainer.getJoy().getRawButton(Constants.TRANSPORT_BUTTON);
    }
}