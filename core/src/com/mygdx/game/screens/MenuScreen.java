package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.app.GameActive;
import com.mygdx.game.app.KuzniaGame;

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
    Stage stage;
    Drawable drawableMenu;
    Drawable drawableNewGame;
    Drawable drawableNewGameActive;
    Drawable drawableLoadGame;
    Drawable drawableLoadGameActive;
    Drawable drawableOptions;
    Drawable drawableOptionsActive;
    Drawable drawableExit;
    Drawable drawableExitActive;
    ImageButton menu;
    ImageButton newGame;
    ImageButton load;
    ImageButton option;
    ImageButton exit;

    public MenuScreen(KuzniaGame game) {
        this.game = game;
        loadTextures();
        createDrawable();
        createMenu();
        createNewGame();
        createLoad();
        createOptions();
        createExit();
        stage = new Stage(new ScreenViewport());
        stage.addActor(menu);
        stage.addActor(newGame);
        stage.addActor(load);
        stage.addActor(option);
        stage.addActor(exit);
        Gdx.input.setInputProcessor(stage);
    }

    private void loadTextures() {
        newGameInactive = new Texture("NGout.png");
        newGameActive = new Texture("NGin.png");
        loadGameActive = new Texture("LGin.png");
        loadGameInactive = new Texture("LGout.png");
        optionsActive = new Texture("OPin.png");
        optionsInactive = new Texture("OPout.png");
        exitActive = new Texture("EXin.png");
        exitInactive = new Texture("EXout.png");
        menuHolder = new Texture("bg.png");
    }

    private void createDrawable() {
        drawableMenu = new TextureRegionDrawable(new TextureRegion(menuHolder));
        drawableNewGame = new TextureRegionDrawable(new TextureRegion(newGameInactive));
        drawableNewGameActive = new TextureRegionDrawable(new TextureRegion(newGameActive));
        drawableLoadGame = new TextureRegionDrawable(new TextureRegion(loadGameInactive));
        drawableLoadGameActive = new TextureRegionDrawable(new TextureRegion(loadGameActive));
        drawableOptions = new TextureRegionDrawable(new TextureRegion(optionsInactive));
        drawableOptionsActive = new TextureRegionDrawable(new TextureRegion(optionsActive));
        drawableExit = new TextureRegionDrawable(new TextureRegion(exitInactive));
        drawableExitActive = new TextureRegionDrawable(new TextureRegion(exitActive));
    }

    private void createMenu() {
        menu = new ImageButton(drawableMenu);
        menu.setPosition(Gdx.app.getGraphics().getWidth() / 2 - menuHolder.getWidth() / 2, Gdx.app.getGraphics().getHeight() / 4);
        menu.setSize(menuHolder.getWidth(), menuHolder.getHeight());
    }

    private void createNewGame() {
        newGame = new ImageButton(drawableNewGame, drawableNewGameActive, drawableNewGameActive);
        newGame.setPosition(Gdx.app.getGraphics().getWidth() / 2 - newGameInactive.getWidth() / 2, menu.getY() - newGameInactive.getHeight() + 210);
        newGame.setSize(newGameInactive.getWidth(), newGameInactive.getHeight());
        newGame.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playSound();
                newGame.getStyle().imageUp = drawableNewGameActive;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                newGame.getStyle().imageUp = drawableNewGame;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameActive(game));
            }
        });
    }

    private void createLoad() {
        load = new ImageButton(drawableLoadGame, drawableLoadGameActive);
        load.setPosition(Gdx.app.getGraphics().getWidth() / 2 - loadGameInactive.getWidth() / 2, newGame.getY() - 50);
        load.setSize(loadGameInactive.getWidth(), loadGameInactive.getHeight());
        load.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playSound();
                load.getStyle().imageUp = drawableLoadGameActive;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                load.getStyle().imageUp = drawableLoadGame;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //wywolanie wczytania gry
            }
        });
    }

    private void createOptions() {
        option = new ImageButton(drawableOptions, drawableOptionsActive);
        option.setPosition(Gdx.app.getGraphics().getWidth() / 2 - optionsInactive.getWidth() / 2, load.getY() - 50);
        option.setSize(optionsInactive.getWidth(), optionsInactive.getHeight());
        option.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playSound();
                option.getStyle().imageUp = drawableOptionsActive;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                option.getStyle().imageUp = drawableOptions;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionScreen(game));
            }
        });
    }

    private void createExit() {
        exit = new ImageButton(drawableExit, drawableExitActive);
        exit.setPosition(Gdx.app.getGraphics().getWidth() / 2 - exitInactive.getWidth() / 2, option.getY() - 50);
        exit.setSize(exitInactive.getWidth(), exitInactive.getHeight());
        exit.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playSound();
                exit.getStyle().imageUp = drawableExitActive;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exit.getStyle().imageUp = drawableExit;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    private void playSound() {
        if (KuzniaGame.soundOn == true) {
            KuzniaGame.pop.play();
        } else if (KuzniaGame.soundOn == false) {

        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 154, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        menu.setPosition(width / 2 - menuHolder.getWidth() / 2, height / 2 - menuHolder.getHeight() / 2);
        menu.setSize(menuHolder.getWidth(), menuHolder.getHeight());
        newGame.setPosition(width / 2 - newGameInactive.getWidth() / 2, menu.getY() - newGameInactive.getHeight() + 210);
        newGame.setSize(newGameInactive.getWidth(), newGameInactive.getHeight());
        load.setPosition(width / 2 - loadGameInactive.getWidth() / 2, newGame.getY() - 50);
        load.setSize(loadGameInactive.getWidth(), loadGameInactive.getHeight());
        option.setPosition(width / 2 - optionsInactive.getWidth() / 2, load.getY() - 50);
        option.setSize(optionsInactive.getWidth(), optionsInactive.getHeight());
        exit.setPosition(width / 2 - exitInactive.getWidth() / 2, option.getY() - 50);
        exit.setSize(exitInactive.getWidth(), exitInactive.getHeight());
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
        newGameInactive.dispose();
        newGameActive.dispose();
        loadGameActive.dispose();
        loadGameInactive.dispose();
        optionsActive.dispose();
        optionsInactive.dispose();
        exitActive.dispose();
        exitInactive.dispose();
        menuHolder.dispose();
        menuHolder.dispose();
        stage.dispose();
    }
}
