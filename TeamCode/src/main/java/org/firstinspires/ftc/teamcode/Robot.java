package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    public DcMotor fL;
    public DcMotor bL;
    public DcMotor fR;
    public DcMotor bR;

    public void init(HardwareMap hardwareMap) {
        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        bL = hardwareMap.get(DcMotor.class, "frontLeft");
        fR = hardwareMap.get(DcMotor.class, "frontLeft");
        bR = hardwareMap.get(DcMotor.class, "frontLeft");

        bL.setDirection(DcMotor.Direction.FORWARD);
        fL.setDirection(DcMotor.Direction.FORWARD);
        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);
    }
}
