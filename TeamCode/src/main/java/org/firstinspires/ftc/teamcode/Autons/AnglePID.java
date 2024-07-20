/*
TO DO
- change the imu commands, since they are designed for a different model
- set up ftc dashbaord
 */

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

// set pid constants and change on ftc dashboard
// https://acmerobotics.github.io/ftc-dashboard/gettingstarted.html
@Autonomous
public class AnglePID extends LinearOpMode {
    public static double kP = 2; // proportional gain
    public static double kI = 0.0; // integral gain (keep at 0, don't need for angles)
    public static double kD = 0.001; // derivative gain

    Drivetrain drive = new Drivetrain();
    ElapsedTime timer = new ElapsedTime(); // for integral, don't actually need here

    double integralSum = 0;
    double lastError = 0;

    private BHI260IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        drive.init(hardwareMap);
        imu = hardwareMap.get(BHI260IMU.class, "imu");
        // configure imu settings
        BHI260IMU.Parameters settings = new BHI260IMU.Parameters();
        settings.mode = BHI260IMU.SensorMode.IMU; // default is ndof
        settings.angleUnit = BHI260IMU.AngleUnit.DEGREES; // radians bad
        imu.initialize(settings);

        double angle = 90;
        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Target IMU Angle", angle);
            /*
            displays current angle. intrinsic is orientation relative to robot's axes
            ZYX is order of angles reported, first angle is angle around Z axis
             */
            telemetry.addData("Current IMU Angle", imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
            double power = PIDControl(angle, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
            drive.setTurnPower(power);
            telemetry.update();
        }
    }

    public double PIDControl(double reference, double state) {
        double error = shortest(reference - state);
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / (timer.seconds());
        timer.reset();
        lastError = error;
        return (error * kP) + (derivative * kD) + (integralSum * kI);
    }


    // determines the shortest distance to reach a degree.
    // for example, we will go 90 degrees clockwise instead of 270 degrees cc
    public double shortest(double degrees) {
        while(degrees > 180){
            degrees -= 360;
        }
        while(degrees < -180){
            degrees += 360;
        }
        return degrees;
    }
}
