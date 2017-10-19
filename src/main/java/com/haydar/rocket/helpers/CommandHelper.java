package com.haydar.rocket.helpers;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandHelper {

    public static ArrayList<String> analyze(String command) {
        ArrayList<String> commands = new ArrayList<>();
        commands.addAll(Arrays.asList(command.split("\n")));

        return commands;
    }
}
