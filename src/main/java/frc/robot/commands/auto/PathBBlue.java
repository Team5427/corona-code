package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathBBlue extends SequentialCommandGroup
{
    public PathBBlue()
    {
        addCommands(new ArcStraight(0.4, -0.52, 25), new Arc(0.7, 0, 3.2), new ArcStraight(0.4, 0.7, -35), new Arc(0.7, 0, 2), new ArcStraight(0.4, -0.6, 35), new Arc(0.7, 0, 2.5));
    }
    
}
