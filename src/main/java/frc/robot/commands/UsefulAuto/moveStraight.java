package frc.robot.commands.UsefulAuto;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class moveStraight extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    double bias = 0;
    double err;
    double setSpeedLeft = -0.2;
    double setSpeedRight = 0.2;
    boolean locked;
    /**
     * Creates a new MoveStraight.
     */
  
    //bias based on distance model in case it is needed
    public moveStraight(double bias)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.bias = bias;
    }

    @Override
    public void initialize() {
        locked = false;
    }

    @Override
    public void execute() {
        err = Robot.yaw;
        
        if(!Robot.hasTarget && !locked){
            driveTrain.getRight().set(-0.2);
            driveTrain.getLeft().set(-0.2);
        } else {

            locked = true;
            if (err < -3) {
                setSpeedLeft = -0.3;
                setSpeedRight = 0.4;
            } if (err > 3) {
                setSpeedRight = 0.3;
                setSpeedLeft = -0.4;
            }
            
            // System.out.println("Left: " + setSpeedLeft);
            //setSpeedRight = (err > 3)?.05:0.2;
            // System.out.println("Right: " + setSpeedRight);
            driveTrain.getLeft().set(setSpeedLeft);
            driveTrain.getRight().set(setSpeedRight);
        }
    //     // System.out.println("asd");
    //     if (err < -3) {
    //         setSpeedLeft = -0.3;
    //         setSpeedRight = 0.4;
    //     } if (err > 3) {
    //         setSpeedRight = 0.3;
    //         setSpeedLeft = -0.4;
    //     }
        
    //     // System.out.println("Left: " + setSpeedLeft);
    //     //setSpeedRight = (err > 3)?.05:0.2;
    //     // System.out.println("Right: " + setSpeedRight);
    //     driveTrain.getLeft().set(setSpeedLeft);
    //     driveTrain.getRight().set(setSpeedRight);
        }

    @Override
    public boolean isFinished() {
        if (Robot.pitch <= -12 && (Robot.yaw >= -3 || Robot.yaw <= 3) && locked) {
            return true;
        }
        //return !(RobotContainer.getJoy().getRawButton(1));
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
    
}
