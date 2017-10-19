package com.haydar.rocket;

public interface IRocketSystem {

    public String ignite(int nozzleId, int pressure, int time);

    public String shutOff(int nozzleId, int time);
}
