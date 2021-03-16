// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GalacticSearch extends SequentialCommandGroup {
  /** Creates a new GalacticSearch. */
  public GalacticSearch(double time1, double time2, double time3, double time4, double turn1, double turn2, double turn3) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
                new MoveStraightPID(time1), 
                new Wait(Constants.WAIT_TIME), 
                new PointTurn(turn1), 
                new MoveStraightPID(time2), 
                new Wait(Constants.WAIT_TIME), 
                new PointTurn(turn2), 
                new MoveStraightPID(time3), 
                new Wait(Constants.WAIT_TIME), 
                new PointTurn(turn3), 
                new MoveStraightPID(time4)
          );
  }
}
