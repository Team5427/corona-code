package frc.robot.commands.auto.CompetitionPaths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.ArcStraight;
import frc.robot.commands.auto.MoveStraightPID;

public class RightSixCells extends SequentialCommandGroup
{
    public RightSixCells()
    {
        addCommands(new MoveStraightPID(2), new ArcStraight(0, 0.5, 20));
    }
    
}
