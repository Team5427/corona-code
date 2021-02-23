package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class BallVisionMove extends CommandBase{
    private DriveTrain driveTrain;
    NetworkTableInstance inst;
    NetworkTable table;

    double distanceFromCenter;
    boolean ballExists;
    boolean ballCentered;

    public BallVisionMove()
    {
        addRequirements(RobotContainer.getDriveTrain());
        
    }

    public void initialize()
    {
        driveTrain = RobotContainer.getDriveTrain();
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("vision");
        
    }

    public void execute()
    {
        distanceFromCenter = table.getEntry("ballDistanceFromCenter").getDouble(0);
        ballExists = table.getEntry("ballExist").getBoolean(false);
        ballCentered = table.getEntry("isBallCentered").getBoolean(false);

        if(!ballExists)
            driveTrain.stop();

        else
        {
            if(distanceFromCenter > 4)
            {
                driveTrain.tankDrive(-.10, 10);
            }
            else if(distanceFromCenter < -4)
            {
                driveTrain.tankDrive(.10, -10);
            }
        }
    }

    public void end(boolean interrupted)
    {
       driveTrain.stop(); 
    }

    public boolean isFinished()
    {
        if(ballCentered)
            return true;

        return false;
    }

    
}
