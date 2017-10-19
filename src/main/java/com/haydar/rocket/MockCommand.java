package com.haydar.rocket;

public class MockCommand implements ICommand {

    @Override
    public boolean isValid(String commands) {
        return true;
    }
}
