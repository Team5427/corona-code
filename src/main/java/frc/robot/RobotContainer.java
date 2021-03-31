/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

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
import frc.robot.commands.FindProximity;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MovePulley;
import frc.robot.commands.MoveShooterTeleop;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.auto.BarrelRacing;
import frc.robot.commands.auto.MoveStraightPID;
import frc.robot.commands.MoveTilt;
import frc.robot.commands.MoveTiltAuto;
import frc.robot.commands.auto.PointTurn;
import frc.robot.commands.auto.Slalom;
import frc.robot.commands.ResetSensors;
import frc.robot.commands.StopVision;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pulley;
import frc.robot.subsystems.Transport;
import edu.wpi.first.wpilibj2.command.Command;
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

  //we will increment this in our commands.
  public static int loop = 0;
  public static boolean canShoot = false;

  private static Joystick joy;
  private static Button intakeButton;
  private static Button transportButton;
  private static Button pulleyButton;
  private static Button shooterTeleop;

  private static Button tiltButtonUp;
  private static Button tiltDownButton;
  private static Button stopAimbot;
  private static Button tiltAuto;

  private final SpeedController frontLeft, rearLeft;
  private final SpeedController frontRight,rearRight;
  
  private static SpeedControllerGroup leftDrive;
  private static SpeedControllerGroup rightDrive;
  private static DifferentialDrive drive;
  private static DriveTrain driveTrain;
  private static Transport transport;
  private static SpeedController transportMotor;

  private static SpeedController intakeMotor;
  private static Intake intake;

  private static SpeedController pulleyMotor;
  private static AnalogInput pulleyProximity;
  private static Pulley pulley;

  private static AHRS ahrs;
  private static Encoder encLeft;
  private static Encoder encRight;
    
  private static AnalogInput transportProximity;
  private static AnalogInput transportProximityTwo;

  private static SpeedController shooterMotorTop;
  private static SpeedController shooterMotorBottom;

  private static Shooter shooter;

  private static Encoder shooterTopEnc;
  private static Encoder shooterBottomEnc;

  private static SpeedController tiltMotor;
  private static DigitalInput tiltSwitch;
  private static Tilt tilt;

  private static Ultrasonic ultra;

  private static Button proximityDistance;
  private static Button shooting;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {
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
    tiltSwitch = new DigitalInput(Constants.LIMIT_SWITCH_TILT);
    tilt = new Tilt(tiltMotor, tiltSwitch);

    pulleyMotor = new WPI_VictorSPX(Constants.PULLEY_MOTOR);
    pulleyProximity = new AnalogInput(Constants.PULLEY_PROXIMITY_SENSOR_PORT);
    pulley = new Pulley(pulleyMotor, pulleyProximity);

    ahrs = new AHRS(SPI.Port.kMXP);

    //encoders have 1440 as PPR and 360 CPR
    encRight = new Encoder(5, 4);
    encRight.setDistancePerPulse(Constants.DISTANCE_PER_PULSE); // cicrumference divided by 1440 (feet)
    //encRight.setReverseDirection(true);
    encLeft = new Encoder(6, 7);
    encLeft.setDistancePerPulse(Constants.DISTANCE_PER_PULSE); // cicrumference divided by 1440 (feet)

    shooterTopEnc = new Encoder(10, 11);
    shooterBottomEnc = new Encoder(12, 13);

    shooterMotorTop = new WPI_VictorSPX(Constants.SHOOTER_MOTOR_TOP);
    shooterMotorBottom = new WPI_VictorSPX(Constants.SHOOTER_MOTOR_BOTTOM);
    shooter = new Shooter(shooterMotorTop, shooterMotorBottom, shooterTopEnc, shooterBottomEnc);


    ultra = new Ultrasonic(22, 23);
    ultra.setAutomaticMode(true);

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
    stopAimbot = new JoystickButton(joy, 9);
    tiltAuto = new JoystickButton(joy, 10);
    proximityDistance = new JoystickButton(joy, 12);

    shooting = new JoystickButton(joy, 11);

    intakeButton.whileHeld(new MoveIntake(Constants.INTAKE_TELEOP_SPEED));
    transportButton.whenPressed(new MoveTransport(Constants.TRANSPORT_TELEOP_SPEED));
    pulleyButton.whenPressed(new MovePulley(Constants.PULLEY_TELEOP_SPEED));
    tiltButtonUp.whileHeld(new MoveTilt(Constants.TILT_SPEED)); //change this timeout number
    shooterTeleop.whileHeld(new MoveShooterTeleop(1.0));
    tiltDownButton.whileHeld(new MoveTilt(-Constants.TILT_SPEED));
    stopAimbot.whenPressed(new StopVision(),true);
    tiltAuto.whenPressed(new MoveTiltAuto(Constants.TILT_SPEED));
    shooting.whenPressed(new ResetSensors());

    proximityDistance.whenPressed(new FindProximity());
  }


  //accessors that take up space
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() 
  {
    return new BarrelRacing();
    // return new PointTurn(80);
  }

  public static DriveTrain getDriveTrain(){return driveTrain;}
  public static SpeedControllerGroup getLeftSCG(){return leftDrive;}
  public static SpeedControllerGroup getRightSCG(){return rightDrive;}
  public static DifferentialDrive getDiffDrive(){return drive;}
  public static AHRS getAHRS(){return ahrs;}
  public static Encoder getEncLeft(){return encLeft;}
  public static Encoder getEncRight(){return encRight;}
  public static Joystick getJoy(){return joy;}
  public static Intake getIntake(){return intake;}
  public static Transport getTransport(){return transport;}
  public static Pulley getPulley(){return pulley;}
  public static Tilt getTilt(){return tilt;}
  public static Shooter getShooter(){return shooter;}
  public static Ultrasonic getUltrasonic(){return ultra;}
  public Command getTurn(){ return new PointTurn(90);}
}