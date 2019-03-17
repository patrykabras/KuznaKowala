package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.KuzniaGame;

public class GameActive implements Screen {

    private final KuzniaGame game;
    private OrthographicCamera mCamera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer render;

    public GameActive(KuzniaGame game){
        this.game=game;
        mCamera = new OrthographicCamera();
        map = new TmxMapLoader().load("mapTuT.tmx");
        render = new OrthogonalTiledMapRenderer(map);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        render.setView(mCamera);
        render.render();
        //update(delta);
        //draw();
    }
    @Override
    public void resize(int width, int height) {
        mCamera.viewportHeight = height;
        mCamera.viewportWidth = width;
        mCamera.position.set(new Vector3(width/2,height/2,0));
        mCamera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
