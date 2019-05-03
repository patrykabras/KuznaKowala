package com.mygdx.game.data.materials;

public class Metal extends Material {
    private static int Value = 200;
    private static Metal instance = new Metal();

    private Metal() {
    }

    public synchronized static Metal getInstance() {
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
