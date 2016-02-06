// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// C++ from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


#include "Drive.h"

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR

Drive::Drive(int SensitivityScale): Command() {
    m_SensitivityScale = SensitivityScale;
        // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	Requires(Robot::driveControl.get());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
}

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR

// Called just before this Command runs the first time
void Drive::Initialize() {

}

// Called repeatedly when this Command is scheduled to run
void Drive::Execute() {
	std::shared_ptr<Joystick> joystick = Robot::oi->getJoystick();
	float x = ScaleAxis(joystick->GetX(), m_SensitivityScale);
	float y = ScaleAxis(joystick->GetY(), m_SensitivityScale);
	Robot::driveControl.get()->TakeJoystickInputs(x, y);
}

// Make this return true when this Command no longer needs to run execute()
bool Drive::IsFinished() {
	Robot::driveControl.get()->TakeJoystickInputs(0, 0);
    return false;
}

// Called once after isFinished returns true
void Drive::End() {

}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
void Drive::Interrupted() {

}

/**
 * Scale Axis by a given factor
 */
float Drive::ScaleAxis(float axis, int scaleFactor) {
	// Scaling the input to exponential curve. x = input from stick -1..1, f=factor
	// Where as f increases the slower the ramp to full speed across low values of |x|
	// and the faster the ramp to full for higher values of |x|. f must be 2 or greater and
	// a whole number. Value needs to be multiplied by -1 if x<0
	// scaled = ((f^(|x|))-1/(f-1)

	float scaled = ((pow(scaleFactor, abs(axis)))-1)/(scaleFactor-1);
	if (axis < 0) {
		scaled = scaled * -1;
	}

	return scaled;
}
