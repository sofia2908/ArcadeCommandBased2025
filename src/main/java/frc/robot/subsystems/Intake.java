// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private final DigitalInput SensorPresencia = new DigitalInput(0);
  private final SparkMax intakeMotor = new SparkMax(Constants.IntakePorts.kintakePortMotor, MotorType.kBrushless);
  private final SparkMaxConfig intakeConfig = new SparkMaxConfig();


  /** Creates a new Intake. */
  public Intake() {

    intakeConfig.inverted(false).idleMode(IdleMode.kBrake).smartCurrentLimit(40);
    intakeMotor.configure(intakeConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
  }


  public void runIntake(double speed) {
    intakeMotor.set(speed);
  }

  public boolean getSensor(){
    return SensorPresencia.get();
  }

  public void stopMotor (){
    intakeMotor.set(0);

  }

  @Override
  public void periodic() {

    SmartDashboard.putBoolean("Sensor de Presencia", !SensorPresencia.get());

    // This method will be called once per scheduler run
  }


 
}
