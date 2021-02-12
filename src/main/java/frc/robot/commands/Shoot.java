// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Pulley;

public class Shoot extends CommandBase 
{

  private double startTime, elapsed;

  /** Creates a new Shoot. */
  public Shoot() 
  {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.getShooter(), RobotContainer.getPulley(), RobotContainer.getTransport());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
    RobotContainer.getPulley().movePulley(Constants.PULLEY_TELEOP_SPEED);
    startTime = Timer.getFPGATimestamp();
    elapsed = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
    RobotContainer.getPulley().movePulley(Constants.PULLEY_TELEOP_SPEED);
    elapsed = Timer.getFPGATimestamp() - startTime;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    RobotContainer.getTransport().stop();
    RobotContainer.getPulley().stop();
    RobotContainer.getShooter().stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return elapsed >= 5.0;
  }
}
