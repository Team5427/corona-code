package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.ShootAll;

public class AethiaRightThreeCells extends SequentialCommandGroup{

    public AethiaRightThreeCells()
    {
        addCommands(new MoveStraightPID(1.3), new Wait(1), new ShootAll(Constants.TIME_BETWEEN_CELLS, Constants.TIME_AFTER_CELLS, 0.8));
    }
    
}
