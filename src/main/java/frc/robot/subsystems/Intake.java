// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private final SparkMax intakeMotor = new SparkMax(Constants.IntakePorts.kintakePortMotor, MotorType.kBrushless);

  private final SparkMaxConfig intakeConfig = new SparkMaxConfig();

  private PIDController pidElevator = new PIDController(0.2, 0, 0.01);

  private double targetPosition;
  private boolean pidMode;
  private final double tolerance = 2;


  /** Creates a new Intake. */
  public Intake() {

    intakeConfig.inverted(false).idleMode(IdleMode.kBrake).smartCurrentLimit(40);
    intakeMotor.configure(intakeConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
  }


  public void runIntake(double speed) {
    pidMode = false;
    intakeMotor.set(speed);
  }

  //======Elevator ===========

  public void positionOne(){
    pidMode = true;
    targetPosition = 0;
  }
  
  public void positionTwo(){
    pidMode = true;
    targetPosition = 50;
  }

  public void targetPosition(double position){
    targetPosition = position;
  }

  public double currentPosition(){
    return intakeMotor.getEncoder().getPosition();
  }

  public void positionPID(){
    double output = pidElevator.calculate(currentPosition(), targetPosition);
    intakeMotor.set(output);
  }

  public boolean isAtPosition(){
    return Math.abs(currentPosition() - targetPosition) <= tolerance;
  }
  
  @Override
  public void periodic() {
    if(pidMode){
      positionPID();
    }
  }
}
