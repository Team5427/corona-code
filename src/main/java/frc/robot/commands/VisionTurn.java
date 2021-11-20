/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import  edu.wpi.first.networktables.NetworkTableInstance;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import  edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class VisionTurn extends CommandBase 
{

  private DriveTrain driveTrain;
  private PhotonCamera cam;
  
  private int attempt =0;
  double dist;
  double bsd;
  boolean angledCentered = false;
  double newDistFromCenter;
  double newCenter;
  double bias = 0;
  double constant = 4;
  PhotonTrackedTarget target;
  boolean targetbool;
  double yaw;
  private double startTime;
  private double currTime;
  /**
   * Creates a new MoveStraight.
   */

  //bias based on distance model in case it is needed
  public VisionTurn(double bias) 
  {
    addRequirements(RobotContainer.getDriveTrain());
    this.bias = bias;
    startTime = currTime = 0;

    cam = new PhotonCamera("photonvision");

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {

  }

  @Override
  public void execute() 
  {
    var result = cam.getLatestResult();
    targetbool = result.hasTargets();
    target = result.getBestTarget();
    yaw = target.getYaw();
    currTime = Timer.getFPGATimestamp();

    
    if(!result.hasTargets()){
      driveTrain.stop();
    }
    else{
      if(result.getBestTarget().getYaw() >= 20){
       driveTrain.getDriveBase().arcadeDrive(0, .2);
      }
      else if(result.getBestTarget().getYaw() > 5){
        driveTrain.getDriveBase().arcadeDrive(0, .1);
      }
      else if(result.getBestTarget().getYaw() <= -20){
        driveTrain.getDriveBase().arcadeDrive(0, -.2);
      }
      else if(result.getBestTarget().getYaw() < -5){
        driveTrain.getDriveBase().arcadeDrive(0, -.1);
      }
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
    if(!targetbool || (yaw <= 5 && yaw >= -5))
      return false;

    return true;
  }

}