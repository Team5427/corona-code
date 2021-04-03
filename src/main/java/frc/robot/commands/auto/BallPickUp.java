package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.MoveTransportIntake;
import frc.robot.commands.ToggleIntake;

public class BallPickUp extends SequentialCommandGroup{
    
    public BallPickUp(){
        addCommands(new ToggleIntake(), new MoveTransportIntake(), new ToggleIntake());
    }
}
