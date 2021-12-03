package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.GenericHID.Hand;

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
        RobotContainer.getShooter().moveShooter((RobotContainer.getJoy().getTriggerAxis(Hand.kRight)));
    }

    @Override
    public void execute() {
        RobotContainer.getShooter().moveShooter((RobotContainer.getJoy().getTriggerAxis(Hand.kRight)));
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