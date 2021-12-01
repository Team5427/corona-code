package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase
{

    private SpeedController left, right;
    private Encoder leftEnc, rightEnc;
    private DigitalInput limitLeft;
    private DigitalInput limitRight;

    public Elevator(SpeedController left, SpeedController right, DigitalInput limitLeft, DigitalInput limitRight, Encoder encoderLeft, Encoder encoderRight)
    {
        this.left = left;
        this.right = right;
        leftEnc = encoderLeft;
        rightEnc = encoderRight;
        this.limitLeft = limitLeft;
        this.limitRight = limitRight;
    }

    public void setSpeed(double speed) 
    {
        left.set(speed);
        right.set(speed);
    }

    public void setLeft(double speed)
    {
        left.set(speed);
    }

    public void setRight(double speed)
    {
        right.set(speed);
    }

    public void stop() 
    {
        left.stopMotor();
        right.stopMotor();
    }

    public boolean getLimitLeft()
    {
        return !limitLeft.get();
    }


    public boolean getLimitRight()
    {
        return !limitRight.get();
    }

    public boolean getEncLimit()
    {
        return leftEnc.getDistance() >= Constants.ELEVATOR_UPPER_LIMIT || rightEnc.getDistance() >= Constants.ELEVATOR_UPPER_LIMIT;
    }

    public Encoder getLeftEnc()
    {
        return leftEnc;
    }

    public Encoder getRightEnc()
    {
        return rightEnc;
    }

    @Override
    public void periodic() {
        //6379.0::-6361.25
    }
    
}