package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class mecanum extends LinearOpMode {
    private DcMotor fL;
    private DcMotor bL;
    private DcMotor fR;
    private DcMotor bR;

    @Override
    public void runOpMode() throws InterruptedException {
        fL = hardwareMap.dcMotor.get("frontLeft");
        bL = hardwareMap.dcMotor.get("frontRight");
        fR = hardwareMap.dcMotor.get("backLeft");
        bR = hardwareMap.dcMotor.get("backRight");

        fR.setDirection(DcMotorSimple.Direction.REVERSE);
        bR.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            move(gamepad.left_stick_y, gamepad.left_stick_x, gamepad.right_stick_x);
        }
    }

    // left joystick controls forward/backward and strafe, right controls turning
    public void move(double power, double strafe, double turn) {
        // normalization factor to make sure motor powers are within valid range
        double norm = Math.max(Math.abs(power) + Math.abs(strafe) + Math.abs(turn), 1);
        double fLPow = (power + strafe + turn) / norm;
        double bLPow = (power - strafe + turn) / norm;
        double fRPow = (power - strafe - turn) / norm;
        double bRPOw = (power + strafe - turn) / norm;

        setPowers(fLPow, bLPow, fRPow, bRPOw);
    }

    public void setPowers(double fLPow, double bLPow, double fRPow, double bRPOw) {
        fL.setPower(fLPow);
        bL.setPower(bLPow);
        fR.setPower(fRPow);
        bR.setPower(bRPOw);
    }
}