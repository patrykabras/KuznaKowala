package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.KuzniaGame;

public class MenuScreen implements Screen {

    KuzniaGame game;

    Texture newGameInactive;
    Texture newGameActive;
    Texture loadGameInactive;
    Texture loadGameActive;
    Texture optionsInactive;
    Texture optionsActive;
    Texture exitInactive;
    Texture exitActive;
    Texture menuHolder;

    public MenuScreen(KuzniaGame game){
    this.game=game;
    newGameInactive=new Texture("NGout.png");
    newGameActive=new Texture("NGin.png");
    loadGameActive=new Texture("LGin.png");
    loadGameInactive=new Texture("LGout.png");
    optionsActive=new Texture("OPin.png");
    optionsInactive=new Texture("OPout.png");
    exitActive=new Texture("EXin.png");
    exitInactive=new Texture("EXout.png");
    menuHolder=new Texture("bg.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 154, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();

        game.batch.draw(menuHolder, 240, 100);
        game.batch.draw(newGameInactive,250,260);
        game.batch.draw(loadGameInactive,250,210);
        game.batch.draw(optionsInactive,250,160);
        game.batch.draw(exitInactive,250,110);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
