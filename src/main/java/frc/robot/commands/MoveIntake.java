package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.XboxController;

public class MoveIntake extends CommandBase
{
    private double speed;

    public MoveIntake(double speed)
    {
        addRequirements(RobotContainer.getIntake());
        this.speed = speed;
    }

    @Override
    public void initialize()
    {
        RobotContainer.getIntake().moveIntake((RobotContainer.getJoy().getLeftTriggerAxis()));
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getIntake().stop();
    }

    @Override
    public void execute() {
        RobotContainer.getIntake().moveIntake((RobotContainer.getJoy().getLeftTriggerAxis()));
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }
}