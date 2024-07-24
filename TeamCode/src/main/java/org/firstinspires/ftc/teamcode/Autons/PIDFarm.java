package org.firstinspires.ftc.teamcode.Autons;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class PIDFarm extends LinearOpMode {
    private PIDController controller;

    public static double kP = 0; // public so you can change on FTC dashboard
    public static double kI = 0;
    public static double kD = 0;
    public static double kF = 0;

    public static int target = 0;
    private final double ticks_in_degree = 700.0 / 100;
    private DcMotorEx arm;

    @Override
    public void runOpMode() throws InterruptedException {
        // init
        controller = new PIDController(kP, kI, kD);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        arm = hardwareMap.get(DcMotorEx.class, "arm"); // CHANGE THIS

        while(opModeIsActive() && !isStopRequested()) {
            controller.setPID(kP, kI, kD);
            int currentPosition = arm.getCurrentPosition();
            double power = controller.calculate(currentPosition, target);
            double feedForward = Math.cos(Math.toRadians(target/ticks_in_degree)) * kF;
            power += feedForward;
            arm.setPower(power);

            telemetry.addData("Position:", currentPosition);
            telemetry.addData("target", target);
            telemetry.update();
        }
    }
}
