package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class BouncePath extends SequentialCommandGroup{
    
    public BouncePath()
    {
        addCommands(new RampArc(0.85, 0.6, 1.6), new RampArc(-0.85, 0.37, 0.5), new Arc(-0.85, 0.0, 1.2), new Arc(-0.85, 0.7, 1.7), new Arc(-0.85, 0, 1.32), new RampArc(0.85, 0, 1.4), new Arc(0.85, 0.6, 2.5), new Arc(0.85, 0, 1.1), new RampArc(-0.8, 0.6, 1.0));
        ////////////////////////////////////////1*//////////////////////////////////////////////////////////////Around Cone//////////////////////////////////2*/////////////////////////////Around Cone/////////////////////////////////3*
    }
}
