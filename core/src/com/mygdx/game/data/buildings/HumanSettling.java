package com.mygdx.game.data.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.camera.Camera;

public class HumanSettling extends Building {
    private static final long serialVersionUID = 1L;
    private SpriteBatch spriteBatch = new SpriteBatch();
    private final Texture lvlTexture = new Texture("humanSpawner1.png");
    private final Texture lvl2Texture = new Texture("humanSpawner2.png");
    private final Texture lvl3Texture = new Texture("grey_box.png");
    private int lvl = 1;

    @Override
    public void build(Camera camera) {
        spriteBatch.setProjectionMatrix(camera.getmCamera().combined);
        spriteBatch.begin();

        switch(lvl){
            case 1:
                spriteBatch.draw(lvlTexture, x, y);
                break;
            case 2:
                spriteBatch.draw(lvl2Texture, x, y);

                break;
            case 3:
                spriteBatch.draw(lvl3Texture, x, y);

                break;
        }
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

    }

    @Override
    public int getLvl() {
        return lvl;
    }
}
