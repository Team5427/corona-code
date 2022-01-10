package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase
{ 
    private MotorController intake;  

    public Intake(MotorController intake) 
    {
        this.intake = intake;
    }
    public void moveIntake(double speed)
    {
        intake.set(speed);
    }
    public void stop()
    {
       intake.stopMotor();
    }
    public boolean isMoving()
    {
        return intake.get() > 0.1 || intake.get() < -0.1;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake", intake.get());
    }
}