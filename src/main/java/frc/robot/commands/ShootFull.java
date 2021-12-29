package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShootFull extends CommandBase {
    private double speedPulley;
    private double speedTransport;
    public ShootFull() {
        addRequirements(RobotContainer.getShooter());
        addRequirements(RobotContainer.getPulley());
        addRequirements(RobotContainer.getIntake());
        addRequirements(RobotContainer.getTransport());
        this.speedPulley = speedPulley;
        this.speedTransport = speedTransport;
    }

    @Override
    public void initialize() {
        RobotContainer.getShooter().moveShooter(1.0);
        RobotContainer.getTransport().moveTransport(speedTransport);
        RobotContainer.getPulley().movePulley(speedPulley);
        RobotContainer.getIntake().moveIntake(1.0);
    }

    @Override
    public void execute() {
        RobotContainer.getShooter().moveShooter(1.0);
        RobotContainer.getTransport().moveTransport(speedTransport);
        RobotContainer.getPulley().movePulley(speedPulley);
        RobotContainer.getIntake().moveIntake(1.0);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
        RobotContainer.getTransport().stop();
        RobotContainer.getPulley().stop();
        RobotContainer.getIntake().stop();
    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(Constants.ALL_BUTTON);
    }
}
