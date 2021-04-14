package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathBRed extends SequentialCommandGroup
{
    public PathBRed()
    {
        System.out.println("yes");
        addCommands(new ArcStraight(0.4, -0.56, 30), new Arc(0.7, 0, 2), new ArcStraight(0.4, 0.6, -30), new Arc(0.7, 0, 2), new ArcStraight(0.4, -0.56, 0), new Arc(0.7, 0, 2));
    }
    
}
