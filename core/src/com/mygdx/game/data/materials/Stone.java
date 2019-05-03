package com.mygdx.game.data.materials;

public class Stone extends Material {
    private static int Value = 200;
    private static Stone instance = new Stone();

    private Stone() {
    }

    public synchronized static Stone getInstance() {
        return instance;
    }

    public int getValue() {
        return Value;
    }

    @Override
    public void increasedValue(int increasedAmount) {
        Value += increasedAmount;
    }

    @Override
    public void decreasedValue(int decreasedAmount) {
        Value -= decreasedAmount;
    }
}
