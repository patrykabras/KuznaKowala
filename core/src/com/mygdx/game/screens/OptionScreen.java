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

public class OptionScreen implements Screen {

    KuzniaGame game;

    Texture menuHolder;
    Texture backInactive;
    Texture backActive;
    Texture checkboxUnchecked;
    Texture checkboxChecked;
    Texture background;
    Stage stage;
    Drawable drawableMenu;
    Drawable drawableBack;
    Drawable drawableBackActive;
    Drawable drawableCheckboxUn;
    Drawable drawableCheckboxCh;
    ImageButton menu;
    ImageButton checkbox;
    ImageButton back;
    GameActive gameActive;

    public OptionScreen(KuzniaGame game) {
        this.game = game;
        background = new Texture("bg.jpg");
        loadTextures();
        createDrawable();
        createMenu();
        createCheckobox();
        createBack();
        stage = new Stage(new ScreenViewport());
        stage.addActor(menu);
        stage.addActor(checkbox);
        stage.addActor(back);
        Gdx.input.setInputProcessor(stage);
    }

    public OptionScreen(KuzniaGame game, GameActive gameActive) {
        this.game = game;
        this.gameActive = gameActive;
        loadTextures();
        createDrawable();
        createMenu();
        createCheckobox();
        createBackPause();
        stage = new Stage(new ScreenViewport());
        stage.addActor(menu);
        stage.addActor(checkbox);
        stage.addActor(back);
        Gdx.input.setInputProcessor(stage);
    }

    private void loadTextures() {
        menuHolder = new Texture("options_ingame.png");
        backInactive = new Texture("bck_out.png");
        backActive = new Texture("bck_in.png");
        checkboxUnchecked = new Texture("grey_box.png");
        checkboxChecked = new Texture("red_boxCheckmark.png");
    }

    private void createDrawable() {
        drawableMenu = new TextureRegionDrawable(new TextureRegion(menuHolder));
        drawableBack = new TextureRegionDrawable(new TextureRegion(backInactive));
        drawableBackActive = new TextureRegionDrawable(new TextureRegion(backActive));
        drawableCheckboxUn = new TextureRegionDrawable(new TextureRegion(checkboxUnchecked));
        drawableCheckboxCh = new TextureRegionDrawable(new TextureRegion(checkboxChecked));
    }

    private void createMenu() {
        menu = new ImageButton(drawableMenu);
        menu.setPosition(Gdx.app.getGraphics().getWidth() / 2 - menuHolder.getWidth() / 2, Gdx.app.getGraphics().getHeight() / 4);
        menu.setSize(menuHolder.getWidth(), menuHolder.getHeight());
    }


    private void createCheckobox() {
        if (!KuzniaGame.music.isPlaying()) {
            checkbox = new ImageButton(drawableCheckboxUn, drawableCheckboxCh, drawableCheckboxCh);
        } else if (KuzniaGame.music.isPlaying()) {
            checkbox = new ImageButton(drawableCheckboxCh, drawableCheckboxUn, drawableCheckboxUn);
        }
        checkbox.setPosition(Gdx.app.getGraphics().getWidth() / 2 + checkboxChecked.getWidth() / 2, menu.getY() + 105);
        checkbox.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playSound();
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkMusic();
            }
        });
    }

    private void checkMusic() {
        if (KuzniaGame.music.isPlaying()) {
            KuzniaGame.music.stop();
            KuzniaGame.soundOn = false;
        } else if (!KuzniaGame.music.isPlaying()) {
            KuzniaGame.music.play();
            KuzniaGame.soundOn = true;
        }
    }

    private void createBack() {
        back = new ImageButton(drawableBack, drawableBackActive);
        back.setPosition(Gdx.app.getGraphics().getWidth() / 2 - backInactive.getWidth() / 2, menu.getY() + 10);
        back.setSize(backInactive.getWidth(), backInactive.getHeight());
        back.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playSound();
                back.getStyle().imageUp = drawableBackActive;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                back.getStyle().imageUp = drawableBack;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });
    }

    private void createBackPause() {
        back = new ImageButton(drawableBack, drawableBackActive);
        back.setPosition(Gdx.app.getGraphics().getWidth() / 2 - backInactive.getWidth() / 2, menu.getY() + 10);
        back.setSize(backInactive.getWidth(), backInactive.getHeight());
        back.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playSound();
                back.getStyle().imageUp = drawableBackActive;
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                back.getStyle().imageUp = drawableBack;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game, gameActive));
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
        game.batch.draw(background, 0 ,0);
        game.batch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        menu.setPosition(width / 2 - menuHolder.getWidth() / 2, height / 2 - menuHolder.getHeight() / 2);
        menu.setSize(menuHolder.getWidth(), menuHolder.getHeight());
        checkbox.setPosition(Gdx.app.getGraphics().getWidth() / 2 + checkboxChecked.getWidth() / 2, menu.getY() + 105);
        checkbox.setSize(checkboxChecked.getWidth(), checkboxChecked.getHeight());
        back.setPosition(width / 2 - backInactive.getWidth() / 2, menu.getY() + 10);
        back.setSize(backInactive.getWidth(), backInactive.getHeight());

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
