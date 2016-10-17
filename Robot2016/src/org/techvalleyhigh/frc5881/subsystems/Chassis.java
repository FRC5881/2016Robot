// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.techvalleyhigh.frc5881.subsystems;

import org.techvalleyhigh.frc5881.RobotMap;
import org.techvalleyhigh.frc5881.util.DynamicCameraServer;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Chassis extends Subsystem {
	
	/**
	 * String used for SmartDashboard key for Forward Camera 1
	 */
	public final static String FORWARD_CAM_1 = "Forward Camera 1";
	
	/**
	 * String used for SmartDashboard key for Forward Camera 2
	 */
	public final static String FORWARD_CAM_2 = "Forward Camera 2";
	
	/**
	 * String used for SmartDashboard key for Reverse Camera
	 */
	public final static String REVERSE_CAM = "Reverse Camera";

    private final PowerDistributionPanel powerDistributionPanel = RobotMap.chassisPowerDistributionPanel;
    
    /**
     * Boolean status of chassis inversion.
     */
    private boolean isInverted = false;

    /**
     * Create the subsystem with a default name.
     */
    public Chassis() {
    	super();
    	initSmartDashboard();
    	initCameraServer();
    }
    
    /**
     * Create the subsystem with the given name.
     */
    public Chassis(String name) {
    	super(name);
    	initSmartDashboard();
    	initCameraServer();
    }
    
    /**
     * Initialize the SmartDashboard values.
     */
    private void initSmartDashboard() {
    	SmartDashboard.putString("Orientation", isInverted ? "Inverted" : "Normal");
    	SmartDashboard.putString(FORWARD_CAM_1, "cam0");
    	SmartDashboard.putString(FORWARD_CAM_2, "cam1");
    	SmartDashboard.putString(REVERSE_CAM, "cam2");
    }
    
    /**
     * Initialize the DynamicCameraServer and settings.
     */
    private void initCameraServer() {
    	DynamicCameraServer.getInstance().setQuality(50);
    	DynamicCameraServer.getInstance().setSize(0);
    	DynamicCameraServer.getInstance().startAutomaticCapture("cam0");
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Returns the status of the chassis inversion.
     * 
     * @return boolean TRUE if the chassis is invented (forward drive is from the rear), otherwise
     *         false.
     */
    public boolean isOrientationInverted() {
    	return isInverted;
    }
    
    /**
     * Set the chassis orientation (drive direction) and video feed.
     * 
     * @param inverted boolean TRUE if the bot is to lead from the back, FALSE to lead from the front
     * @param dashboardCamLabel The SmartDashboard label to use to select the current cam, or null
     */
    public void setOrientationAndVideo(final boolean inverted, final String dashboardCamLabel) {
    	isInverted = inverted;
    	
    	SmartDashboard.putString("Orientation", isInverted ? "Inverted" : "Normal");
    	
    	String finalCamLabel = dashboardCamLabel != null ? dashboardCamLabel :
    		(isInverted ? REVERSE_CAM : FORWARD_CAM_1);
    	
    	DynamicCameraServer.getInstance().switchAutomaticCapture(finalCamLabel);
    }
}

