package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.KuzniaGame;

public class GameActive implements Screen {

    private final KuzniaGame game;
    private Camera mCamera;
    private MapRenderer mapRenderer;
    private Hud hud;
    private Viewport gamePort;


    public GameActive(KuzniaGame game){
        this.game=game;
        mCamera = new Camera();
        mapRenderer = new MapRenderer();
        hud = new Hud(game.batch,game);
        gamePort = new FitViewport(720, 420, mCamera.getmCamera());
    }
    @Override
    public void show() {

    }
    public void userInput(){
        //escape button to menu
        if(Gdx.input.isKeyPressed(131))
        {
            game.setScreen(new PauseScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.startUp(mCamera);
        hud.showInterface();
        userInput();
        mCamera.update();
        //draw();
    }
    @Override
    public void resize(int width, int height) {
        mCamera = new Camera(width,height);
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
