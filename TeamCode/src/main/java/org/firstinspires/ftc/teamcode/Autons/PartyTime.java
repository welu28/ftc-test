package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.LED;

@Autonomous
public class PartyTime extends LinearOpMode {
    private LED lights = new LED();

    @Override
    public void runOpMode() throws InterruptedException {
        lights.init(hardwareMap);
        waitForStart();

        lights.startParty();
        while(opModeIsActive()) {
            lights.update();
        }
        sleep(50); // prevent busy waiting
        lights.endParty();
    }
}
