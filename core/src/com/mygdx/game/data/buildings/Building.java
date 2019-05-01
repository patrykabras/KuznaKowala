package com.mygdx.game.data.buildings;

import com.mygdx.game.data.materials.Material;

public abstract class Building implements Buildable, Destroyable,Upgradable {
    @Override
    public abstract void build();

    public abstract void destroy();

    public abstract void upgrade();

    public abstract void working();
}
