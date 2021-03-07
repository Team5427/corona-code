package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ResetSensors extends CommandBase
{
    public ResetSensors()
    {}

    @Override
    public void initialize()
    {
        RobotContainer.getEncLeft().reset();
        RobotContainer.getEncRight().reset();
        RobotContainer.getAHRS().reset();
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
