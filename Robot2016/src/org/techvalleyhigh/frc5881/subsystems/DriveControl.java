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

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.techvalleyhigh.frc5881.Robot;
import org.techvalleyhigh.frc5881.RobotMap;
import org.techvalleyhigh.frc5881.commands.Drive;


/**
 *
 */
public class DriveControl extends Subsystem {

    /**
     * String used for SmartDashboard key for Auto Gyro Tolerance
     */
    private static final String AUTO_GYRO_TOLERANCE = "Auto Gyro Tolerance (+- Deg)";

    /**
     * String used for SmartDashboard key for Full Power 10' Time
     */
    private static final String FULL_POWER_TIME = "Full Power 10' Time";

    /**
     * String used for SmartDashboard value for Full Speed
     */
    private static final String FULL_SPEED_VALUE = "FULL";

    /**
     * String used for SmartDashboard key for Half Power 10' Time
     */
    private static final String HALF_POWER_TIME = "Half Power 10' Time";

    /**
     * String used for SmartDashboard value for Half Speed
     */
    private static final String HALF_SPEED_VALUE = "HALF";

    /**
     * String used for SmartDashboard key for One-Third Power 10' Time
     */
    private static final String ONETHIRD_POWER_TIME = "One-Third Power 10' Time";

    /**
     * String used for SmartDashboard value for 1/3rd Speed
     */
    private static final String ONETHIRD_SPEED_VALUE = "THIRD";

    /**
     * String used for SmartDashboard key for Joystick X-Axis Deadzone
     */
    private static final String JOYSTICK_DEADZONE_X = "Joystick X-Axis Deadzone";

    /**
     * String used for SmartDashboard key for Joystick Y-Axis Deadzone
     */
    private static final String JOYSTICK_DEADZONE_Y = "Joystick Y-Axis Deadzone";

    private static final PIDController leftDrivePIDController = new PIDController(.2d, .02d, 0, RobotMap.driveControlLeftEncoder, null);
    private static final PIDController rightDrivePIDController = new PIDController(.2d, .02d, 0, RobotMap.driveControlRightEncoder, null);
    private static final PIDController gyroPID = new PIDController(7, 2, 0, RobotMap.driveControlDigitalGyro, null);

    private static final RobotDrive robotDrive = RobotMap.driveControlRobotDrive;

    /**
     * Object for access to the 2016 First Choice 1-axis Gyro on the RoboRIO SPI Port.
     */
    private ADXRS450_Gyro digitalGyro;
    /**
     * Chooser for SmartDashboard to select teh autonomous drive speed.
     */
    private SendableChooser autoSpeedChooser;


    /**
     * Create the subsystem with a default name.
     */
    public DriveControl() {
        super();
        initSmartDashboard();
    }

    /**
     * Create the subsystem with the given name.
     */
    public DriveControl(String name) {
        super(name);
        initSmartDashboard();
    }

