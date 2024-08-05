package org.firstinspires.ftc.teamcode.Autons;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

@Config
@TeleOp
public class PIDForward extends LinearOpMode {
    private PIDController controller;

    public static double P = 0; // public so you can change on FTC dashboard
    public static double I = 0;
    public static double D = 0;

    public static int targetPosition = 5000; // target position in encoder ticks

    private Drivetrain drivetrain = new Drivetrain();

    @Override
    public void runOpMode() throws InterruptedException {
        controller = new PIDController(P, I, D);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        drivetrain.init(hardwareMap);

        drivetrain.fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drivetrain.fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drivetrain.bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drivetrain.bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        drivetrain.fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drivetrain.fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drivetrain.bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drivetrain.bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            controller.setPID(P, I, D);
            int currentPosition = getAveragePosition();
            double power = controller.calculate(currentPosition, targetPosition);

            drivetrain.setPowers(power);

            telemetry.addData("Current Position:", currentPosition);
            telemetry.addData("Target Position:", targetPosition);
            telemetry.addData("Power:", power);
            telemetry.update();
        }
    }

    private int getAveragePosition() {
        int fLPosition = drivetrain.fL.getCurrentPosition();
        int fRPosition = drivetrain.fR.getCurrentPosition();
        int bLPosition = drivetrain.bL.getCurrentPosition();
        int bRPosition = drivetrain.bR.getCurrentPosition();

        return (fLPosition + fRPosition + bLPosition + bRPosition) / 4;
    }
}