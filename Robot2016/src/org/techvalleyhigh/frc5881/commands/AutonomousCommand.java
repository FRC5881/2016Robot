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

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.techvalleyhigh.frc5881.subsystems.*;

/**
 * Autonomous Command Group. Contains the step-by-step instructions for the various
 * autonomous commands.
 */
public class AutonomousCommand extends CommandGroup {


    public AutonomousCommand(String Autoroutine) {
 
    	// If we're not doing nothing, lower the arm...
    	if (Autoroutine != "null") {
    		addSequential(new AutonomousArmClose());
    	}
    	
    	// All comments about distance forward from auto line are off by 2'
    	if (Autoroutine == "reach") {
    		// Step 1 -> Forward 141.22" == 11.768'
    		addSequential(new AssistedDrive(distanceAddingRobotLength(9.768), 0));

    	} else if (Autoroutine == "gunit") {
    		// Step 1 -> Forward 150" == 12.5'
    		addSequential(new AssistedDrive(distanceAddingRobotLength(10.5), 0));
    	} else if (Autoroutine == "gunit-moat") {
    		addSequential(new AssistedDrive(distanceAddingRobotLength(15), 0));
    	} else if (Autoroutine == "gunit-rockwall") {
    		addSequential(new AssistedDrive(distanceAddingRobotLength(13.5), 0));
    	} else if (Autoroutine == "spyscore") {
    		// Step 1 -> Turn clockwise 60 degrees & Forward 59" == 4.916'
    		addSequential(new AssistedDrive(4.916, 60));
    		// Step 2 -> Turn counter-clockwise 90 degrees & Forward 97.5" == 8.125'
    		addSequential(new AssistedDrive(8.125, -90));

    	} else if (Autoroutine == "offsetbreechright") {
    		// Step 1 -> Forward 70" == 5.833'
    		addSequential(new AssistedDrive(distanceAddingRobotLength(3.833), 0));
    		// Step 2 -> Turn clockwise 90 degrees & Forward 50" == 4.166'
    		addSequential(new AssistedDrive(distanceAddingRobotLength(4.166), 90));
    		// Step 3 -> Turn counter-clockwise 90 degrees & Forward 80" == 6.666'
    		addSequential(new AssistedDrive(distanceAddingRobotLength(6.666), -90));

    	} else if (Autoroutine == "offsetbreechleft") {
    		//Step 1 -> Forward 70" == 5.833'
    		addSequential(new AssistedDrive(distanceAddingRobotLength(3.833), 0));
    		//Step 2 -> Turn counter-clockwise 90 degrees & Forward 50" == 4.166'
    		addSequential(new AssistedDrive(distanceAddingRobotLength(4.166), -90));
    		//Step 3 -> Turn counter-clockwise 90 degrees & Forward 80" == 6.666'
    		addSequential(new AssistedDrive(distanceAddingRobotLength(6.666), 90));
    	} else if (Autoroutine == "null") {
    		// No autonomous
    	}

     }

    /**
     * Adds the robot length to the distance given to adjust for various measurement points
     * during autonomous design. For example, if an autonomous drive is to go 10 feed forward
     * from the auto line, but the measurement point was the other side ofthe robot, this
     * will adjust the distance.
     * 
     * @param distance feet to travel
     * @return number of feet of travel plus the length of the robot.
     */
    private double distanceAddingRobotLength(double distance) {
    	// Robot Length 30.5" or 2.5416'

    	return distance + 2.5416;
    }
}
