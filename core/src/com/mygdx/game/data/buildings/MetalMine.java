package com.mygdx.game.data.buildings;

import com.mygdx.game.data.materials.Metal;
//import sun.awt.SunToolkit;

public class MetalMine extends Building {
    private static final long serialVersionUID = 3L;
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
    Metal metal = Metal.getInstance();

    while(true) {
        metal.increasedValue(VALUE_INCREASE);
    }

    }
}
