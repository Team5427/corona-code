package frc.robot.commands.UsefulAuto;

import java.util.List;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.PathWeaver;

public class TrajectoryAuton extends SequentialCommandGroup {
    public TrajectoryAuton(){

    }
    public static SequentialCommandGroup FollowTrajectory () {
        var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
              Constants.ksVolts, 
              Constants.kvVoltSecondsPerMeter, 
              Constants.kaVoltSecondsSquaredPerMeter), 
            Constants.kDriveKinematics, 10);
      
          // Create config for trajectory
          TrajectoryConfig config = new TrajectoryConfig(
            Constants.kMaxSpeedMetersPerSecond, 
            Constants.kMaxAccelerationMetersPerSecondSquared).setKinematics(Constants.kDriveKinematics).addConstraint(autoVoltageConstraint);
      
          // An example trajectory to follow.  All units in meters.
          Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
              // Start at the origin facing the +X direction
              new Pose2d(0, 0, new Rotation2d(0)),
              // Pass through these two interior waypoints, making an 's' curve path
              List.of(
                  new Translation2d(1, 1),
                  new Translation2d(2, -1)
              ),
              // End 3 meters straight ahead of where we started, facing forward
              new Pose2d(3, 0, new Rotation2d(0)),
              // Pass config
              config
          );
      
          RamseteCommand ramseteCommand = new RamseteCommand(
              PathWeaver.trajectory,
              RobotContainer.odometryDriveTrain::getPose,
              new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
              new SimpleMotorFeedforward(Constants.ksVolts,
                                        Constants.kvVoltSecondsPerMeter,
                                        Constants.kaVoltSecondsSquaredPerMeter),
              Constants.kDriveKinematics,
              RobotContainer.odometryDriveTrain::getWheelSpeeds,
              new PIDController(Constants.kPDriveVel, 0, 0),
              new PIDController(Constants.kPDriveVel, 0, 0),
              // RamseteCommand passes volts to the callback
              RobotContainer.odometryDriveTrain::tankDriveVolts,
              RobotContainer.odometryDriveTrain
          );
      
          // Reset odometry to the starting pose of the trajectory.
          RobotContainer.odometryDriveTrain.resetOdometry(exampleTrajectory.getInitialPose());
      
          // Run path following command, then stop at the end.
          return ramseteCommand.andThen(() -> RobotContainer.odometryDriveTrain.tankDriveVolts(0, 0));
    }    

    }
