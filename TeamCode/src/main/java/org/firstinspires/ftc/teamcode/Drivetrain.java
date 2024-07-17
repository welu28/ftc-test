package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class Drivetrain {
    private DcMotor fL;
    private DcMotor bL;
    private DcMotor fR;
    private DcMotor bR;

    public void init(HardwareMap map) {
        fL = map.dcMotor.get("frontLeft");
        bL = map.dcMotor.get("frontRight");
        fR = map.dcMotor.get("backLeft");
        bR = map.dcMotor.get("backRight");

        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);
    }

    // left joystick controls forward/backward and strafe, right controls turning
    public void move(double power, double strafe, double turn) {
        double fLPow = power + strafe + turn;
        double bLPow = power - strafe + turn;
        double fRPow = power - strafe - turn;
        double bRPOw = power + strafe - turn;

        setPowers(fLPow, bLPow, fRPow, bRPOw);
    }

    public void setPowers(double fLPow, double bLPow, double fRPow, double bRPOw) {
        fL.setPower(fLPow);
        bL.setPower(bLPow);
        fR.setPower(fRPow);
        bR.setPower(bRPOw);
    }

    public void setPower(double pow) {
        fL.setPower(pow);
        bL.setPower(pow);
        fR.setPower(pow);
        bR.setPower(pow);
    }

    public void setTurnPower(double pow) {
        fL.setPower(pow);
        bL.setPower(pow);
        fR.setPower(-pow);
        bR.setPower(-pow);
    }
}
