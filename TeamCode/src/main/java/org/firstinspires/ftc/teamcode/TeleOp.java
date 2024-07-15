package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class TeleOp extends LinearOpMode {

    private Drivetrain drive = new Drivetrain();

    @Override
    public void runOpMode() {
        drive.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            double power = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            drive.move(power, strafe, turn);

            telemetry.addData("Power", power);
            telemetry.addData("Strafe", strafe);
            telemetry.addData("Turn", turn);
            telemetry.update();
        }
    }
}
