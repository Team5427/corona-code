/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import  edu.wpi.first.networktables.NetworkTableEntry;
import  edu.wpi.first.networktables.NetworkTableInstance;
import  edu.wpi.first.networktables.NetworkTable;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

import frc.robot.subsystems.DriveTrain;

public class StopVision extends CommandBase {


  private DriveTrain driveTrain;
  

  /**
   * Creates a new MoveStraight.
   */

  //bias based on distance model in case it is needed
  public StopVision() 
  {
    addRequirements(RobotContainer.getDriveTrain());
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    RobotContainer.getDriveTrain().stop();
  }

  @Override
  public void execute() 
  {
    RobotContainer.getDriveTrain().stop();

     
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    RobotContainer.getDriveTrain().stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
      return true;
  }
}