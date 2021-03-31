/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrain;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  public static double lowest;
  public static double highest;
  // public static double lastTime;
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    RobotContainer.getAHRS().reset();
    RobotContainer.getEncLeft().reset();
    RobotContainer.getEncRight().reset();

    DriveTrain.leftSpeed = 0;
    DriveTrain.rightSpeed = 0;
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    // SmartDashboard.putNumber("Left Encoder Distance", RobotContainer.getEncLeft().getDistance());
    // SmartDashboard.putNumber("Right Encoder Distance", RobotContainer.getEncRight().getDistance());
    // SmartDashboard.putNumber("Proximity one", RobotContainer.getTransport().getDistance());
    // SmartDashboard.putNumber("Proximity two", RobotContainer.getTransport().getDistanceTwo());
    // SmartDashboard.putNumber("Proximity three", RobotContainer.getPulley().getDistance());

    // SmartDashboard.putNumber("Prox3 Low", lowest);
    // SmartDashboard.putNumber("Prox3 High", highest);

    // SmartDashboard.putBoolean("Intake Covered", RobotContainer.getTransport().getIntakeCovered());
    // SmartDashboard.putBoolean("Transport covered", RobotContainer.getTransport().getTransportCovered());
    // SmartDashboard.putBoolean("Pulley Covered", RobotContainer.getPulley().getPulleyCovered());

    SmartDashboard.putNumber("Distance", Units.metersToInches(RobotContainer.getDriveTrain().getAvgDistance()));

    SmartDashboard.putNumber("Yaw", RobotContainer.getAHRS().getYaw());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    lowest = 0;
    highest = 0;
  }

  @Override
  public void disabledPeriodic() {
    
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() 
  {
    // lastTime = Timer.getFPGATimestamp();
    RobotContainer.getAHRS().reset();
    RobotContainer.getEncLeft().reset();
    RobotContainer.getEncRight().reset();

    DriveTrain.leftSpeed = 0;
    DriveTrain.rightSpeed = 0;
    
    m_autonomousCommand = RobotContainer.getAutonomousCommand();

    if(m_autonomousCommand != null)
    {
      m_autonomousCommand.schedule();
    }

    // RobotContainer.getDriveTrain().getLeft().set(-0.3);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    // System.out.printf("%.4f\n", (Timer.getFPGATimestamp() - lastTime));
    // lastTime = Timer.getFPGATimestamp();
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    lowest = 0;
    highest = 0;
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    // RobotContainer.getShooter().getShooterMotorTop().set(0.4);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
}