package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
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
        mCamera.position.set(720/2,420/2,0);
    }
    @Override
    public void show() {

    }

    public void userInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            mCamera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            mCamera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mCamera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mCamera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mCamera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mCamera.translate(0, 3, 0);
        }
        mCamera.zoom = MathUtils.clamp(mCamera.zoom, 0.1f, 500/mCamera.viewportWidth);

        float effectiveViewportWidth = mCamera.viewportWidth * mCamera.zoom;
        float effectiveViewportHeight = mCamera.viewportHeight * mCamera.zoom;

        mCamera.position.x = MathUtils.clamp(mCamera.position.x, effectiveViewportWidth / 2f,  720- effectiveViewportWidth / 2f);
        mCamera.position.y = MathUtils.clamp(mCamera.position.y, effectiveViewportHeight / 2f, 420- effectiveViewportHeight / 2f);
    }

    public void update(){
        userInput();
        mCamera.update();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        render.setView(mCamera);
        render.render();
        update();
        //draw();
    }
    @Override
    public void resize(int width, int height) {
        mCamera = new OrthographicCamera(width,height);
        mCamera.position.set(mCamera.viewportWidth / 2f, mCamera.viewportHeight / 2f, 0);
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
