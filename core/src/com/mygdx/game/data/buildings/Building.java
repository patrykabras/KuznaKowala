package com.mygdx.game.data.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.camera.Camera;

public abstract class Building implements Buildable, Destroyable, Upgradable {
    protected float x,y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public abstract void build(Camera camera);

    public abstract void destroy();

    public abstract void upgrade();

    public abstract void working();

    public abstract int getLvl();
}
