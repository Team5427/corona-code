package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase
{
    private SpeedControllerGroup left, right;

    private DifferentialDrive driveBase;

    public DriveTrain(SpeedControllerGroup left, SpeedControllerGroup right, DifferentialDrive driveBase)
    {
        this.left = left;
        this.right = right;
        this.driveBase = driveBase;
    }

    public SpeedControllerGroup getLeft()
    {
        return left;
    }

    public SpeedControllerGroup getRight()
    {
        return right;
    }

    public void tankDrive(double leftSpeed, double rightSpeed)
    {
        driveBase.tankDrive(leftSpeed, rightSpeed);
    }

    public void stop()
    {
        left.stopMotor();
        right.stopMotor();
    }

    public void takeJoystickInputs(XboxController joy)
    {
        driveBase.arcadeDrive(joy.getY(Hand.kLeft), -joy.getX(Hand.kRight));
    }

    public DifferentialDrive getDriveBase()
    {
        return driveBase;
    }

    @Override
    public void periodic()
    {}

}