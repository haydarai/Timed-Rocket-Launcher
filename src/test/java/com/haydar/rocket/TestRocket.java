package com.haydar.rocket;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRocket {

    private RocketController controller;

    @Before
    public void setUp() {
        MockRocketSystem rocketSystem = new MockRocketSystem();
        MockCommand command = new MockCommand();
        this.controller = new RocketController(rocketSystem, command);
    }

    @Test
    public void test() {
        try {
            // calling the fireUp method
            controller.fireUp("IGNITE 0 1000 AT 0\nIGNITE 1 500 AT 2\nSHUTOFF 0 AT 5\nSHUTOFF 1 AT 6");

            // remaining commands: "IGNITE 0 1000 AT 0, IGNITE 1 500 AT 2, SHUTOFF 0 AT 5, SHUTOFF 1 AT 6"
            assertEquals(4, controller.getCommands().size()); // Check how many commands left.

            // remaining commands: "IGNITE 1 500 AT 2, SHUTOFF 0 AT 5, SHUTOFF 1 AT 6"
            Thread.sleep(100); // Added a 0.1 second delay to wait for the first command to be executed.
            assertEquals(3, controller.getCommands().size()); // Check how many commands left.

            // remaining commands: "SHUTOFF 0 AT 5, SHUTOFF 1 AT 6"
            Thread.sleep(2000); // Delay between 0th second to 2nd second.
            assertEquals(2, controller.getCommands().size()); // Check how many commands left.

            // remaining commands: "SHUTOFF 1 AT 6"
            Thread.sleep(3000); // Delay between 2nd second to 5th second.
            assertEquals(1, controller.getCommands().size()); // Check how many commands left.

            // remaining commands: ""
            Thread.sleep(1000); // Delay between 5th second to 6th second.
            assertEquals(0, controller.getCommands().size()); // Check how many commands left.
        } catch (StringFormatException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
