package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonButScuffed extends SequentialCommandGroup{
    public AutonButScuffed(){
        addCommands(new Arc(.7, 0, 1), new Wait(1), new ShootAutoButScuffed());
    }
    
}
