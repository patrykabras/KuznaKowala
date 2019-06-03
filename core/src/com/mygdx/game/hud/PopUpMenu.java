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
    public static boolean isOn;


    public PopUpMenu(KuzniaGame game) {

        table = new Table();
        table.bottom();
        this.game = game;
        isOn = false;
        loadTextures();
        createDrawable();
        createUpgrade();
        createDestroy();
        table.add(upgrade).width(48);
        table.add(destroy).width(48);
        stage = new Stage(new ScreenViewport());
        table.setFillParent(true);
        stage.addActor(table);
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


    private void createUpgrade(){
        upgrade = new ImageButton(drawableUpgrade);
        upgrade.setSize(upgradeTexture.getWidth(), upgradeTexture.getHeight());
        upgrade.setPosition(Gdx.app.getGraphics().getWidth() / 4 - upgradeTexture.getWidth()/2 , backgroundTexture.getHeight()/2);
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
        destroy.setSize(destroyTexture.getWidth(), destroyTexture.getHeight());
        destroy.setPosition((Gdx.app.getGraphics().getWidth() / 4 * 3) - destroyTexture.getWidth()/2 , upgrade.getY());
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
            game.batch.begin();
            game.batch.draw(backgroundTexture, Gdx.app.getGraphics().getWidth() / 2 - backgroundTexture.getWidth()/2, 0);
            game.batch.end();
            this.stage.act();
            this.stage.draw();
        }
    }
}
