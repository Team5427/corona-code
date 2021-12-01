package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class VisionTurnLeft extends CommandBase
{

  private DriveTrain driveTrain = RobotContainer.getDriveTrain();

  double bias = 0;
  /**
   * Creates a new MoveStraight.
   */

  //bias based on distance model in case it is needed
  public VisionTurnLeft(double bias)
  {
    addRequirements(RobotContainer.getDriveTrain());
    this.bias = bias;


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {

  }

  @Override
  public void execute()
  {


    if(!Robot.hasTarget){
      driveTrain.getRight().set(0.5);
      driveTrain.getLeft().set(0.5);
    }
    else{
      if(Robot.yaw >= 20){
        driveTrain.getRight().set(-0.5);
        driveTrain.getLeft().set(-0.5);      }
      else if(Robot.yaw > 3){
        driveTrain.getRight().set(-0.2);
        driveTrain.getLeft().set(-0.2);      }
      else if(Robot.yaw <= -20){
        driveTrain.getRight().set(0.5);
        driveTrain.getLeft().set(0.5);      }
      else if(Robot.yaw < -3){
        driveTrain.getRight().set(0.2);
        driveTrain.getLeft().set(0.2);

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
    if(!Robot.hasTarget || (Robot.yaw >= 3 || Robot.yaw <= -3))
      return false;

    return true;
  }

}