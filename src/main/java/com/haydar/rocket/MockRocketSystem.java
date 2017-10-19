package com.haydar.rocket;

public class MockRocketSystem implements IRocketSystem {

    @Override
    public String ignite(int nozzleId, int pressure, int time) {
        int remaining = nozzleId % 10;
        String ordinal = "th";
        if (remaining == 1) {
            ordinal = "st";
        } else if (remaining == 2) {
            ordinal = "nd";
        } else if (remaining == 3) {
            ordinal = "rd";
        }
        return "Fire up the " + nozzleId + ordinal + " nozzle by " + pressure + " pound pressure per second at " + time;
    }

    @Override
    public String shutOff(int nozzleId, int time) {
        int remaining = nozzleId % 10;
        String ordinal = "th";
        if (remaining == 1) {
            ordinal = "st";
        } else if (remaining == 2) {
            ordinal = "nd";
        } else if (remaining == 3) {
            ordinal = "rd";
        }
        return "Shut off the " + nozzleId + ordinal + " nozzle at " + time;
    }
}
