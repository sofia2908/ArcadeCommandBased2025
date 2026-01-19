// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;

public class IntakeDanna extends SubsystemBase {
  /** Creates a new IntakeDanna. */
  

   SparkMax intakeMotor = new SparkMax(10, MotorType.kBrushless);
   SparkMaxConfig IntakeConfig = new SparkMaxConfig();

   PIDController pidController = new PIDController(Constants.IntakeDanna.kP, Constants.IntakeDanna.kI, Constants.IntakeDanna.kD);
   
    double targetPosition;
    boolean pidMode;
    double tolerance = 2;
  

  public IntakeDanna () {
    IntakeConfig.idleMode(IdleMode.kBrake).smartCurrentLimit(40);
    intakeMotor.configure(IntakeConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
  }

  public void runIntake(double speed) {
    pidMode = false;
    intakeMotor.set(speed);
  }

  //ELEVADOR 

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

  public boolean isAtposition () {
    return Math.abs(currentPosition() - targetPosition) <= tolerance;
  }

  public void positionPID(){
    double output = pidController.calculate(currentPosition(), targetPosition);
    intakeMotor.set(output);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(pidMode){
      positionPID();
    }
    
  }
}
