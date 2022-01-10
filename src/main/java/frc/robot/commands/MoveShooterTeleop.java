package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveShooterTeleop extends CommandBase
{
    private double speed;

    public MoveShooterTeleop(double speed)
    {
        addRequirements(RobotContainer.getShooter());
        this.speed = speed;
    }

    @Override
    public void initialize() {
        RobotContainer.getShooter().moveShooter((RobotContainer.getJoy().getRightTriggerAxis()));
    }

    @Override
    public void execute() {
        if (RobotContainer.getJoy().getRightTriggerAxis() > 0 && RobotContainer.getJoy().getRightTriggerAxis() < 1)
        RobotContainer.getShooter().moveShooter(1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
    }
}