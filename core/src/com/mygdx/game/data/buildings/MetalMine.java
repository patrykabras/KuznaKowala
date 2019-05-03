package com.mygdx.game.data.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.camera.Camera;
import com.mygdx.game.data.materials.Metal;
//import sun.awt.SunToolkit;

public class MetalMine extends Building {
    private static final long serialVersionUID = 3L;
    private static final int VALUE_INCREASE = 20;
    private SpriteBatch spriteBatch = new SpriteBatch();
    private Texture texture = new Texture("MetalTemp.png");
    Metal metal = Metal.getInstance();

    private int lvl = 1;

    MetalMine() {
        System.out.println("MetalMine has been build!");
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
        metal.increasedValue(VALUE_INCREASE*lvl);
    }
}
