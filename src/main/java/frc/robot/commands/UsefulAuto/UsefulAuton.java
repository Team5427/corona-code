package frc.robot.commands.UsefulAuto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class UsefulAuton extends SequentialCommandGroup{
    public UsefulAuton(){
        addCommands(new Arc(.7, 0, 1));
    }
}
