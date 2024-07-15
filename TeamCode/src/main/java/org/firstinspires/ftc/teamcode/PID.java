package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

// set pid constants and change on ftc dashboard
// https://acmerobotics.github.io/ftc-dashboard/gettingstarted.html
@Config
@TeleOp
public class PID extends LinearOpMode {
    public static double kP = 2; // proportional gain
    public static double kI = 0.0; // integral gain (keep at 0, don't need for angles)
    public static double kD = 0.0; // derivative gain

    Drivetrain drive = new Drivetrain();
    ElapsedTime timer = new ElapsedTime();

    double integralSum = 0;
    double lastError = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init(hardwareMap);

    }

    public double PIDControl(double reference, double state) {
        double error = angleWrap(reference - state);
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / (timer.seconds());
        timer.reset();
        lastError = error;
        return (error * kP) + (derivative * kD) + (integralSum * kI);
    }


    // determines the shortest distance to reach a degree.
    // for example, we will go 90 degrees clockwise instead of 270 degrees cc
    public double angleWrap(int degrees) {
        while(degrees > 180){
            degrees -= 360;
        }
        while(degrees < -180){
            degrees += 360;
        }
        return degrees;
    }
}
