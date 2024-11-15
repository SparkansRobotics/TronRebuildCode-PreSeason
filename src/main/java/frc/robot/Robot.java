// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;



public class Robot extends TimedRobot {


  private Joystick controller;

  private CANSparkMax frontLeftMotor;
  private CANSparkMax backLeftMotor;
  private CANSparkMax frontRightMotor;
  private CANSparkMax backRightMotor;

  private double speed = 0.85;


  @Override
  public void robotInit() {

    // Change to [MotorType.kBrushless] if using brushless motors
    frontLeftMotor = new CANSparkMax(1, MotorType.kBrushed);
    backLeftMotor = new CANSparkMax(2, MotorType.kBrushed);
    frontRightMotor = new CANSparkMax(3, MotorType.kBrushed);
    backRightMotor = new CANSparkMax(4, MotorType.kBrushed);

    controller = new Joystick(0);

  }

  @Override
  public void teleopPeriodic() {

    double leftWheelsRaw = controller.getRawAxis(1) + controller.getRawAxis(2)*0.7;
    double rightWheelsRaw = controller.getRawAxis(1) - controller.getRawAxis(2)*0.7;


    double leftWheels = 0;
    double rightWheels = 0;


    
    // This is not required but good practice
    // Normalize the motor values
    if (leftWheelsRaw > 1) {
      leftWheels = 1;
    } else if (leftWheelsRaw < -1) {
      leftWheels = -1;
    } else {
      leftWheels = leftWheelsRaw;
    }

    if (rightWheelsRaw > 1) {
      rightWheels = 1;
    } else if (rightWheelsRaw < -1) {
      rightWheels = -1;
    } else {
      rightWheels = rightWheelsRaw;
    }


    // Most tank drive gearboxes only work when the motors for each side are 
    // rotating opposite directions. Depending on your gearbox, you may have 
    // to invert the given values like so.
    frontLeftMotor.set(-leftWheels * speed);
    backLeftMotor.set(-leftWheels * speed);
    frontRightMotor.set(rightWheels * speed);
    backRightMotor.set(rightWheels * speed);

  }


  @Override
  public void disabledPeriodic() {

  }

  @Override
  public void robotPeriodic() {

  }
}
