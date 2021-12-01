package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathARed extends SequentialCommandGroup
{
    public PathARed()
    {
        addCommands(new ArcStraight(0.4, -0.57, 15), new Arc(0.7, 0, 2.4), new ArcStraight(0.4, 0.68, -55), new Arc(0.7, 0, 2.3), new ArcStraight(0.4, -0.5, 0), new Arc(0.85, 0, 2));
        //, new ArcStraight(0, 0.7, -72), new RampArc(-0.5, 0, 3), new Wait(1), new ArcStraight(0, -0.7, 45), new RampArc(-0.7, 0, 2)
    }
    
}
