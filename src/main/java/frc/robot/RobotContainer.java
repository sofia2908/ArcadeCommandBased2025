// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.AutoRobot;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.GetCoral;
import frc.robot.commands.IntakeSensor;
import frc.robot.commands.LeaveCoral;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.PS4Controller;

import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

public class RobotContainer {

  private final DriveTrain driveTrain = new DriveTrain();
  private final Intake intake = new Intake();
  private final PS4Controller ps4Controller = new PS4Controller(0);
  private final CommandPS4Controller commandPS4Controller = new CommandPS4Controller(0);

  private final DriveWithJoystick driveWithJoystickCmd = new DriveWithJoystick(driveTrain, ps4Controller);
  private final LeaveCoral leaveCoralCmd = new LeaveCoral(intake);
  private final GetCoral getCoralCmd = new GetCoral(intake);
  private final AutoRobot autoRobotCmd = new AutoRobot(intake);
  private final IntakeSensor intakeSensorCmd = new IntakeSensor(intake);

  public RobotContainer() {
    configureBindings();

    intake.setDefaultCommand(intakeSensorCmd);
    driveTrain.setDefaultCommand(driveWithJoystickCmd);

  }

  private void configureBindings() {
    commandPS4Controller.circle().whileTrue(leaveCoralCmd);
    commandPS4Controller.square().whileTrue(getCoralCmd);
    commandPS4Controller.triangle().onTrue(autoRobotCmd);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");

  }
}
