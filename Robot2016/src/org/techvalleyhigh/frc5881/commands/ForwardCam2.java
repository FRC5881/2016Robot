// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.techvalleyhigh.frc5881.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.techvalleyhigh.frc5881.Robot;
import org.techvalleyhigh.frc5881.subsystems.Chassis;

/**
 * Command to select the second forward camera, and set the robot orientation forward.
 */
public class ForwardCam2 extends Command {

    public ForwardCam2() {

        requires(Robot.chassis);

        // Enables this command to run when the robot is disabled.
        // USE WITH CAUTION
        setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.chassis.setOrientationAndVideo(false, SmartDashboard.getString(Chassis.FORWARD_CAM_2));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
