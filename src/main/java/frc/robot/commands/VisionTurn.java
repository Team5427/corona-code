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

public class VisionTurn extends CommandBase {


  private DriveTrain driveTrain;
  NetworkTableInstance inst;
  NetworkTable table;
  private int attempt =0;
  double distanceFromCenter; // distance of center of target to center of camera
  double biggestSideHeight; //records size (in height) of larger side of target (left or right)
  boolean angledCentered = false;
  double newDistFromCenter;
  double newCenter;
  double bias = 0;
  double constant = 4;

  /**
   * Creates a new MoveStraight.
   */

  //bias based on distance model in case it is needed
  public VisionTurn(double bias) 
  {
    addRequirements(RobotContainer.getDriveTrain());
    this.bias = bias;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    driveTrain = RobotContainer.getDriveTrain();
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("vision");

  }

  @Override
  public void execute() 
  {
    distanceFromCenter = table.getEntry("targetDistanceFromCenter").getDouble(0);
    distanceFromCenter -=bias;
    biggestSideHeight = table.getEntry("biggestSideDifference").getDouble(0);
    boolean targetExists = table.getEntry("targetExists").getBoolean(false);
    distanceFromCenter -= 4;

    
    if(!targetExists)
    driveTrain.stop();
 
    else if(biggestSideHeight < 25)
    {

      if(distanceFromCenter > 0)
      {
        if(distanceFromCenter > 55)
          driveTrain.tankDrive(-.15, .15);
        else
          driveTrain.tankDrive(-.13, .13);
      }

      else if(distanceFromCenter < 0)
      {
        if(distanceFromCenter < -55)
          driveTrain.tankDrive(.15, -.15);
        else
          driveTrain.tankDrive(.13, -.13);
      }

    }

    else if(distanceFromCenter > 0)
    {
      if(distanceFromCenter < 25)
        driveTrain.tankDrive(-.14, .14);
      if(distanceFromCenter > 60)
        driveTrain.tankDrive(-.16, .16);
      else
        driveTrain.tankDrive(-.15, .15);
    }

    else if(distanceFromCenter < 0)
    {
      if(distanceFromCenter > -25)
        driveTrain.tankDrive(.14,- .14);
      if(distanceFromCenter < -60)
        driveTrain.tankDrive(.16, -.16);
      else
        driveTrain.tankDrive(.15, -.15);
    }
     
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    SmartDashboard.putNumber("attempt", attempt);
    double proportion = table.getEntry("proportion").getDouble(1);
    distanceFromCenter = table.getEntry("targetDistanceFromCenter").getDouble(0);
    distanceFromCenter -= 4;
    distanceFromCenter -=bias;
    distanceFromCenter = Math.abs(distanceFromCenter);
   

    if(proportion>.9 && distanceFromCenter < 3 + (constant*proportion))
       {return true;}

    if(distanceFromCenter < 3)
      {return true;}

    return false;
  }

}