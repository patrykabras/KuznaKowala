package com.mygdx.game.data.buildings;

import com.mygdx.game.data.gridData.CellsHolder;

import java.io.Serializable;

public interface Buildable extends Serializable {
    public void build(CellsHolder cellsHolder);
}
