package frc.robot.commands.auto.CompetitionPaths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShootAll;
import frc.robot.commands.auto.ArcStraight;
import frc.robot.commands.auto.MoveStraightPID;
import frc.robot.commands.auto.Wait;

public class RightSixCells extends SequentialCommandGroup
{
    public RightSixCells()
    {
        addCommands(/*new ShootAll(1.5, 2), */new ArcStraight(0, -0.5, 30), new Wait(1), new MoveStraightPID(3));
    }   
}