package com.mygdx.game.gird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.camera.Camera;

public class GridRenderer {
    private ShapeRenderer render;
    private MapGrid grid;

    public GridRenderer() {
        render = new ShapeRenderer();
        render.setAutoShapeType(true);
        grid = new MapGrid(51,51);
    }

    public void start(Camera camera) {
        render.begin();
        render.setProjectionMatrix(camera.getmCamera().combined);
        grid.render(render);
        render.end();
    }

    public MapGrid getGrid() {
        return grid;
    }
}
