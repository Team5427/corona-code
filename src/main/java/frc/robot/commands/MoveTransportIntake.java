package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveTransportIntake extends CommandBase
{
    public MoveTransportIntake()
    {
        addRequirements(RobotContainer.getTransport());
    }

    @Override
    public void initialize()
    {
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_TELEOP_SPEED);
    }

    @Override
    public void execute()
    {
        RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_TELEOP_SPEED);
    }

    @Override
    public boolean isFinished()
    {
        return !RobotContainer.getTransport().getIntakeCovered();
    }

    @Override
    public void end(boolean interrupted)
    {
        RobotContainer.getTransport().stop();
    }
}
