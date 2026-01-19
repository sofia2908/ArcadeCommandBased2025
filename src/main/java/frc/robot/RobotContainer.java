// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.IntakeDanna;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.InCoral;
import frc.robot.commands.LeaveCoral;
import frc.robot.commands.PosicionArriba;
import frc.robot.commands.Posicionuno;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

public class RobotContainer {

  
    
    
      
            private final DriveTrain driveTrain = new DriveTrain();
            private final PS4Controller ps4Controller = new PS4Controller(0);
            private final CommandPS4Controller commandPS4Controller = new CommandPS4Controller(0);
            private final IntakeDanna intakeDanna = new IntakeDanna();
            
          
    private final DriveWithJoystick driveWithJoystickCmd = new DriveWithJoystick(driveTrain, ps4Controller); 
          
          
    private final InCoral InCoralCmd = new InCoral(intakeDanna);
    private final LeaveCoral leaveCoralCmd = new LeaveCoral(intakeDanna);
    private final Posicionuno posicionunoCmd = new Posicionuno(intakeDanna);
    private final PosicionArriba posicionArribaCmd = new PosicionArriba(intakeDanna);
  
    
    
    
  
    public RobotContainer() {
      configureBindings();
  
      driveTrain.setDefaultCommand(driveWithJoystickCmd);
    }
  
    private void configureBindings() {
      
      commandPS4Controller.cross().whileTrue(InCoralCmd);
      commandPS4Controller.triangle().whileTrue(leaveCoralCmd);
    commandPS4Controller.square().whileTrue(posicionArribaCmd);
    commandPS4Controller.circle().whileTrue(posicionunoCmd);



  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
