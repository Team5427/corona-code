package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathBBlue extends SequentialCommandGroup
{
    public PathBBlue()
    {
        addCommands(new ArcStraight(0.4, -0.7, 25), new Arc(0.7, 0, 2.5));
    }
    
}
