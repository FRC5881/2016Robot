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
     * Default value for Auto Gyro Tolerance
     */
    private static final double AUTO_GYRO_TOLERANCE_DEFAULT = 2;

    /**
     * String used for SmartDashboard value for Full Speed
     */
    private static final String FULL_SPEED_VALUE = "FULL";

    /**
     * String used for SmartDashboard value for Half Speed
     */
    private static final String HALF_SPEED_VALUE = "HALF";

    /**
     * String used for SmartDashboard value for 3/4 Speed
     */
    private static final String THREE_QUARTERS_SPEED_VALUE = "THREEQUARTERS";

    /**
     * String used for SmartDashboard key for Joystick X-Axis Deadzone
     */
    private static final String JOYSTICK_DEADZONE_X = "Joystick X-Axis Deadzone";

    /**
     * Default value for Joystick X-Axis Deadzone
     */
    private static final double JOYSTICK_DEADZONE_X_DEFAULT = 0.1;

    /**
     * String used for SmartDashboard key for Joystick Y-Axis Deadzone
     */
    private static final String JOYSTICK_DEADZONE_Y = "Joystick Y-Axis Deadzone";

    /**
     * Default value for Joystick Y-Axis Deadzone
     */
    private static final double JOYSTICK_DEADZONE_Y_DEFAULT = 0.1;

    private static final String LEFT_DRIVE_PID_KP = "Left Drive PID Kp";
    private static final double LEFT_DRIVE_PID_KP_DEFAULT = 0.013;
    private static final String LEFT_DRIVE_PID_KI = "Left Drive PID Ki";
    private static final double LEFT_DRIVE_PID_KI_DEFAULT = 0.001;
    private static final String LEFT_DRIVE_PID_KD = "Left Drive PID Kd";
    private static final double LEFT_DRIVE_PID_KD_DEFAULT = 0.012;

    private static final String RIGHT_DRIVE_PID_KP = "Right Drive PID Kp";
    private static final double RIGHT_DRIVE_PID_KP_DEFAULT = 0.013;
    private static final String RIGHT_DRIVE_PID_KI = "Right Drive PID Ki";
    private static final double RIGHT_DRIVE_PID_KI_DEFAULT = 0.001;
    private static final String RIGHT_DRIVE_PID_KD = "Right Drive PID Kd";
    private static final double RIGHT_DRIVE_PID_KD_DEFAULT = 0.012;

    private static final String GYRO_PID_KP = "Gyro PID Kp";
    private static final double GYRO_PID_KP_DEFAULT = 0.14;
    private static final String GYRO_PID_KI = "Gyro PID Ki";
    private static final double GYRO_PID_KI_DEFAULT = 0.002;
    private static final String GYRO_PID_KD = "Gyro PID Kd";
    private static final double GYRO_PID_KD_DEFAULT = 0.045;

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
     * Initialize the SmartDashboard values.
     */
    private void initSmartDashboard() {
        calibrateGyro();

        // Gryo tolerance - used in auto to provide non-perfect directions
        SmartDashboard.putNumber(AUTO_GYRO_TOLERANCE, AUTO_GYRO_TOLERANCE_DEFAULT);

        autoSpeedChooser = new SendableChooser();
        autoSpeedChooser.addDefault("Full Speed", FULL_SPEED_VALUE);
        autoSpeedChooser.addObject("Half-Speed", HALF_SPEED_VALUE);
        autoSpeedChooser.addObject("3/4 Speed", THREE_QUARTERS_SPEED_VALUE);
        SmartDashboard.putData("Autonomous Speed Selection", autoSpeedChooser);

        SmartDashboard.putNumber(JOYSTICK_DEADZONE_X, JOYSTICK_DEADZONE_X_DEFAULT);
        SmartDashboard.putNumber(JOYSTICK_DEADZONE_Y, JOYSTICK_DEADZONE_Y_DEFAULT);

        SmartDashboard.putNumber(LEFT_DRIVE_PID_KP, LEFT_DRIVE_PID_KP_DEFAULT);
        SmartDashboard.putNumber(LEFT_DRIVE_PID_KI, LEFT_DRIVE_PID_KI_DEFAULT);
        SmartDashboard.putNumber(LEFT_DRIVE_PID_KD, LEFT_DRIVE_PID_KD_DEFAULT);

        SmartDashboard.putNumber(RIGHT_DRIVE_PID_KP, RIGHT_DRIVE_PID_KP_DEFAULT);
        SmartDashboard.putNumber(RIGHT_DRIVE_PID_KI, RIGHT_DRIVE_PID_KI_DEFAULT);
        SmartDashboard.putNumber(RIGHT_DRIVE_PID_KD, RIGHT_DRIVE_PID_KD_DEFAULT);

        SmartDashboard.putNumber(GYRO_PID_KP, GYRO_PID_KP_DEFAULT);
        SmartDashboard.putNumber(GYRO_PID_KI, GYRO_PID_KI_DEFAULT);
        SmartDashboard.putNumber(GYRO_PID_KD, GYRO_PID_KD_DEFAULT);
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
        return (int) SmartDashboard.getNumber(AUTO_GYRO_TOLERANCE, AUTO_GYRO_TOLERANCE_DEFAULT);
    }

    /**
     * Update the SmartDashboard with current values.
     */
    private void updateDashboard() {
        SmartDashboard.putNumber("Gyro Heading", getGyroAngle());
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
    public void rawDrive(double move, double turn) {
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
        int scaleFactor = scale;

        if (scaleFactor < 2) {
            scaleFactor = 2;
        }

        Joystick joystick = Robot.oi.getJoystick();
        double x = joystick.getX();
        double y = joystick.getY();

        double deadZoneX = Math.abs(SmartDashboard.getNumber(JOYSTICK_DEADZONE_X, JOYSTICK_DEADZONE_X_DEFAULT));
        double deadZoneY = Math.abs(SmartDashboard.getNumber(JOYSTICK_DEADZONE_Y, JOYSTICK_DEADZONE_Y_DEFAULT));

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

        Position scaled = scaleAxis(x, y, scaleFactor);

        robotDrive.arcadeDrive(-1 * scaled.y, -3 * scaled.x / 4, true);
    }

    private String getAutoSpeedSelection() {
        return (String) autoSpeedChooser.getSelected();
    }

    public double getLeftPIDKp() {
        return SmartDashboard.getNumber(LEFT_DRIVE_PID_KP, LEFT_DRIVE_PID_KP_DEFAULT);
    }

    public double getLeftPIDKi() {
        return SmartDashboard.getNumber(LEFT_DRIVE_PID_KI, LEFT_DRIVE_PID_KI_DEFAULT);
    }

    public double getLeftPIDKd() {
        return SmartDashboard.getNumber(LEFT_DRIVE_PID_KD, LEFT_DRIVE_PID_KD_DEFAULT);
    }

    public double getRightPIDKp() {
        return SmartDashboard.getNumber(RIGHT_DRIVE_PID_KP, RIGHT_DRIVE_PID_KP_DEFAULT);
    }

    public double getRightPIDKi() {
        return SmartDashboard.getNumber(RIGHT_DRIVE_PID_KI, RIGHT_DRIVE_PID_KI_DEFAULT);
    }

    public double getRightPIDKd() {
        return SmartDashboard.getNumber(RIGHT_DRIVE_PID_KD, RIGHT_DRIVE_PID_KD_DEFAULT);
    }

    public double getGyroPIDKp() {
        return SmartDashboard.getNumber(GYRO_PID_KP, GYRO_PID_KP_DEFAULT);
    }

    public double getGyroPIDKi() {
        return SmartDashboard.getNumber(GYRO_PID_KI, GYRO_PID_KI_DEFAULT);
    }

    public double getGyroPIDKd() {
        return SmartDashboard.getNumber(GYRO_PID_KD, GYRO_PID_KD_DEFAULT);
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
            case THREE_QUARTERS_SPEED_VALUE:
                return 0.75f;
            default:
                System.out.println("Unknown autoSpeedSelection: " + speed);
                return 1f;
        }
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

