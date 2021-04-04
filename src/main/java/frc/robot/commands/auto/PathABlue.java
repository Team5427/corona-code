package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathABlue extends SequentialCommandGroup
{
    public PathABlue()
    {
        addCommands(new ArcStraight(0, -0.7, 29), new RampArc(-0.5, 0, 2), new Wait(1), new ArcStraight(0, 0.7, -72), new RampArc(-0.5, 0, 3), new Wait(1), new ArcStraight(0, -0.7, 47), new RampArc(-0.7, 0, 1));
    }
    
}
