package com.kidel.operation;

public class Level {

    private final int value;

    public Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Level plus(Level level) {
        return new Level(value + level.getValue());
    }
}
