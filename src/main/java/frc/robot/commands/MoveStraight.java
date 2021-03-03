/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class MoveStraight extends CommandBase {

  private double time;
  private double startTime;
  private DriveTrain driveTrain;
  private double maxVelocity, maxTime, velocity = 0;

  /**
   * Creates a new MoveStraight.
   */
  public MoveStraight(double time) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.time = time;
    addRequirements(RobotContainer.getDriveTrain());
    //initialize();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain = RobotContainer.getDriveTrain();
    RobotContainer.getEncLeft().reset();
    RobotContainer.getEncRight().reset();
    startTime = Timer.getFPGATimestamp();
    //execute();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    velocity = RobotContainer.getDriveTrain().getAvgRate();
    double timediff = Timer.getFPGATimestamp() - startTime;
    if(velocity > maxVelocity)
    {
      maxVelocity = velocity;
      maxTime = timediff;
    }
    // System.out.println(RobotContainer.getDriveTrain().getAvgRate()+ "....."+ timediff);
    driveTrain.tankDrive(-1.0, -1.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    driveTrain.stop();
    // System.out.println(maxVelocity+ ": "+ maxTime);
    //101.2511257187744: 1.8536430000000053
    //102.30077177204211: 2.5554659999999956
    //101.94183232946264: 2.5361699999999985
    //101.831243: 2.31509296
    //43.9858117

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - startTime > time;
  }

}
