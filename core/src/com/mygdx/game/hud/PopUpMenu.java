package com.mygdx.game.hud;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.app.GameActive;
import com.mygdx.game.app.KuzniaGame;
import com.mygdx.game.screens.MenuScreen;

public class PopUpMenu {

    KuzniaGame game;

    Texture backgroundTexture;
    Texture upgradeTexture;
    Texture destroyTexture;
    Drawable drawableBackground;
    Drawable drawableUpgrade;
    Drawable drawableDestroy;
    private Stage stage;
    Table table;
    private ImageButton upgrade;
    private ImageButton destroy;
    private ImageButton background;
    public static boolean isOn;


    public PopUpMenu(KuzniaGame game) {

        table = new Table();
        table.bottom();
        this.game = game;
        isOn = false;
        loadTextures();
        createDrawable();
        createMenu();
        createUpgrade();
        createDestroy();
        stage = new Stage(new ScreenViewport());
        stage.addActor(background);
        stage.addActor(upgrade);
        stage.addActor(destroy);
        Gdx.input.setInputProcessor(stage);
    }

    private void loadTextures(){
        backgroundTexture = new Texture("background.png");
        upgradeTexture = new Texture("up.png");
        destroyTexture = new Texture("cross.png");
    }

    private void createDrawable(){
        drawableBackground = new TextureRegionDrawable(new TextureRegion(backgroundTexture));
        drawableUpgrade = new TextureRegionDrawable(new TextureRegion(upgradeTexture));
        drawableDestroy = new TextureRegionDrawable(new TextureRegion(destroyTexture));
    }

    private void createMenu() {
        background = new ImageButton(drawableBackground);
        background.setPosition(Gdx.app.getGraphics().getWidth() / 2 - backgroundTexture.getWidth() / 2, 0);
    }

    private void createUpgrade(){
        upgrade = new ImageButton(drawableUpgrade);
        upgrade.setPosition(background.getX() + 24 - upgradeTexture.getWidth()/2 , background.getY() + 5);
        upgrade.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameActive.canBuild = true;
                isOn = false;
            }
        });
    }

    private void createDestroy(){
        destroy = new ImageButton(drawableDestroy);
        destroy.setPosition((upgrade.getX() + 40) , upgrade.getY());
        destroy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameActive.canDestroy = true;
                isOn = false;
            }
        });
    }

    public void showMenu(){
        if(isOn == true) {

            this.stage.act();
            this.stage.draw();
        }
    }
}
