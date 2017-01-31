// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4415.ProtoButterflyBot.subsystems;


import org.usfirst.frc4415.ProtoButterflyBot.Robot;
import org.usfirst.frc4415.ProtoButterflyBot.RobotMap;
import org.usfirst.frc4415.ProtoButterflyBot.commands.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	AHRS ahrs;
	boolean changeDrive = true;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController speedController1 = RobotMap.driveTrainSpeedController1;
    private final SpeedController speedController2 = RobotMap.driveTrainSpeedController2;
    private final SpeedController speedController3 = RobotMap.driveTrainSpeedController3;
    private final SpeedController speedController4 = RobotMap.driveTrainSpeedController4;
    private final RobotDrive robotDrive4 = RobotMap.driveTrainRobotDrive4;
    private final DoubleSolenoid doubleSolenoid = RobotMap.driveTrainDoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ButterDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void gyroMecanumDrive(){
    	if ( Robot.oi.protoButterflyJoystick.getRawButton(1)) {
            ahrs.reset();
        }
        try {
            /* Use the joystick X axis for lateral movement,            */
            /* Y axis for forward movement, and Z axis for rotation.    */
            /* Use navX MXP yaw angle to define Field-centric transform */
            robotDrive4.mecanumDrive_Cartesian(Robot.oi.protoButterflyJoystick.getX(), Robot.oi.protoButterflyJoystick.getY(), 
            		Robot.oi.protoButterflyJoystick.getTwist(), 0);
            //ahrs.getAngle()
        } catch( RuntimeException ex ) {
            DriverStation.reportError("Error communicating with drive system:  " + ex.getMessage(), true);
        }
        Timer.delay(0.005);		// wait for a motor update time
    }
    
    public void getGryo(){
    	try {
			/***********************************************************************
			 * navX-MXP:
			 * 
			 * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.            
			 * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * navX-Micro:
			 * - Communication via I2C (RoboRIO MXP or Onboard) and USB.
			 * - See http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * Multiple navX-model devices on a single robot are supported.
			 ************************************************************************/
            ahrs = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
    }
    
    public void invertMotorsMecanum(){
    	RobotMap.driveTrainRobotDrive4.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        RobotMap.driveTrainRobotDrive4.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    }
    
    public void arcadeDrive(){
    	robotDrive4.arcadeDrive(Robot.oi.getProtoButterflyJoystick());	
    }
    
    public void invertMotorsArcade(){
    	RobotMap.driveTrainRobotDrive4.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
    	RobotMap.driveTrainRobotDrive4.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    }
    	
    public void toggleMecanum(){
    	doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void toggleArcade(){
    	doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void pneuoff(){
    	doubleSolenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    public void changeGyroMecanumDriveCode(){
    	changeDrive = true;
    }
    
    public void changeArcadeDriveCode(){
    	changeDrive = false;
    }
    
    public boolean getChangeDrive(){
    	return changeDrive;
    }
}

