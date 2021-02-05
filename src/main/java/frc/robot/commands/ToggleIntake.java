package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ToggleIntake extends CommandBase
{
    private boolean isFinished = false;

    public ToggleIntake()
    {
        isFinished = false;
    }

    @Override
    public void initialize()
    {
        if(RobotContainer.getIntake().isMoving())
        {
            RobotContainer.getIntake().stop();
            isFinished = true;
        }
        else
        {
            RobotContainer.getIntake().moveIntake(Constants.INTAKE_INTEGRATED_SPEED);
        }
    }

    @Override
    public void execute()
    {

    }

    @Override
    public boolean isFinished()
    {
        return isFinished || RobotContainer.getTransport().getIntakeCovered();
    }

    @Override
    public void end(boolean interrupted)
    {
        RobotContainer.getIntake().stop();
        isFinished = false;
    }
}
