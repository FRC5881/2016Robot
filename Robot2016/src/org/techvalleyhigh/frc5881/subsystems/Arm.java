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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.techvalleyhigh.frc5881.RobotMap;


/**
 *
 */
public class Arm extends Subsystem {
    /**
     * String used for SmartDashboard key for Spinner Eject Speed
     */
    public static final String SPINNER_EJECT_SPEED = "Spinner Eject Speed";

    /**
     * String used for SmartDashboard key for Spinner Intake Speed
     */
    public static final String SPINNER_INTAKE_SPEED = "Spinner Intake Speed";

    /**
     * String used for SmartDashboard key for Articulation Speed
     */
    public static final String ARTICULATION_SPEED = "Articulation Speed";

    private final SpeedController spinnerSpeedController = RobotMap.armSpinnerSpeedController;
    private final DigitalInput armUpperLimitSwitch = RobotMap.armArmUpperLimitSwitch;
    private final DigitalInput armLowerLimitSwitch = RobotMap.armArmLowerLimitSwitch;
    private final SpeedController armSpeedController = RobotMap.armArmSpeedController;
    private final Ultrasonic ultrasonicBallSensor = RobotMap.armUltrasonicBallSensor;
    private final DigitalInput ballCaptureLimitSwitch = RobotMap.armBallCaptureLimitSwitch;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    /**
     * Create the subsystem with a default name.
     */
    public Arm() {
        super();
        initSmartDashboard();
    }

    /**
     * Create the subsystem with the given name.
     */
    public Arm(String name) {
        super(name);
        initSmartDashboard();
    }

    /**
     * Initialize the SmartDashboard values.
     */
    private void initSmartDashboard() {
        SmartDashboard.putNumber(SPINNER_EJECT_SPEED, 0.5);
        SmartDashboard.putNumber(SPINNER_INTAKE_SPEED, 0.3);
        SmartDashboard.putNumber(ARTICULATION_SPEED, 0.3);
    }

    public void initDefaultCommand() {

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Commands the spinner to intake a ball, spinning at the motor value from the SmartDashboard.
     * Care is taken to make sure, no matter the value entered, it spins in a "+" direction.
     */
    public void spinIntake() {
        double value = SmartDashboard.getNumber(SPINNER_INTAKE_SPEED, 0.3);
        spinnerSpeedController.set(value < 0 ? value * -1 : value);
    }

    /**
     * Commands the spinner to stop.
     */
    public void spinStop() {
        spinnerSpeedController.set(0);
    }

    /**
     * Commands the spinner to eject a ball, spinning at the motor value from the SmartDashboard.
     * Care is taken to make sure, no matter the value entered, it spins in a "-" direction.
     */
    public void spinEject() {
        double value = SmartDashboard.getNumber(SPINNER_EJECT_SPEED, 0.5);
        spinnerSpeedController.set(value > 0 ? value * -1 : value);
    }

    /**
     * Commands the articulation motor to lower the arm, spinning at the motor value from the
     * SmartDashboard. In this context "down" is considered toward the intake front of the robot
     * when the arm is in a position perpendicular to the floor in a nominal state.
     */
    public void articulateDown() {
        double value = SmartDashboard.getNumber(ARTICULATION_SPEED, 0.3);
        armSpeedController.set(value > 0 ? value * -1 : value);
    }

    /**
     * Commands the articulation motor to stop.
     */
    public void articulateStop() {
        armSpeedController.set(0);
    }

    /**
     * Commands the articulation motor to raise the arm, spinning at the motor value from the
     * SmartDashboard. In this context "up" is considered away the intake front of the robot
     * when the arm is in a position perpendicular to the floor in a nominal state.
     */
    public void articulateUp() {
        double value = SmartDashboard.getNumber(ARTICULATION_SPEED, 0.3);
        armSpeedController.set(value < 0 ? value * -1 : value);
    }
}

