package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Random;

// just for fun: https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
public class LED {
    private RevBlinkinLedDriver lights;
    private ElapsedTime timer = new ElapsedTime();
    public boolean isPartyTime = false;
    private static final double PARTY_INTERVAL = 0.5;
    private RevBlinkinLedDriver.BlinkinPattern[] patterns = RevBlinkinLedDriver.BlinkinPattern.values();
    private Random random = new Random();

    public void init(HardwareMap map) {
        lights = map.get(RevBlinkinLedDriver.class, "lights");
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
    }

    public void startParty() {
        isPartyTime = true;
        this.setRandomPattern();
    }

    public void endParty() {
        isPartyTime = false;
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
    }

    public void update() {
        if(isPartyTime && timer.seconds() >= PARTY_INTERVAL) {
            this.setRandomPattern();
        }
    }

    public void setRandomPattern() {
        RevBlinkinLedDriver.BlinkinPattern pattern = patterns[random.nextInt(patterns.length)];
        lights.setPattern(pattern);
        timer.reset();
    }
}
