// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Transport;

public class MoveTransportShooting extends CommandBase 
{

  private double startTime;
  private double currentTime;
  private double timeElapsed;

  /** Creates a new MoveTransportShooting. */
  public MoveTransportShooting() 
  {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.getTransport(), RobotContainer.getShooter());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    //RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
    RobotContainer.getShooter().moveShooter(Constants.SHOOTER_DOWN_SPEED);
    startTime = Timer.getFPGATimestamp();
    timeElapsed = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    timeElapsed = Timer.getFPGATimestamp() - startTime;

    if(RobotContainer.getTransport().getTransportCovered() && timeElapsed < 4)
    {
      RobotContainer.getTransport().stop();
    }
    else
    {
      RobotContainer.getTransport().moveTransport(Constants.TRANSPORT_INTEGRATED_SPEED);
      RobotContainer.getShooter().moveShooter(Constants.SHOOTER_DOWN_SPEED);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
      CommandScheduler.getInstance().schedule(new Shoot());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    SmartDashboard.putNumber("Time elapsed", timeElapsed);
    return timeElapsed >= 4;
  }
}
