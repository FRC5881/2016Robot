// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// C++ from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.




#include "Chassis.h"
#include "../RobotMap.h"
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=INCLUDES
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=INCLUDES
#include "SmartDashboard/SmartDashboard.h"
#include "../Classes/DynamicCameraServer.h"

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

Chassis::Chassis() : Subsystem("Chassis") {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    powerDistributionPanel = RobotMap::chassisPowerDistributionPanel;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    isInverted = false;
    SmartDashboard::PutString("Orientation", "Normal");
    SmartDashboard::PutString("Forward Camera Name", "cam0");
    SmartDashboard::PutString("Reverse Camera Name", "cam1");

    DynamicCameraServer::GetInstance()->SetQuality(50);
    DynamicCameraServer::GetInstance()->SetSize(0);
    DynamicCameraServer::GetInstance()->StartAutomaticCapture("cam0");
}

void Chassis::InitDefaultCommand() {
    // Set the default command for a subsystem here.
    // SetDefaultCommand(new MySpecialCommand());
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
}


// Put methods for controlling this subsystem
// here. Call these from Commands.

bool Chassis::IsOrientationInverted() {
	return isInverted;
}

void Chassis::InvertOrientation() {
	isInverted = ! isInverted;

	SmartDashboard::PutString("Orientation", isInverted ? "Inverted" : "Normal");

	std::string	cam = !isInverted ? SmartDashboard::GetString("Forward Camera Name", "cam0") :
			SmartDashboard::GetString("Reverse Camera Name", "cam1");

	std::cout << "Bot Now " << isInverted << " Cam Selection " << cam << std::endl;

	DynamicCameraServer::GetInstance()->SwitchAutomaticCapture(cam.c_str());
}
