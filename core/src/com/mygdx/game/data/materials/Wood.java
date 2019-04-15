package com.mygdx.game.data.materials;

public class Wood extends Material {
    private static int Value = 0;

    public static int getValue() {
        return Value;
    }

    @Override
    public void increasedValue(int increasedAmount) {
        this.Value += increasedAmount;
    }

    @Override
    public void decrasedValue(int decreasedAmount) {
        this.Value -= decreasedAmount;
    }
}
