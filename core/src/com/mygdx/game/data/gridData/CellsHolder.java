package com.mygdx.game.data.gridData;

import com.mygdx.game.gird.Cell;

public class CellsHolder {
    private static CellsHolder instance = new CellsHolder();
    private Cell[][] cells = new Cell[46][46];

    private CellsHolder() {
    }

    public synchronized static CellsHolder getInstance() {
        return instance;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
