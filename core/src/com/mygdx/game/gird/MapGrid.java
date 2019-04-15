package com.mygdx.game.gird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.camera.Camera;
import com.mygdx.game.map.TitleType;

public class MapGrid {
    private boolean[][] mapGrid;
    private static final int CELL_SIZE = 32;
    private static final int MAP_SIZE = 50;

    private int mapWidth,mapHeight;

    MapGrid(int width, int height){
        mapGrid = new boolean[width][height];

        this.mapWidth = width*CELL_SIZE;
        this.mapHeight = height*CELL_SIZE;
    }

    void render(ShapeRenderer render){
        for (int x = 0; x < mapGrid.length ; x++) {
            for (int y = 0; y < mapGrid[x].length ; y++) {
                render.line(x*CELL_SIZE,0,x*CELL_SIZE,y*CELL_SIZE);
                render.line(0,y*CELL_SIZE,x*CELL_SIZE,y*CELL_SIZE);
            }
        }
    }

    void createGrid(TitleType titleType){
        Cell[][] cells = new Cell[50][50];

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                cells[x][y].setTitleInfo(titleType.getCell(x,y));
            }
        }

    }
}
