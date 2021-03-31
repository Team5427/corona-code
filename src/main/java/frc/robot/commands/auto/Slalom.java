// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Slalom extends SequentialCommandGroup {
  /** Creates a new Slalom. */
  public Slalom() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    // addCommands(new RampArc(-0.4, 0, 0.5), new Arc(-0.7, 0.52, 2.5), new RampDownArc(-0.7, -0.47, 2.47), new Wait(0.3), new MoveStraightPID(4), new Arc(-0.7,-0.47, 2.47), new Arc(-0.7, 0.55, 7.7), new Arc(-0.7, -0.435, 2), new Arc(-0.7, 0, 3));
    addCommands(new RampArc(0.4, 0, 0.5), new Arc(0.7, 0.52, 1.5), new ArcStraight(0.7, -0.50, 0), new Arc(0.7, 0, 2.0), new Arc(0.7,-0.53, 1.8), new Arc(0.7, 0.55, 5.9), new ArcStraight(0.7, -0.52, 180), new Arc(0.7, 0, 2.3), new Arc(0.7, -0.52, 1.5), new Arc(0.7, 0.60, 1.0));//1.64
    
  }
}