package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathABlue extends SequentialCommandGroup
{
    public PathABlue()
    {
        addCommands(new ArcStraight(0.4, -0.62, 16), new Arc(0.7, 0, 2.8), new ArcStraight(0.4, 0.66, -55), new Arc(0.7, 0, 2.5), new ArcStraight(0.4, -0.64, 15), new Arc(0.7, 0, 2.6));
    }
    
}
