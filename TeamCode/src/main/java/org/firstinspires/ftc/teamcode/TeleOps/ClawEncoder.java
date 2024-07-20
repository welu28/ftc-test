package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ClawEncoder extends LinearOpMode {
    DcMotor motor;
    double ticks = 4000; // find on goBilda
    double target; // target position for motor to turn to

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotor.class, "-----insert motor name here-----");
        telemetry.addData("Status", "Initialized");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while(opModeIsActive()) {
            if(gamepad1.a) turn(1/2);
            else if(gamepad1.b) reset();
            telemetry.addData("Ticks:", motor.getCurrentPosition());
        }
    }

    public void turn(int turns) {
        target = ticks * turns;
        motor.setTargetPosition((int)target);
        motor.setPower(0.5); //change accordingly
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void reset() {
        motor.setTargetPosition(0);
        motor.setPower(0.7);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
