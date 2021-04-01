package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class BouncePath extends SequentialCommandGroup{
    
    public BouncePath()
    {
        addCommands(new Arc(0.85, 0.64, 0.7), new Wait(1.1), new RampArc(-0.85, 0, 1));
    }
}
