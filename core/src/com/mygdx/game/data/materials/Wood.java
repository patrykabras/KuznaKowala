package com.mygdx.game.data.materials;

public class Wood extends Material {
    private static int Value = 200;
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
    public void increasedValue(int increasedAmount) {
        this.Value += increasedAmount;
    }

    @Override
    public void decreasedValue(int decreasedAmount) {
        this.Value -= decreasedAmount;
    }
}
