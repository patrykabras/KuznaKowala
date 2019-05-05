package com.mygdx.game.data.gridData;

import com.mygdx.game.gird.Cell;

import java.util.Arrays;

public class CellsHolder {
    private static CellsHolder instance = new CellsHolder();
    private Cell[][] cells = new Cell[50][50];

    private CellsHolder() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                cells[i][j] = new Cell();
            }

        }
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
