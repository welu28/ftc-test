package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    public DcMotor fL;
    public DcMotor bL;
    public DcMotor fR;
    public DcMotor bR;

    public void init(HardwareMap map) {
        fL = map.dcMotor.get("frontLeft");
        bL = map.dcMotor.get("frontRight");
        fR = map.dcMotor.get("backLeft");
        bR = map.dcMotor.get("backRight");

        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);


    }

    // left joystick controls forward/backward and strafe, right controls turning
    public void move(double power, double strafe, double turn) {
        // normalize so doesn't exceed 1
        double norm = Math.max(Math.abs(power) + Math.abs(strafe) + Math.abs(turn), 1);
        double fLPow = power + strafe + turn;
        double bLPow = power - strafe + turn;
        double fRPow = power - strafe - turn;
        double bRPOw = power + strafe - turn;

        setPowers(fLPow/norm, bLPow/norm, fRPow/norm, bRPOw/norm);
    }

    public void setPowers(double fLPow, double bLPow, double fRPow, double bRPOw) {
        fL.setPower(fLPow);
        bL.setPower(bLPow);
        fR.setPower(fRPow);
        bR.setPower(bRPOw);
    }

    public void setPowers(double pow) {
        this.setPowers(pow, pow, pow, pow);
    }

    public void setTurnPower(double pow) {
        fL.setPower(pow);
        bL.setPower(pow);
        fR.setPower(-pow);
        bR.setPower(-pow);
    }
}
