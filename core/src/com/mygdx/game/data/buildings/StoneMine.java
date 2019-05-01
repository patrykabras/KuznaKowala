package com.mygdx.game.data.buildings;

import com.mygdx.game.data.materials.Stone;

public class StoneMine extends Building {
    public static final long serialVersionUID = 2L;
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
    Stone stone = Stone.getInstance();

    while(true){
        stone.increasedValue(VALUE_INCREASE*lvl);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    }
}
