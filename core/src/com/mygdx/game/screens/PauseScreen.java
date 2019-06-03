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
import com.mygdx.game.data.gridData.CellsHolder;
import com.mygdx.game.data.materials.Metal;
import com.mygdx.game.data.materials.Stone;
import com.mygdx.game.data.materials.Wood;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.File;

public class PauseScreen implements Screen {
    KuzniaGame game;

    Texture continueGameInactive;
    Texture continueGameActive;
    Texture saveGameInactive;
    Texture saveGameActive;
    Texture optionsInactive;
    Texture optionsActive;
    Texture exitInactive;
    Texture exitActive;
    Texture menuHolder;
    Texture background;
    Stage stage;
    Drawable drawableMenu;
    Drawable drawableContinue;
    Drawable drawableContinueActive;
    Drawable drawableSaveGame;
    Drawable drawableSaveGameActive;
    Drawable drawableOptions;
    Drawable drawableOptionsActive;
    Drawable drawableExit;
    Drawable drawableExitActive;
    ImageButton menu;
    ImageButton contiune;
    ImageButton save;
    ImageButton option;
    ImageButton exit;
    GameActive gameActive;

    public PauseScreen(KuzniaGame game, GameActive gameActive) {
        this.game = game;
        this.gameActive = gameActive;
        background = new Texture("bg.jpg");
        loadTextures();
        createDrawable();
        createMenu();
        createContinue();
        createSave();
        createOptions();
        createExit();
        stage = new Stage(new ScreenViewport());
        stage.addActor(menu);
        stage.addActor(contiune);
        stage.addActor(save);
        stage.addActor(option);
        stage.addActor(exit);
        Gdx.input.setInputProcessor(stage);
    }

    private void loadTextures() {
        continueGameActive = new Texture("Continue_in.png");
        continueGameInactive = new Texture("Continue_out.png");
        saveGameActive = new Texture("SG_in.png");
        saveGameInactive = new Texture("SG_out.png");
        optionsActive = new Texture("OPin.png");
        optionsInactive = new Texture("OPout.png");
        exitActive = new Texture("EXin.png");
        exitInactive = new Texture("EXout.png");
        menuHolder = new Texture("bg.png");
    }

    private void createDrawable() {
        drawableMenu = new TextureRegionDrawable(new TextureRegion(menuHolder));
        drawableContinue = new TextureRegionDrawable(new TextureRegion(continueGameInactive));
        drawableContinueActive = new TextureRegionDrawable(new TextureRegion(continueGameActive));
        drawableSaveGame = new TextureRegionDrawable(new TextureRegion(saveGameInactive));
        drawableSaveGameActive = new TextureRegionDrawable(new TextureRegion(saveGameActive));
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

    private void createContinue() {
        contiune = new ImageButton(drawableContinue, drawableContinueActive, drawableContinueActive);
        contiune.setPosition(Gdx.app.getGraphics().getWidth() / 2 - continueGameInactive.getWidth() / 2, menu.getY() - continueGameInactive.getHeight() + 210);
        contiune.setSize(continueGameInactive.getWidth(), continueGameInactive.getHeight());
        contiune.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playSound();
                contiune.getStyle().imageUp = drawableContinueActive;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                contiune.getStyle().imageUp = drawableContinue;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameActive.resume();
                game.setScreen(gameActive);

                dispose();
            }
        });
    }

    private void createSave() {
        save = new ImageButton(drawableSaveGame, drawableSaveGameActive);
        save.setPosition(Gdx.app.getGraphics().getWidth() / 2 - saveGameInactive.getWidth() / 2, contiune.getY() - 50);
        save.setSize(saveGameInactive.getWidth(), save.getHeight());
        save.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playSound();
                save.getStyle().imageUp = drawableSaveGameActive;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                save.getStyle().imageUp = drawableSaveGame;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //zaimplementowanie zapisu
                try {
                    JAXBContext metal = JAXBContext.newInstance(Metal.class);
                    JAXBContext wood = JAXBContext.newInstance(Wood.class);
                    JAXBContext stone = JAXBContext.newInstance(Stone.class);
                    JAXBContext cells = JAXBContext.newInstance(CellsHolder.class);

                    Marshaller metalMarshaller = metal.createMarshaller();
                    Marshaller woodMarshaller = wood.createMarshaller();
                    Marshaller stoneMarshaller = stone.createMarshaller();
                    Marshaller cellsMarshaller = cells.createMarshaller();

                    Metal metalValue = Metal.getInstance();
                    Wood woodValue = Wood.getInstance();
                    Stone stoneValue = Stone.getInstance();
                    CellsHolder cellsHolderValue = CellsHolder.getInstance();

                    metalMarshaller.marshal(metalValue, new File("metal.xml"));
                    metalMarshaller.marshal(woodValue, new File("wood.xml"));
                    metalMarshaller.marshal(stoneValue, new File("stone.xml"));
                    metalMarshaller.marshal(cellsHolderValue, new File("cells.xml"));


                } catch (JAXBException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void createOptions() {
        option = new ImageButton(drawableOptions, drawableOptionsActive);
        option.setPosition(Gdx.app.getGraphics().getWidth() / 2 - optionsInactive.getWidth() / 2, save.getY() - 50);
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
                game.setScreen(new OptionScreen(game, gameActive));
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
                game.setScreen(new MenuScreen(game));
                dispose();
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
        game.batch.begin();
        game.batch.draw(background, 0, 0, 720, 480);
        game.batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        menu.setPosition(width / 2 - menuHolder.getWidth() / 2, height / 2 - menuHolder.getHeight() / 2);
        menu.setSize(menuHolder.getWidth(), menuHolder.getHeight());
        contiune.setPosition(width / 2 - continueGameInactive.getWidth() / 2, menu.getY() - continueGameActive.getHeight() + 210);
        contiune.setSize(continueGameActive.getWidth(), continueGameActive.getHeight());
        save.setPosition(width / 2 - saveGameInactive.getWidth() / 2, contiune.getY() - 50);
        save.setSize(saveGameInactive.getWidth(), saveGameInactive.getHeight());
        option.setPosition(width / 2 - optionsInactive.getWidth() / 2, save.getY() - 50);
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
        continueGameInactive.dispose();
        continueGameActive.dispose();
        saveGameActive.dispose();
        saveGameInactive.dispose();
        optionsActive.dispose();
        optionsInactive.dispose();
        exitActive.dispose();
        exitInactive.dispose();
        menuHolder.dispose();
        menuHolder.dispose();
        stage.dispose();
    }
}
