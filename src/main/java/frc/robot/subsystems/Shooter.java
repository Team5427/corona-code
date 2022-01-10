package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase
{
    private MotorController shooterMotorTop;
    private MotorController shooterMotorBottom;
    private Encoder shooterTopEnc;
    private Encoder shooterBottomEnc;
    
    public Shooter(MotorController shooterMotorTop, MotorController shooterMotorBottom, Encoder top, Encoder bottom)
    {
        this.shooterMotorTop = shooterMotorTop;
        this.shooterMotorBottom = shooterMotorBottom;
        shooterTopEnc = top;
        shooterBottomEnc = bottom;
    }

    public MotorController getShooterMotorTop()
    {
        return shooterMotorTop;
    }

    public MotorController getShooterMotorBottom()
    {
        return shooterMotorBottom;
    }

    public Encoder getTopEnc()
    {
        return shooterTopEnc;
    }

    public Encoder getBottomEnc()
    {
        return shooterBottomEnc;
    }

    public void moveShooter(double speed)
    {
        shooterMotorBottom.set(-speed);
        shooterMotorTop.set(speed);
    }

    public void stop()
    {
        shooterMotorBottom.set(0);
        shooterMotorTop.set(0);
    }

    public double calculateShooterSpeed()
    {
        double shooterSpeed = (RobotContainer.getUltrasonic().getRangeInches() / 12) / 5;
        return shooterSpeed;  
    }
}