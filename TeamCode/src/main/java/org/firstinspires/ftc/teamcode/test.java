package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp()
public class test extends LinearOpMode
{
    private Robot robot = new Robot();


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        DcMotor fL = robot.frontLeft;
        DcMotor bL = robot.backLeft;
        DcMotor fR = robot.frontRight;
        DcMotor bR = robot.backRight;
        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.left_stick_y != 0) {
                fL.setPower(gamepad1.left_stick_y);
                bL.setPower(gamepad1.left_stick_y);
            }
            if(gamepad1.right_stick_y != 0) {
                fR.setPower(gamepad1.right_stick_y);
                bR.setPower(gamepad1.right_stick_y);
            }
            else if(gamepad1.right_stick_x != 0) {
                fR.setPower(-gamepad1.right_stick_x);
                bL.setPower(-gamepad1.right_stick_x);
                fL.setPower(gamepad1.right_stick_x);
                bR.setPower(gamepad1.right_stick_x);
            }
            fL.setPower(0);
            fR.setPower(0); 
            bL.setPower(0);
            bR.setPower(0);
        }
    }
}
