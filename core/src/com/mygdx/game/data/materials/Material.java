package com.mygdx.game.data.materials;

import java.io.Serializable;

public abstract class Material implements Serializable {
    final static int DEFAULT_VALUE = 200;


    public abstract void reset();

    public abstract void increasedValue(int increasedAmount);

    public abstract void decreasedValue(int decreasedAmount);
}
