package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer wood;
    private Integer stone;
    private Integer ore;

    Label woodLabel;
    Label stoneLabel;
    Label oreLabel;


    public Hud(SpriteBatch sb){
        wood = 0;
        stone = 0;
        ore = 0;

        viewport = new FitViewport(720, 420, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        woodLabel = new Label(String.format(String.format("%03d", wood)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        stoneLabel = new Label(String.format(String.format("%03d", stone)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        oreLabel = new Label(String.format(String.format("%03d", ore)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(woodLabel).expandX().padLeft(-550);
        table.row();
        table.add(stoneLabel).expandX().padLeft(-550);
        table.row();
        table.add(oreLabel).expandX().padLeft(-550);
        table.row();

        stage.addActor(table);

    }
}
