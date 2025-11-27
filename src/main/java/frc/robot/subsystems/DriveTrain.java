// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class DriveTrain extends SubsystemBase {

  private final SparkMax leftMaster = new SparkMax(Constants.Drive.kLeftMasterPort, MotorType.kBrushed);
  private final SparkMax leftFollower = new SparkMax(Constants.Drive.kLeftFollowerPort, MotorType.kBrushed);
  private final SparkMax rightMaster = new SparkMax(Constants.Drive.kRightMasterPort, MotorType.kBrushed);
  private final SparkMax rightFollower = new SparkMax(Constants.Drive.kRightFollowerPort, MotorType.kBrushed);

  private final SparkMaxConfig leftMasterConfig = new SparkMaxConfig();
  private final SparkMaxConfig leftFollowerConfig  = new SparkMaxConfig();
  private final SparkMaxConfig rightMasterConfig = new SparkMaxConfig();
  private final SparkMaxConfig rightFollowerConfig  = new SparkMaxConfig();

  private final DifferentialDrive arcadeDrive = new DifferentialDrive(leftMaster, rightMaster);

  /** Creates a new Drive. */
  public DriveTrain() {

    leftMasterConfig.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(40);
    leftFollowerConfig.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(40).follow(leftMaster);
    rightMasterConfig.inverted(false).idleMode(IdleMode.kBrake).smartCurrentLimit(40);
    rightFollowerConfig.inverted(false).idleMode(IdleMode.kBrake).smartCurrentLimit(40).follow(rightMaster);

    leftMaster.configure(leftMasterConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    leftFollower.configure(leftFollowerConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    rightMaster.configure(rightMasterConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    rightFollower.configure(rightFollowerConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    
    arcadeDrive.setSafetyEnabled(true);
    arcadeDrive.setExpiration(0.1);
    arcadeDrive.setMaxOutput(1.0);
    arcadeDrive.setDeadband(0.02);
        
  }

  public void arcadeDrive(double speed, double rotation) {
    arcadeDrive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
