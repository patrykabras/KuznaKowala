package com.mygdx.game.data.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.camera.Camera;
import com.mygdx.game.data.materials.Stone;

public class StoneMine extends Building {
    public static final long serialVersionUID = 2L;
    private static final int VALUE_INCREASE = 20;
    private SpriteBatch spriteBatch = new SpriteBatch();
    private final Texture lvlTexture = new Texture("Stone1.png");
    private final Texture lvl2Texture = new Texture("Stone2.png");
    private final Texture lvl3Texture = new Texture("Stone3.png");
    private final Texture lvl4Texture = new Texture("StoneTemp.png");
    private final Texture lvl5Texture = new Texture("StoneTemp.png");
    Stone stone = Stone.getInstance();
    private int lvl = 1;


    public StoneMine(){
        System.out.println("StoneMine has been build!");
    }

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
            case 4:
                spriteBatch.draw(lvl4Texture, x, y);

                break;
            case 5:
                spriteBatch.draw(lvl5Texture, x, y);
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
        stone.increasedValue((VALUE_INCREASE * lvl));
    }

    public int getLvl() {
        return lvl;
    }
}
