package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AethiaMoveBack extends SequentialCommandGroup{
    public AethiaMoveBack(){
        addCommands(new MoveStraightPID(1.5));
    }
}
