package com.mygdx.game.data.materials;

import java.io.Serializable;

public abstract class Material implements Serializable {

    public abstract void increasedValue(int increasedAmount);

    public abstract void decrasedValue(int decreasedAmount);
}
