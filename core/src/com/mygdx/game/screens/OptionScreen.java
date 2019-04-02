package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.app.KuzniaGame;

public class OptionScreen implements Screen {

    KuzniaGame game;

    Texture menuHolder;
    Texture backInactive;
    Texture backActive;
    Texture checkboxUnchecked;
    Texture checkboxChecked;
    public static int IsClicked = 1; //Sprawdza czy boks z muzyką jest "zaptaszkowany" 1 - tak, 0 - nie, domyślnie muzyka włączona(?)
    public OptionScreen(KuzniaGame game){
        this.game=game;

     menuHolder=new Texture("options_ingame.png");
     backInactive=new Texture("bck_out.png");
     backActive=new Texture("bck_in.png");
     checkboxUnchecked=new Texture("grey_box.png");
     checkboxChecked=new Texture("red_boxCheckmark.png");

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

        if(Gdx.input.getX() >250 && Gdx.input.getX() < 440 && Gdx.input.getY()>260 && Gdx.input.getY()<310) {
            game.batch.draw(backActive, 250, 110);
            if(Gdx.input.justTouched())
            {
                this.dispose();
                game.setScreen(new MenuScreen(game));
            }
        }else game.batch.draw(backInactive, 250, 110);
        //if sprawdzajacy czy boks jest zaznaczony
        if(IsClicked==0) {
            if (Gdx.input.getX() > 370 && Gdx.input.getX() < 410 && Gdx.input.getY() > 165 && Gdx.input.getY() < 205) {
                game.batch.draw(checkboxUnchecked, 370, 205);
                if (Gdx.input.justTouched()) {
                    IsClicked=1;
                    game.batch.draw(checkboxChecked, 370, 205);
                    KuzniaGame.music.play();
                }
            } else game.batch.draw(checkboxUnchecked, 370, 205);
        } else {
            game.batch.draw(checkboxChecked, 370, 205);
            if (Gdx.input.getX() > 370 && Gdx.input.getX() < 410 && Gdx.input.getY() > 165 && Gdx.input.getY() < 205)
                if (Gdx.input.justTouched()) {
                    IsClicked=0;
                    game.batch.draw(checkboxUnchecked, 370, 205);{
                        KuzniaGame.music.stop();
                }
            } else game.batch.draw(checkboxChecked, 370, 205);
        }

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
