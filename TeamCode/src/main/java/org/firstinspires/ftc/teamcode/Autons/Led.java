package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.LED;

@Autonomous
public class Led extends LinearOpMode {
    private LED led;

    @Override
    public void runOpMode() throws InterruptedException {
        led = new LED();
        led.init(hardwareMap);
        led.startParty();
        while(opModeIsActive()) {
            led.update();
        }
        led.endParty();
    }
}
