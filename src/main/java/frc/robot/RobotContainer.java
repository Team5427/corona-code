/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MovePulley;
import frc.robot.commands.MoveShooterTeleop;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.MoveTilt;
import frc.robot.commands.MoveTiltAuto;
import frc.robot.commands.auto.AethiaCenterThreeCells;
import frc.robot.commands.auto.AethiaLeftThreeCells;
import frc.robot.commands.auto.AethiaRightSixCells;
import frc.robot.commands.auto.AethiaRightThreeCells;
import frc.robot.commands.auto.PointTurn;
import frc.robot.commands.ShootAll;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pulley;
import frc.robot.subsystems.Transport;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Throttle;
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
  private static Joystick joy;
  private static Button intakeButton;
  private static Button transportButton;
  private static Button pulleyButton;
  private static Button shooterTeleop;
  private static Button tiltButtonUp;
  private static Button tiltDownButton;
  private static Button tiltAuto;
  private static Button moveElevatorUp;
  private static Button moveElevatorDown;
  public static Button shootAll;

  //motors 
  private final SpeedController frontLeft, rearLeft;
  private final SpeedController frontRight,rearRight;
  private static SpeedControllerGroup leftDrive;
  private static SpeedControllerGroup rightDrive;
  private static SpeedController transportMotor;
  private static SpeedController intakeMotor;
  private static SpeedController shooterMotorTop;
  private static SpeedController shooterMotorBottom;
  private static SpeedController pulleyMotor;
  private static SpeedController tiltMotor;
  private static SpeedController elevatorLeft, elevatorRight;
  private static SpeedController throttleMotor;

  //sensors
  private static AnalogInput pulleyProximity;
  private static AnalogInput transportProximity;
  private static AnalogInput transportProximityTwo;
  private static Encoder shooterTopEnc;
  private static Encoder shooterBottomEnc;
  private static Encoder elevatorLeftEnc, elevatorRightEnc;
  private static DigitalInput tiltSwitch;
  private static DigitalInput limitSwitchLeft;
  private static DigitalInput limitSwitchRight;
  private static Ultrasonic ultra;
  private static AHRS ahrs;

  //subsystems
  private static DifferentialDrive drive;
  private static DriveTrain driveTrain;
  private static Transport transport;
  private static Intake intake;
  private static Pulley pulley;
  private static Shooter shooter;
  private static Tilt tilt;
  private static Elevator elevator;
  private static Throttle throttle;

  //camera
  public static CameraServer server;
  public static UsbCamera cam;
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {
    // server = CameraServer.getInstance();
    // cam = server.startAutomaticCapture(0);

    frontLeft = new WPI_VictorSPX(Constants.LEFT_TOP_MOTOR);
    rearLeft = new WPI_VictorSPX(Constants.LEFT_BOTTOM_MOTOR);
    leftDrive = new SpeedControllerGroup(frontLeft, rearLeft);
    frontRight = new WPI_VictorSPX(Constants.RIGHT_BOTTOM_MOTOR);
    rearRight = new WPI_VictorSPX(Constants.RIGHT_TOP_MOTOR);
    rightDrive = new SpeedControllerGroup(frontRight, rearRight);
    drive = new DifferentialDrive(leftDrive, rightDrive);
    drive.setSafetyEnabled(false);
    driveTrain = new DriveTrain(leftDrive, rightDrive, drive);
    driveTrain.setDefaultCommand(new DriveWithJoystick());

    intakeMotor = new WPI_VictorSPX(Constants.INTAKE_MOTOR);
    intake = new Intake(intakeMotor);

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

    elevatorLeft = new WPI_VictorSPX(Constants.ELEVATOR_LEFT_MOTOR);
    elevatorRight = new WPI_VictorSPX(Constants.ELEVATOR_RIGHT_MOTOR);
    elevatorLeftEnc = new Encoder(Constants.ELEVATOR_LEFT_PORT_1, Constants.ELEVATOR_LEFT_PORT_2);
    elevatorRightEnc = new Encoder(Constants.ELEVATOR_RIGHT_PORT_1, Constants.ELEVATOR_RIGHT_PORT_2);
    limitSwitchLeft = new DigitalInput(Constants.ELEVATOR_LIMIT_LEFT);
    limitSwitchRight = new DigitalInput(Constants.ELEVATOR_LIMIT_RIGHT);
    elevator = new Elevator(elevatorLeft, elevatorRight, limitSwitchLeft, limitSwitchRight, elevatorLeftEnc, elevatorRightEnc);

    throttleMotor = new WPI_VictorSPX(Constants.CLIMB_MANIPULATOR);
    throttle = new Throttle(throttleMotor);

    ultra = new Ultrasonic(Constants.ULTRASONIC_PING, Constants.ULTRASONIC_ECHO);
    Ultrasonic.setAutomaticMode(true);

    ahrs = new AHRS(SPI.Port.kMXP);

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
    joy = new Joystick(0);

    intakeButton = new JoystickButton(joy, Constants.INTAKE_BUTTON);
    transportButton = new JoystickButton(joy, Constants.TRANSPORT_BUTTON);
    pulleyButton = new JoystickButton(joy, Constants.PULLEY_BUTTON);
    tiltButtonUp = new JoystickButton(joy, Constants.TILT_BUTTON_UP);
    shooterTeleop = new JoystickButton(joy, Constants.SHOOTER_TELEOP);
    tiltDownButton = new JoystickButton(joy, Constants.TILT_BUTTON_DOWN);
    tiltAuto = new JoystickButton(joy, Constants.TILT_AUTO_BUTTON);
    shootAll = new JoystickButton(joy, Constants.SHOOT_ALL_BUTTON);
    moveElevatorUp = new JoystickButton(joy, Constants.ELEVATOR_UP_BUTTON);
    moveElevatorDown = new JoystickButton(joy, Constants.ELEVATOR_DOWN_BUTTON);
  

    intakeButton.whileHeld(new MoveIntake(Constants.INTAKE_TELEOP_SPEED));
    transportButton.whenPressed(new MoveTransport(Constants.TRANSPORT_TELEOP_SPEED));
    pulleyButton.whenPressed(new MovePulley(Constants.PULLEY_TELEOP_SPEED));
    tiltButtonUp.whileHeld(new MoveTilt(Constants.TILT_SPEED)); 
    shooterTeleop.whileHeld(new MoveShooterTeleop(Constants.SHOOTER_TELEOP_SPEED));
    tiltDownButton.whileHeld(new MoveTilt(-Constants.TILT_SPEED));
    tiltAuto.whenPressed(new MoveTiltAuto(Constants.TILT_SPEED));
    moveElevatorUp.whileHeld(new MoveElevator(Constants.ELEVATOR_SPEED));
    moveElevatorDown.whileHeld(new MoveElevator(-Constants.ELEVATOR_SPEED));
    shootAll.whenPressed(new ShootAll(Constants.TIME_BETWEEN_CELLS, Constants.TIME_AFTER_CELLS,1));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() 
  {
    return new AethiaCenterThreeCells();
  }

  public static DriveTrain getDriveTrain(){return driveTrain;}
  public static SpeedControllerGroup getLeftSCG(){return leftDrive;}
  public static SpeedControllerGroup getRightSCG(){return rightDrive;}
  public static DifferentialDrive getDiffDrive(){return drive;}
  public static AHRS getAHRS(){return ahrs;}
  public static Joystick getJoy(){return joy;}
  public static Intake getIntake(){return intake;}
  public static Transport getTransport(){return transport;}
  public static Pulley getPulley(){return pulley;}
  public static Tilt getTilt(){return tilt;}
  public static Shooter getShooter(){return shooter;}
  public static Ultrasonic getUltrasonic(){return ultra;}
  public static Elevator getElevator(){return elevator;}
  public Command getTurn(){ return new PointTurn(90);}
}