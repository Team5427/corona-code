package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class BarrelRacing extends SequentialCommandGroup
{
    public BarrelRacing()
    {
        addCommands(new RampArc(0.85, 0, 1.6), new Arc(0.85, -0.68, 2.3), new ArcStraight(0.85, -0.68, -8), new Arc(0.85, 0, 1.2), new Arc(0.85, 0.68, 3.5), new Arc(0.85, 0, 1.2), new ArcStraight(0.85, 0.65, 188), new Arc(0.85, 0, 1));
    }
    
}
