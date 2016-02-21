// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// C++ from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


#include "RobotMap.h"
#include "LiveWindow/LiveWindow.h"


// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=ALLOCATION
std::shared_ptr<SpeedController> RobotMap::armSpinnerSpeedController;
std::shared_ptr<DigitalInput> RobotMap::armArmUpperLimitSwitch;
std::shared_ptr<DigitalInput> RobotMap::armArmLowerLimitSwitch;
std::shared_ptr<SpeedController> RobotMap::armArmSpeedController;
std::shared_ptr<Ultrasonic> RobotMap::armUltrasonicBallSensor;
std::shared_ptr<DigitalInput> RobotMap::armBallCaptureLimitSwitch;
std::shared_ptr<SpeedController> RobotMap::driveControlLeftSpeedController1;
std::shared_ptr<SpeedController> RobotMap::driveControlLeftSpeedController2;
std::shared_ptr<SpeedController> RobotMap::driveControlRightSpeedController1;
std::shared_ptr<SpeedController> RobotMap::driveControlRightSpeedController2;
std::shared_ptr<RobotDrive> RobotMap::driveControlRobotDrive;
std::shared_ptr<PowerDistributionPanel> RobotMap::chassisPowerDistributionPanel;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=ALLOCATION

std::shared_ptr<ADXRS450_Gyro> RobotMap::driveControlDigitalGyro;

void RobotMap::init() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    LiveWindow *lw = LiveWindow::GetInstance();

    armSpinnerSpeedController.reset(new Talon(4));
    lw->AddActuator("Arm", "Spinner Speed Controller", std::static_pointer_cast<Talon>(armSpinnerSpeedController));
    
    armArmUpperLimitSwitch.reset(new DigitalInput(2));
    lw->AddSensor("Arm", "Arm Upper Limit Switch", armArmUpperLimitSwitch);
    
    armArmLowerLimitSwitch.reset(new DigitalInput(3));
    lw->AddSensor("Arm", "Arm Lower Limit Switch", armArmLowerLimitSwitch);
    
    armArmSpeedController.reset(new Talon(5));
    lw->AddActuator("Arm", "Arm Speed Controller", std::static_pointer_cast<Talon>(armArmSpeedController));
    
    armUltrasonicBallSensor.reset(new Ultrasonic(0, 1));
    lw->AddSensor("Arm", "Ultrasonic Ball Sensor", armUltrasonicBallSensor);
    
    armBallCaptureLimitSwitch.reset(new DigitalInput(4));
    lw->AddSensor("Arm", "Ball Capture Limit Switch", armBallCaptureLimitSwitch);
    
    driveControlLeftSpeedController1.reset(new Victor(0));
    lw->AddActuator("Drive Control", "Left Speed Controller 1", std::static_pointer_cast<Victor>(driveControlLeftSpeedController1));
    
    driveControlLeftSpeedController2.reset(new Victor(1));
    lw->AddActuator("Drive Control", "Left Speed Controller 2", std::static_pointer_cast<Victor>(driveControlLeftSpeedController2));
    
    driveControlRightSpeedController1.reset(new Victor(2));
    lw->AddActuator("Drive Control", "Right Speed Controller 1", std::static_pointer_cast<Victor>(driveControlRightSpeedController1));
    
    driveControlRightSpeedController2.reset(new Victor(3));
    lw->AddActuator("Drive Control", "Right Speed Controller 2", std::static_pointer_cast<Victor>(driveControlRightSpeedController2));
    
    driveControlRobotDrive.reset(new RobotDrive(driveControlLeftSpeedController1, driveControlLeftSpeedController2,
              driveControlRightSpeedController1, driveControlRightSpeedController2));
    
    driveControlRobotDrive->SetSafetyEnabled(true);
        driveControlRobotDrive->SetExpiration(0.1);
        driveControlRobotDrive->SetSensitivity(0.5);
        driveControlRobotDrive->SetMaxOutput(1.0);

    chassisPowerDistributionPanel.reset(new PowerDistributionPanel(0));
    lw->AddSensor("Chassis", "PowerDistributionPanel", chassisPowerDistributionPanel);
    


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    driveControlDigitalGyro.reset(new ADXRS450_Gyro(SPI::kOnboardCS0));
    lw->AddSensor("Drive Control", "DigitalGyro", driveControlDigitalGyro);

}
