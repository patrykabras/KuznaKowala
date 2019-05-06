package com.mygdx.game.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.app.KuzniaGame;
import com.mygdx.game.data.materials.Metal;
import com.mygdx.game.data.materials.Stone;
import com.mygdx.game.data.materials.Wood;


public class Hud {
    private final KuzniaGame game;
    public Stage stage;
    Label woodLabel;
    Label stoneLabel;
    Label oreLabel;
    private Viewport viewport;
    private Wood wood = Wood.getInstance();
    private Stone stone = Stone.getInstance();
    private Metal metal = Metal.getInstance();
    private Texture resources;


    public Hud(SpriteBatch sb, KuzniaGame game) {
        this.game = game;
        resources = new Texture("resources.png");

        viewport = new FitViewport(720, 420, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        woodLabel = new Label(String.format(String.format("%03d", wood.getValue())), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        stoneLabel = new Label(String.format(String.format("%03d", stone.getValue())), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        oreLabel = new Label(String.format(String.format("%03d", metal.getValue())), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(woodLabel).expandX().padLeft(-550);
        table.row();
        table.add(stoneLabel).expandX().padLeft(-550);
        table.row();
        table.add(oreLabel).expandX().padLeft(-550);
        table.row();

        stage.addActor(table);

    }

    public void showInterface() {
        woodLabel.setText(wood.getValue());
        stoneLabel.setText(stone.getValue());
        oreLabel.setText(metal.getValue());
        game.batch.begin();
        game.batch.draw(resources, 0, 361);
        game.batch.end();
        game.batch.setProjectionMatrix(this.stage.getCamera().combined);
        this.stage.draw();
    }
}
