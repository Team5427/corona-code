package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase
{
    private MotorControllerGroup left, right;

    private DifferentialDrive driveBase;

    public DriveTrain(MotorControllerGroup left, MotorControllerGroup right, DifferentialDrive driveBase)
    {
        this.left = left;
        this.right = right;
        this.driveBase = driveBase;
    }

    public MotorControllerGroup getLeft()
    {
        return left;
    }

    public MotorControllerGroup getRight()
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
        driveBase.arcadeDrive(-joy.getRightX(), joy.getLeftY());
    }

    public DifferentialDrive getDriveBase()
    {
        return driveBase;
    }

    @Override
    public void periodic()
    {}

}