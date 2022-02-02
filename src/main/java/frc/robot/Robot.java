/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.commands.PathWeaver;




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

  NetworkTable table;
  NetworkTable table2;

  String trajectoryJSON = "paths/YourPath.wpilib.json";
  Trajectory trajectory = new Trajectory();

  public static double pitch, pitch2;
  public static double yaw, yaw2;
  public static double skew, skew2;
  public static double area, area2;
  public static double PixelX, PixelX2;
  public static double PixelY, PixelY2;
  public static boolean hasTarget, hasTarget2;

  double default_all = 0.0;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit()
  {
    PathWeaver.ConvertJson();
    m_robotContainer = new RobotContainer();
    RobotContainer.getAHRS().reset();
    NetworkTableInstance PIInstance = NetworkTableInstance.create();
    NetworkTableInstance PI2Instance = NetworkTableInstance.create();
    PIInstance.setServer("photonvision");
    PIInstance.startClient();
    table = PIInstance.getTable("photonvision").getSubTable("photoncam");
    PI2Instance.setServer("photonvision");
    PI2Instance.startClient();
    table = PI2Instance.getTable("photonvision").getSubTable("photoncam");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic()
  {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    // SmartDashboard.putBoolean("Intake Covered", RobotContainer.getTransport().getIntakeCovered());
    // SmartDashboard.putBoolean("Transport covered", RobotContainer.getTransport().getTransportCovered());
    // SmartDashboard.putBoolean("Pulley Covered", RobotContainer.getPulley().getPulleyCovered());

    //SmartDashboard.putNumber("Yaw", RobotContainer.getAHRS().getYaw());
    // SmartDashboard.putNumber("Left", RobotContainer.getElevator().getLeftEnc().getDistance());
    // SmartDashboard.putNumber("Right", RobotContainer.getElevator().getRightEnc().getDistance());
    // SmartDashboard.putNumber("Shooter Top Enc Rate", RobotContainer.getShooter().getTopEnc().getRate()*(60.0/1024.0));
    // //SmartDashboard.putNumber("Shooter Bottom Enc Rate", RobotContainer.getShooter().getBottomEnc().getRate()*(60.0/1024.0));

    hasTarget = table.getEntry("hasTarget").getBoolean(true);
    pitch = table.getEntry("targetPitch").getDouble(default_all);
    yaw = table.getEntry("targetYaw").getDouble(default_all);
    skew = table.getEntry("targetSkew").getDouble(default_all);
    area = table.getEntry("targetArea").getDouble(default_all);
    PixelX = table.getEntry("targetPixelsX").getDouble(default_all);
    PixelY = table.getEntry("targetPixelsY").getDouble(default_all);

    hasTarget2 = table.getEntry("hasTarget").getBoolean(true);
    pitch2 = table.getEntry("targetPitch").getDouble(default_all);
    yaw2 = table.getEntry("targetYaw").getDouble(default_all);
    skew2 = table.getEntry("targetSkew").getDouble(default_all);
    area2 = table.getEntry("targetArea").getDouble(default_all);
    PixelX2 = table.getEntry("targetPixelsX").getDouble(default_all);
    PixelY2 = table.getEntry("targetPixelsY").getDouble(default_all);
    //System.out.println(RobotContainer.getAHRS().getYaw());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit()
  {
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
    RobotContainer.getAHRS().reset();

    m_autonomousCommand = RobotContainer.getAutonomousCommand();

    if(m_autonomousCommand != null)
    {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    RobotContainer.getAHRS().reset();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic()
  {
    //System.out.println(RobotContainer.getShooter().getTopEnc().getRaw());
    //System.out.println("test");
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
}