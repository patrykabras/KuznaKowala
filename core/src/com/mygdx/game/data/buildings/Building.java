package com.mygdx.game.data.buildings;

public abstract class Building implements Buildable,Destrucatble,Upgradable {
    @Override
    public abstract void build();

    public abstract void destroy();

    public abstract void upgrade();

    public abstract void working();
}
