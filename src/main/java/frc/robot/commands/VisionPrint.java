package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class VisionPrint extends CommandBase{
    private PhotonCamera cam;

    boolean HASTargets;

    PhotonTrackedTarget target;

    double yaw;
    double pitch;
    double area;
    double skew;

    public VisionPrint() {
        cam = new PhotonCamera("epiccam");
    }

    //var result = camera.getLatestResult();
    int x = 1;
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        var result = cam.getLatestResult();
        HASTargets = result.hasTargets();
        if (HASTargets) {
            target = result.getBestTarget();
            yaw = target.getYaw();
            pitch = target.getPitch();
            area = target.getArea();
            skew = target.getSkew();
            System.out.println("Yaw:" + yaw + " Pitch:" + pitch + " Area:" + area + " Skew:" + skew);

        } else {
            System.out.println("No target Tracked :(");
        }

    }

    public void end() {

    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(Constants.VISION_PRINT_BTN);
    }

}
