package com.haydar.rocket;

import com.haydar.rocket.helpers.CommandHelper;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class RocketController {

    private ICommand command;
    private IRocketSystem rocketSystem;
    private List<String> commands; // Used for test to calculate how many commands remains

    RocketController(MockRocketSystem rocketSystem, MockCommand command) {
        this.rocketSystem = rocketSystem;
        this.command = command;
    }

    List<String> getCommands() {
        return commands;
    }

    void fireUp (String controlString) throws StringFormatException {
        if (this.command.isValid(controlString)) {
            commands = CommandHelper.analyze(controlString);
            for (String c : commands) {
                String[] splittedCommand = c.split(" ");
                if (splittedCommand[0].equalsIgnoreCase("ignite")) {
                    int nozzleId = Integer.valueOf(splittedCommand[1]);
                    int pressure = Integer.valueOf(splittedCommand[2]);
                    int time = Integer.valueOf(splittedCommand[4]);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println(rocketSystem.ignite(nozzleId, pressure, time));
                            commands.remove(c); // So that on test I can check the size of the ArrayList
                        }
                    }, time * 1000);
                } else if (splittedCommand[0].equalsIgnoreCase("shutoff")) {
                    int nozzleId = Integer.valueOf(splittedCommand[1]);
                    int time = Integer.valueOf(splittedCommand[3]);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println(rocketSystem.shutOff(nozzleId, time));
                            commands.remove(c); // So that on test I can check the size of the ArrayList
                        }
                    }, time * 1000);
                }
            }
        } else {
            throw new StringFormatException("Command format isn't valid");
        }
    }
}
