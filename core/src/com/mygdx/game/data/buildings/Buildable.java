package com.mygdx.game.data.buildings;

import com.mygdx.game.camera.Camera;

import java.io.Serializable;

public interface Buildable extends Serializable {
    public void build(Camera camera);
}
