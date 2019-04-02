package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.app.GameActive;
import com.mygdx.game.app.KuzniaGame;

public class PauseScreen implements Screen {
    KuzniaGame game;

    Texture conitnueGameInactive;
    Texture continueGameActive;
    Texture saveGameInactive;
    Texture saveGameActive;
    Texture optionsInactive;
    Texture optionsActive;
    Texture exitInactive;
    Texture exitActive;
    Texture menuHolder;

    public PauseScreen(KuzniaGame game) {
        this.game = game;
        conitnueGameInactive = new Texture("Continue_out.png");
        continueGameActive = new Texture("Continue_in.png");
        saveGameActive = new Texture("SG_in.png");
        saveGameInactive = new Texture("SG_out.png");
        optionsActive = new Texture("OPin.png");
        optionsInactive = new Texture("OPout.png");
        exitActive = new Texture("EXin.png");
        exitInactive = new Texture("EXout.png");
        menuHolder = new Texture("bg.png");
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


        if(Gdx.input.isKeyJustPressed(131))
        {
            game.setScreen(new GameActive(game));
        }

        if (Gdx.input.getX() > 250 && Gdx.input.getX() < 440 && Gdx.input.getY() > 110 && Gdx.input.getY() < 160) {
            game.batch.draw(continueGameActive, 250, 260);
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new GameActive(game));
            }

        } else game.batch.draw(conitnueGameInactive, 250, 260);

        if (Gdx.input.getX() > 250 && Gdx.input.getX() < 440 && Gdx.input.getY() > 160 && Gdx.input.getY() < 210) {
            game.batch.draw(saveGameActive, 250, 210);
        } else game.batch.draw(saveGameInactive, 250, 210);

        if (Gdx.input.getX() > 250 && Gdx.input.getX() < 440 && Gdx.input.getY() > 210 && Gdx.input.getY() < 260) {
            game.batch.draw(optionsActive, 250, 160);
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new OptionScreen(game));
            }

        } else game.batch.draw(optionsInactive, 250, 160);

        if (Gdx.input.getX() > 250 && Gdx.input.getX() < 440 && Gdx.input.getY() > 260 && Gdx.input.getY() < 310) {
            game.batch.draw(exitActive, 250, 110);
            if (Gdx.input.justTouched()) {
                Gdx.app.exit();
            }
        } else game.batch.draw(exitInactive, 250, 110);


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
        conitnueGameInactive.dispose();
        continueGameActive.dispose();
        saveGameActive.dispose();
        saveGameInactive.dispose();
        optionsActive.dispose();
        optionsInactive.dispose();
        exitActive.dispose();
        exitInactive.dispose();
        menuHolder.dispose();
        menuHolder.dispose();
    }
}
