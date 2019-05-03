package com.mygdx.game.data.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.camera.Camera;
import com.mygdx.game.data.materials.Wood;

public class WoodCutter extends Building {
    private static final long serialVersionUID = 4L;
    private static final int VALUE_INCREASE = 20;
    private SpriteBatch spriteBatch = new SpriteBatch();
    private Texture texture = new Texture("WoodCutterTemp.png");
    Wood wood = Wood.getInstance();
    private int lvl = 1;

    public WoodCutter() {
        System.out.println("WoodCutter has been build!");
    }

    @Override
    public void build(Camera camera) {
        spriteBatch.setProjectionMatrix(camera.getmCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(texture, x, y);
        spriteBatch.end();
    }

    @Override
    public void destroy() {
        spriteBatch.dispose();
    }

    @Override
    public void upgrade() {
        lvl++;
    }

    @Override
    public void working() {
        wood.increasedValue(VALUE_INCREASE*lvl);
    }
}
