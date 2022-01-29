/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MovePulley;
import frc.robot.commands.MoveShooterTeleop;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.VisionForward;
import frc.robot.commands.MoveTilt;
import frc.robot.commands.VisionTurn;
import frc.robot.commands.VisionTurnRight;
import frc.robot.commands.UsefulAuto.TrajectoryAuton;
import frc.robot.commands.UsefulAuto.moveStraight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.OdometryDriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pulley;
import frc.robot.subsystems.Transport;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tilt;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
  // The robot's subsystems and commands are defined here...

  //numbers

  //joystick
  private static XboxController joy;
  private static Button transportButton;
  private static Button pulleyButton;
  private static Button tiltUp;
  private static Button tiltDown;
  private static Button moveElevatorUp;
  private static Button moveElevatorDown;
  public static Button visionbtn;
  public static Button visionbtnf;

  //motors
  private final MotorController frontLeft, rearLeft;
  private final MotorController frontRight,rearRight;
  private static MotorControllerGroup leftDrive;
  private static MotorControllerGroup rightDrive;
  private static MotorController transportMotor;
  private static MotorController intakeMotor;
  private static MotorController shooterMotorTop;
  private static MotorController shooterMotorBottom;
  private static MotorController pulleyMotor;
  private static MotorController tiltMotor;
  private static MotorController elevatorLeft, elevatorRight;

  //sensors
  private static AnalogInput pulleyProximity;
  private static AnalogInput transportProximity;
  private static AnalogInput transportProximityTwo;
  public static Encoder shooterTopEnc;
  public static Encoder shooterBottomEnc;
  private static Encoder elevatorLeftEnc, elevatorRightEnc;
  public static Encoder dt_left_top_enc, dt_right_top_enc;
  private static DigitalInput tiltSwitch;
  private static DigitalInput limitSwitchLeft;
  private static DigitalInput limitSwitchRight;
  private static Ultrasonic ultra;
  private static AHRS ahrs;

  //subsystems
  private static DifferentialDrive drive;
  public static OdometryDriveTrain odometryDriveTrain;
  private static DriveTrain driveTrain;
  private static Transport transport;
  private static Intake intake;
  private static Pulley pulley;
  private static Shooter shooter;
  private static Tilt tilt;
  private static Elevator elevator;

  //camera
  public static CameraServer server;
  public static UsbCamera cam;

  //trajectory
  public static DifferentialDriveOdometry robot_odometry;

  public static PIDController pid;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {

    // dt_left_top_enc = new Encoder(Constants.DT_ENC_LEFT_TOP, Constants.DT_ENC_LEFT_TOP2);
    // dt_right_top_enc = new Encoder(Constants.DT_ENC_RIGHT_TOP, Constants.DT_ENC_RIGHT_TOP2);
    // dt_left_top_enc.setDistancePerPulse(Constants.DISTANCE_PER_PULSE);
    // dt_right_top_enc.setDistancePerPulse(Constants.DISTANCE_PER_PULSE);

    pid = new PIDController(0, 0, 0);

    frontLeft = new WPI_VictorSPX(Constants.LEFT_TOP_MOTOR);
    rearLeft = new WPI_VictorSPX(Constants.LEFT_BOTTOM_MOTOR);
    leftDrive = new MotorControllerGroup(frontLeft, rearLeft);
    frontRight = new WPI_VictorSPX(Constants.RIGHT_BOTTOM_MOTOR);
    rearRight = new WPI_VictorSPX(Constants.RIGHT_TOP_MOTOR);
    rightDrive = new MotorControllerGroup(frontRight, rearRight);
    drive = new DifferentialDrive(leftDrive, rightDrive);
    drive.setSafetyEnabled(false);
    driveTrain = new DriveTrain(leftDrive, rightDrive, drive);
    //odometryDriveTrain = new OdometryDriveTrain(leftDrive, rightDrive, drive, dt_left_top_enc, dt_right_top_enc, ahrs, robot_odometry);
    driveTrain.setDefaultCommand(new DriveWithJoystick());
    dt_left_top_enc = new Encoder(4, 5);
    dt_right_top_enc = new Encoder(6, 7);
    dt_left_top_enc.setDistancePerPulse(1);
    dt_right_top_enc.setDistancePerPulse(1);



    intakeMotor = new WPI_VictorSPX(Constants.INTAKE_MOTOR);
    intake = new Intake(intakeMotor);
    intake.setDefaultCommand(new MoveIntake(Constants.INTAKE_TELEOP_SPEED));

    transportMotor = new WPI_VictorSPX(Constants.TRANSPORT_MOTOR);
    transportProximity = new AnalogInput(Constants.TRANSPORT_PROXIMITY_ONE_SENSOR_PORT);
    transportProximityTwo = new AnalogInput(Constants.TRANSPORT_PROXIMITY_TWO_SENSOR_PORT);
    transport = new Transport(transportMotor, transportProximity, transportProximityTwo);

    tiltMotor = new WPI_VictorSPX(Constants.TILT_MOTOR);
    tiltSwitch = new DigitalInput(Constants.TILT_SWITCH_PORT);
    tilt = new Tilt(tiltMotor, tiltSwitch);

    pulleyMotor = new WPI_VictorSPX(Constants.PULLEY_MOTOR);
    pulleyProximity = new AnalogInput(Constants.PULLEY_PROXIMITY_SENSOR_PORT);
    pulley = new Pulley(pulleyMotor, pulleyProximity);

    shooterTopEnc = new Encoder(Constants.SHOOTER_TOP_ENC_PORT_1, Constants.SHOOTER_TOP_ENC_PORT_2);
    shooterBottomEnc = new Encoder(Constants.SHOOTER_BOTTOM_ENC_PORT_1, Constants.SHOOTER_BOTTOM_ENC_PORT_2);
    shooterBottomEnc.setDistancePerPulse(Constants.SHOOTER_DIST_PER_PULSE);
    shooterTopEnc.setDistancePerPulse(-Constants.SHOOTER_DIST_PER_PULSE);
    shooterMotorTop = new WPI_VictorSPX(Constants.SHOOTER_MOTOR_TOP);
    shooterMotorBottom = new WPI_VictorSPX(Constants.SHOOTER_MOTOR_BOTTOM);
    shooter = new Shooter(shooterMotorTop, shooterMotorBottom, shooterTopEnc, shooterBottomEnc);
    shooter.setDefaultCommand(new MoveShooterTeleop(Constants.SHOOTER_TELEOP_SPEED));

    elevatorLeft = new WPI_VictorSPX(Constants.ELEVATOR_LEFT_MOTOR);
    elevatorRight = new WPI_VictorSPX(Constants.ELEVATOR_RIGHT_MOTOR);
    elevatorLeftEnc = new Encoder(Constants.ELEVATOR_LEFT_PORT_1, Constants.ELEVATOR_LEFT_PORT_2);
    elevatorRightEnc = new Encoder(Constants.ELEVATOR_RIGHT_PORT_1, Constants.ELEVATOR_RIGHT_PORT_2);
    limitSwitchLeft = new DigitalInput(Constants.ELEVATOR_LIMIT_LEFT);
    limitSwitchRight = new DigitalInput(Constants.ELEVATOR_LIMIT_RIGHT);
    elevator = new Elevator(elevatorLeft, elevatorRight, limitSwitchLeft, limitSwitchRight, elevatorLeftEnc, elevatorRightEnc);


    ultra = new Ultrasonic(Constants.ULTRASONIC_PING, Constants.ULTRASONIC_ECHO);
    Ultrasonic.setAutomaticMode(true);

    ahrs = new AHRS(SPI.Port.kMXP);
    //robot_odometry = new DifferentialDriveOdometry(ahrs.getRotation2d());

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
    joy = new XboxController(0);

    transportButton = new JoystickButton(joy, Constants.TRANSPORT_BUTTON);
    pulleyButton = new JoystickButton(joy, Constants.PULLEY_BUTTON);
    tiltUp = new JoystickButton(joy, Constants.TILT_BUTTON_UP);
    tiltDown = new JoystickButton(joy, Constants.TILT_BUTTON_DOWN);
    moveElevatorUp = new JoystickButton(joy, Constants.ELEVATOR_UP_BUTTON);
    moveElevatorDown = new JoystickButton(joy, Constants.ELEVATOR_DOWN_BUTTON);
    visionbtn = new JoystickButton(joy, Constants.VISION_PRINT_BTN);
    visionbtnf = new JoystickButton(joy, 2);



    transportButton.whenPressed(new MoveTransport(Constants.TRANSPORT_TELEOP_SPEED));
    pulleyButton.whenPressed(new MovePulley(Constants.PULLEY_TELEOP_SPEED));
    tiltUp.whenPressed(new MoveTilt(Constants.TILT_SPEED));
    tiltDown.whenPressed(new MoveTilt(-Constants.TILT_SPEED));
    moveElevatorUp.whileHeld(new MoveElevator(Constants.ELEVATOR_SPEED));
    moveElevatorDown.whileHeld(new MoveElevator(-Constants.ELEVATOR_SPEED));
    //visionbtn.whenPressed(new moveStraight(0));
    //visionbtnf.whileHeld(new VisionForward(0));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand()
  {

    return new moveStraight(0);

  }

  public static DriveTrain getDriveTrain(){return driveTrain;}
  public static DifferentialDriveOdometry getOdometry(){return robot_odometry;}
  public static MotorControllerGroup getLeftSCG(){return leftDrive;}
  public static MotorControllerGroup getRightSCG(){return rightDrive;}
  public static DifferentialDrive getDiffDrive(){return drive;}
  public static AHRS getAHRS(){return ahrs;}
  public static XboxController getJoy(){return joy;}
  public static Intake getIntake(){return intake;}
  public static Transport getTransport(){return transport;}
  public static Pulley getPulley(){return pulley;}
  public static Tilt getTilt(){return tilt;}
  public static Shooter getShooter(){return shooter;}
  public static Ultrasonic getUltrasonic(){return ultra;}
  public static Elevator getElevator(){return elevator;}
}