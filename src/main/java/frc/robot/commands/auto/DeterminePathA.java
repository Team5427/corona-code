package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class DeterminePathA extends CommandBase
{
    private double timeDiff = 2;
    private double startTime = 0;
    public static boolean covered = false;

    public DeterminePathA()
    {
        addRequirements(RobotContainer.getIntake());
    }

    @Override
    public void initialize()
    {
        RobotContainer.getIntake().moveIntake(Constants.INTAKE_TELEOP_SPEED);
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished()
    {
        covered = RobotContainer.getTransport().getIntakeCovered();
        SmartDashboard.putBoolean("Covered", covered);
        return Timer.getFPGATimestamp() - startTime >= timeDiff || covered;
    }

    @Override
    public void end(boolean interrupted)
    {
        SmartDashboard.putBoolean("Covered", covered);
        
        if(covered)
        {
            CommandScheduler.getInstance().schedule(new PathARed());
        }
        else
        {
            System.out.println("Wrong path!!!!(*@!()");
            CommandScheduler.getInstance().schedule(new PathABlue());
        }
    }
}
