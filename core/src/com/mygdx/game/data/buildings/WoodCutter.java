package com.mygdx.game.data.buildings;

import com.mygdx.game.data.materials.Wood;

public class WoodCutter extends Building {
    private static final long serialVersionUID = 4L;
    private static final int VALUE_INCREASE = 20;

    @Override
    public void build() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void upgrade() {

    }

    @Override
    public void working() {
    Wood wood = Wood.getInstance();

    while (true){
        wood.increasedValue(VALUE_INCREASE);
    }
    }
}
