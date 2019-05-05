package com.mygdx.game.data.materials;

public class Wood extends Material {
    private static Wood instance = new Wood();
    private int value = DEFAULT_VALUE;

    private Wood() {
    }

    public synchronized static Wood getInstance() {
        return instance;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void reset() {
        value = DEFAULT_VALUE;
    }

    @Override
    public synchronized void increasedValue(int increasedAmount) {
        this.value += increasedAmount;
    }

    @Override
    public synchronized void decreasedValue(int decreasedAmount) {
        this.value -= decreasedAmount;
    }
}
