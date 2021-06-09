package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

public class AethiaRightThreeCells extends SequentialCommandGroup{

    public AethiaRightThreeCells()
    {
        addCommands(new MoveStraightPID(1.3), new Wait(1));
    }
    
}
