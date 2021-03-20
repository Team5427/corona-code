// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class MoveStraightPID extends PIDCommand {
  /** Creates a new MoveStraightPID. */

  private double startTime;
  private double commandTime;

  public MoveStraightPID(double time) {
    super(
        // The controller that the command will use
        new PIDController(0.11, 0.05, 0),
        // This should return the measurement
        () -> RobotContainer.getAHRS().getYaw(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
          RobotContainer.getDriveTrain().rampLeft(0.4);
          RobotContainer.getDriveTrain().getRight().set(-output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.getDriveTrain());
    commandTime = time;
    // Configure additional PID options by calling `getController` here.
  }

  @Override
  public void initialize()
  {
    startTime = Timer.getFPGATimestamp();
    super.initialize();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return Timer.getFPGATimestamp() - startTime >= commandTime;
  }

  @Override
  public void end(boolean interrupted)
  {
    while(DriveTrain.rightSpeedHigh > 0 || DriveTrain.leftSpeedHigh > 0)
    {
      RobotContainer.getDriveTrain().rampDownRight(Constants.AUTONOMOUS_SPEED);
      RobotContainer.getDriveTrain().rampDownLeft(-Constants.AUTONOMOUS_SPEED);
    }
  }
}
