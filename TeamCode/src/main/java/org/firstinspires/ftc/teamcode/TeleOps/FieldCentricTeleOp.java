package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Imu;

@TeleOp
public class FieldCentricTeleOp extends LinearOpMode {

    private Drivetrain drivetrain;
    private Imu imu;

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain();
        drivetrain.init(hardwareMap);

        imu = new Imu();
        imu.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            if (gamepad1.options) {
                imu.imu.resetYaw();
            }

            double botHeading = imu.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // counteract imperfect strafing

            drivetrain.move(rotY, rotX, rx);
        }
    }
}
