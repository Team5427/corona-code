package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;


public class Intake extends SubsystemBase
{ 
    private SpeedController intake;  

    public Intake(SpeedController intake) 
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

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake", intake.get());
    }
}