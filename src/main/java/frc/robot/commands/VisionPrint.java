package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import frc.robot.Robot;



public class VisionPrint extends CommandBase{



    public VisionPrint() {

        //cam = new PhotonCamera("photoncam");
        //System.out.println("hbbbbbbh");

    }

    //var result = camera.getLatestResult();
    @Override
    public void initialize() {
        //System.out.println("hbh000000000000000000000000000000000000000000000000000000000000000000000000000000000");


    }

    @Override
    public void execute() {

        System.out.println(Robot.yaw);

    }

    public void end() {

    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(Constants.VISION_PRINT_BTN);
    }

}
