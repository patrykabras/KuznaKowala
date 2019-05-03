package com.mygdx.game.data.materials;

public class Wood extends Material {
    private volatile static int Value = 200;
    private static Wood instance = new Wood();

    private Wood() {
    }

    public synchronized static Wood getInstance() {
        return instance;
    }

    public int getValue() {
        return Value;
    }

    @Override
    public synchronized void increasedValue(int increasedAmount) {
        this.Value += increasedAmount;
    }

    @Override
    public synchronized void decreasedValue(int decreasedAmount) {
        this.Value -= decreasedAmount;
    }
}