    /**
     * Initialize the SmartDashboard values.
     */
    private void initSmartDashboard() {
        calibrateGyro();

        // Timing settings. These are timed numbers measured as the amount of time it takes
        // the robot to move 10' at the given power level.
        SmartDashboard.putNumber(FULL_POWER_TIME, 1.1);
        SmartDashboard.putNumber(HALF_POWER_TIME, 2.2);
        SmartDashboard.putNumber(ONETHIRD_POWER_TIME, 3.3);

        // Gryo tolerance - used in auto to provide non-perfect directions
        SmartDashboard.putNumber(AUTO_GYRO_TOLERANCE, 5);

        autoSpeedChooser = new SendableChooser();
        autoSpeedChooser.addDefault("Full Speed", FULL_SPEED_VALUE);
        autoSpeedChooser.addObject("Half-Speed", HALF_SPEED_VALUE);
        autoSpeedChooser.addObject("1/3rd Speed", ONETHIRD_SPEED_VALUE);
        SmartDashboard.putData("Autonomous Speed Selection", autoSpeedChooser);

        SmartDashboard.putNumber(JOYSTICK_DEADZONE_X, 0.1);
        SmartDashboard.putNumber(JOYSTICK_DEADZONE_Y, 0.1);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new Drive(10));
    }

    public void calibrateGyro() {
        digitalGyro = RobotMap.driveControlDigitalGyro;
        digitalGyro.calibrate();
    }

    /**
     * Gets the current angle as reported by the 1-axis Gyro.
     *
     * @return Current Gyro angle. No drift correction is applied.
     */
    public double getGyroAngle() {
        return digitalGyro.getAngle();
    }

    /**
     * Gets the currently set Gyro Tolerance for Autonomous.
     *
     * @return Number of degrees of tolerance +-
     */
    public int getAutoGyroTolerance() {
        return (int) SmartDashboard.getNumber(AUTO_GYRO_TOLERANCE, 5);
    }

    /**
     * Update the SmartDashboard with current values.
     */
    private void updateDashboard() {
        SmartDashboard.putNumber("Gyro Heading", getGyroAngle());
        SmartDashboard.putNumber("Gyro PID Output", gyroPID.get());
        SmartDashboard.putNumber("Left PID Output", leftDrivePIDController.get());
        SmartDashboard.putNumber("Right PID Output", rightDrivePIDController.get());
    }

    /**
     * Stop all drive motors.
     */
    public void stopDrive() {
        robotDrive.arcadeDrive(0, 0);
    }

    /**
     * Command the drive motors to move and turn without correcting for deadzone or scaling.
     * Joystick input should NOT be fed thru this function.
     *
     * @param move Motor amount to move from -1 to 1
     * @param turn Motor amount to turn from -1 to 1
     */
    public void rawDrive(float move, float turn) {
        updateDashboard();
        robotDrive.arcadeDrive(move, turn, true);
    }

    /**
     * Takes the axis-values from the joystick, applies the deadzone, robot orientation,
     * scales the values, and translates to motion.
     *
     * @param scale Scaling factor to apply, must be a while number of at least 2.
     */
    public void takeJoystickInputs(int scale) {
        if (scale < 2) {
            scale = 2;
        }

        Joystick joystick = Robot.oi.getJoystick();
        double x = joystick.getX();
        double y = joystick.getY();

        double deadZoneX = Math.abs(SmartDashboard.getNumber(JOYSTICK_DEADZONE_X, 0.1));
        double deadZoneY = Math.abs(SmartDashboard.getNumber(JOYSTICK_DEADZONE_Y, 0.1));

        if (x < deadZoneX && x > (deadZoneX * -1)) {
            x = 0;
        }
        if (y < deadZoneY && y > (deadZoneY * -1)) {
            y = 0;
        }

        if (Robot.chassis.isOrientationInverted()) {
            x = -1 * x;
            y = -1 * y;
        }

        Position scaled = scaleAxis(x, y, scale);

        robotDrive.arcadeDrive(-1 * scaled.y, -3 * scaled.x / 4, true);
    }

    /**
     * Gets the amount of time the robot takes to go 10' at full power in seconds.
     *
     * @return Seconds to go 10' at full power.
     */
    private double getFullPowerTime() {
        return SmartDashboard.getNumber(FULL_POWER_TIME, 0.92);
    }

    /**
     * Gets the amount of time the robot takes to go 10' at half power in seconds.
     *
     * @return Seconds to go 10' at half power.
     */
    private double getHalfPowerTime() {
        return SmartDashboard.getNumber(HALF_POWER_TIME, 1.84);
    }

    /**
     * Gets the amount of time the robot takes to go 10' at 1/3rd power in seconds.
     *
     * @return Seconds to go 10' at 1/3rd power.
     */
    private double getThirdPowerTime() {
        return SmartDashboard.getNumber(ONETHIRD_POWER_TIME, 2.76);
    }

    private String getAutoSpeedSelection() {
        return (String) autoSpeedChooser.getSelected();
    }

    /**
     * Returns the float value for the motor drive power for the given set time.
     */
    public float getAutoSpeedValue() {
        String speed = getAutoSpeedSelection();
        switch (speed) {
            case FULL_SPEED_VALUE:
                return 1;
            case HALF_SPEED_VALUE:
                return 0.5f;
            case ONETHIRD_SPEED_VALUE:
                return 0.33333f;
            default:
                System.out.println("Unknown autoSpeedSelection: " + speed);
                return 1f;
        }
    }

    /**
     * Gets the amount of time to go 10' at the selected power level.
     *
     * @return double seconds to tavel 10' at chosen speed
     */
    public double getAutoSetPowerTime() {
        String speed = getAutoSpeedSelection();
        switch (speed) {
            case FULL_SPEED_VALUE:
                return getFullPowerTime();
            case HALF_SPEED_VALUE:
                return getHalfPowerTime();
            case ONETHIRD_SPEED_VALUE:
                return getThirdPowerTime();
        }

        return 1d;
    }

    /**
     * Scale Axis by a given factor. Applies the formula ((f^(|x|))-1/(f-1) where f is the
     * scale factor and x is the axis (x and y independently) to create an exponential
     * acceleration curve used to provide finer joystick input control. Larger factor values
     * produce a flatter beginning and steeper ending curve while smaller numbers produce a more
     * linear result.
     *
     * @param x           x-axis to scale
     * @param y           y-axis to scale
     * @param scaleFactor factor to scale by, must be at least 2
     * @return Position as x, y scaled
     */
    private Position scaleAxis(double x, double y, float scaleFactor) {
        // Scaling the input to exponential curve. x = input from stick -1..1, f=factor
        // Where as f increases the slower the ramp to full speed across low values of |x|
        // and the faster the ramp to full for higher values of |x|. f must be 2 or greater and
        // a whole number. Value needs to be multiplied by -1 if x<0
        // scaled = ((f^(|x|))-1/(f-1)


        double xScaled = ((Math.pow(scaleFactor, Math.abs(x))) - 1) / (scaleFactor - 1) * (x < 0 ? -1 : 1);
        double yScaled = ((Math.pow(scaleFactor, Math.abs(y))) - 1) / (scaleFactor - 1) * (y < 0 ? -1 : 1);

        SmartDashboard.putNumber("Scale Factor", scaleFactor);
        SmartDashboard.putNumber("Y-Scale", yScaled);
        SmartDashboard.putNumber("X-Scale", xScaled);

        return new Position(xScaled, yScaled);
    }

    /**
     * Basic class to hold positional x, y data.
     */
    private class Position {
        public double x;
        public double y;

        /**
         * Create a Position object with the given values
         *
         * @param xValue x-axis position
         * @param yValue y-axis position
         */
        public Position(double xValue, double yValue) {
            x = xValue;
            y = yValue;
        }
    }
}

