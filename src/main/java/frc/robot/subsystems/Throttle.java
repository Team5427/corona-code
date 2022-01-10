package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Throttle extends SubsystemBase {
 
	
	private MotorController motorController;


    public Throttle(MotorController motorController) 
    {
        this.motorController = motorController;
    }

    @Override
    public void periodic() 
    {
        if(RobotContainer.getJoy().getPOV() >= 225 && RobotContainer.getJoy().getPOV() <= 315)
        {
            motorController.set(-1.0);
        }
        if(RobotContainer.getJoy().getPOV() >= 45 && RobotContainer.getJoy().getPOV() <= 135)
        {
            motorController.set(1.0);
        }
        if(RobotContainer.getJoy().getPOV() == -1)
        {
            motorController.set(0);
        }
    }

    public MotorController getMotor()
    {
        return motorController;
    }
}