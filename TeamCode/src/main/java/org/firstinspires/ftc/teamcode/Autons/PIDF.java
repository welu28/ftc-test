package org.firstinspires.ftc.teamcode.Autons;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;


import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
@TeleOp
public class PIDF extends LinearOpMode {
    private PIDController controller;

    public static double P = 0; // public so you can change on FTC dashboard
    public static double I = 0;
    public static double D = 0;
    public static double F = 0;


    public static int target = 100;
    private final double ticks_in_degree = 384.5 / 180;

    private DcMotor rightLift;
    private DcMotor leftLift;


    @Override
    public void runOpMode() throws InterruptedException {
        controller = new PIDController(P, I, D);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        rightLift = hardwareMap.get(DcMotor.class, "rightLift");
        rightLift.setDirection(DcMotor.Direction.REVERSE);
        leftLift = hardwareMap.get(DcMotor.class, "leftLift");
        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            controller.setPID(P, I, D);
            int currentPosition = rightLift.getCurrentPosition();
            double power = controller.calculate(currentPosition, target);
            double feedForward = Math.cos(Math.toRadians(target/ticks_in_degree)) * F;
            power += feedForward;
            rightLift.setPower(power);
            leftLift.setPower(power);

            telemetry.addData("position:", currentPosition);
            telemetry.addData("target:", target);
            telemetry.update();
        }
    }
}

