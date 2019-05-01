package com.mygdx.game.data.buildings;

import com.mygdx.game.data.materials.Metal;
//import sun.awt.SunToolkit;

public class MetalMine extends Building {
    private static final long serialVersionUID = 3L;
    private static final int VALUE_INCREASE = 20;
    private int lvl = 1;
    @Override
    public void build() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void upgrade() {
    lvl++;
    }

    @Override
    public void working() {
    Metal metal = Metal.getInstance();

    while(true) {
        metal.increasedValue(VALUE_INCREASE*lvl);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    }
}
