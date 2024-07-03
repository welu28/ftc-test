package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp()
public class test extends LinearOpMode
{
    private DcMotor fL;
    private DcMotor fR;
    private DcMotor bL;
    private DcMotor bR;


    @Override
    public void runOpMode() throws InterruptedException {
        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        fR = hardwareMap.get(DcMotor.class, "frontRight");
        bL = hardwareMap.get(DcMotor.class, "backLeft");
        bR = hardwareMap.get(DcMotor.class, "backRight");
        telemetry.addData("Status", "Initialized");
        bL.setDirection(DcMotor.Direction.FORWARD);
        fL.setDirection(DcMotor.Direction.FORWARD);
        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);
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