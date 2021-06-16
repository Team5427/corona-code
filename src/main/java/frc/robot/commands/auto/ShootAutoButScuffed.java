package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class ShootAutoButScuffed extends CommandBase{
    double startTime;
    double shooterTime;
    double runTime;
    public ShootAutoButScuffed(){
        startTime = Timer.getFPGATimestamp();
        shooterTime = 2;
        runTime = 10;
        
    }

    @Override
    public void initialize() {
       RobotContainer.getShooter().moveShooter(1);
       
        
    }

    @Override
    public void execute(){
        if(Timer.getFPGATimestamp() - startTime > shooterTime){
            RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_TELEOP_SPEED);
            RobotContainer.getPulley().movePulley(Constants.PULLEY_TELEOP_SPEED);
        }

    }

    @Override
    public boolean isFinished(){
        if(Timer.getFPGATimestamp() - startTime > runTime){
            return true;
        }

        return false;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getShooter().stop();
        RobotContainer.getTransport().stop();
        RobotContainer.getPulley().stop();
    }
}
