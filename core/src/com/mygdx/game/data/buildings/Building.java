package com.mygdx.game.data.buildings;

import com.mygdx.game.data.gridData.CellsHolder;

public abstract class Building implements Buildable, Destroyable, Upgradable {

    @Override
    public abstract void build(CellsHolder cellsHolder);

    public abstract void destroy();

    public abstract void upgrade();

    public abstract void working();
}
