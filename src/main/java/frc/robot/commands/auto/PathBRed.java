package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathBRed extends SequentialCommandGroup
{
    public PathBRed()
    {
        addCommands(new ArcStraight(0.4, -0.7, 30), new Arc(0.7, 0, 2));
    }
    
}
