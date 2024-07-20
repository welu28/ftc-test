package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class MotorEncoder extends LinearOpMode {
    private Drivetrain drivetrain;
    private DcMotor fL;
    private DcMotor bL;
    private DcMotor fR;
    private DcMotor bR;

    @Override
    public void runOpMode() {
        drivetrain = new Drivetrain();
        drivetrain.init(hardwareMap);

        // Assign local variables to the drivetrain motors
        fL = drivetrain.fL;
        bL = drivetrain.bL;
        fR = drivetrain.fR;
        bR = drivetrain.bR;

        // Reset encoders
        resetEncoders();
        waitForStart();

        // Drive forward for 1000 encoder ticks
        driveToPosition(1000, 0.5);
        drivetrain.setPower(0);
    }

    private void resetEncoders() {
        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void driveToPosition(int targetPosition, double power) {
        fL.setTargetPosition(targetPosition);
        bL.setTargetPosition(targetPosition);
        fR.setTargetPosition(targetPosition);
        bR.setTargetPosition(targetPosition);

        drivetrain.setPowers(power, power, power, power);

        while (opModeIsActive() && fL.isBusy() && bL.isBusy() &&
                fR.isBusy() && bR.isBusy()) {
            // Wait until the motors reach the target position
            telemetry.addData("Status", "Driving to position");
            telemetry.addData("Front Left", fL.getCurrentPosition());
            telemetry.addData("Back Left", bL.getCurrentPosition());
            telemetry.addData("Front Right", fR.getCurrentPosition());
            telemetry.addData("Back Right", bR.getCurrentPosition());
            telemetry.update();
        }
    }
}
