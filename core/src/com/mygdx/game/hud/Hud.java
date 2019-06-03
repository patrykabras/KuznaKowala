package com.mygdx.game.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.app.KuzniaGame;
import com.mygdx.game.data.materials.Metal;
import com.mygdx.game.data.materials.Stone;
import com.mygdx.game.data.materials.Wood;
import com.mygdx.game.people.Population;


public class Hud {
    private final KuzniaGame game;
    public Stage stage;
    Label woodLabel;
    Label stoneLabel;
    Label oreLabel;
    Label humanLabel;
    private Viewport viewport;
    private Wood wood = Wood.getInstance();
    private Stone stone = Stone.getInstance();
    private Metal metal = Metal.getInstance();
    private Population population;
    private Image woodTexture;
    private Image stoneTexture;
    private Image metalTexture;
    private Image humanTexture;

    public Hud(SpriteBatch sb, KuzniaGame game, Population population) {
        this.game = game;
        this.population = population;


        viewport = new FitViewport(720, 420, new OrthographicCamera());
        stage = new Stage(viewport, sb);


        Table table = new Table();
        table.top();
        table.setFillParent(true);

        woodTexture = new Image(new Texture("wood.png"));
        stoneTexture = new Image(new Texture("stone.png"));
        metalTexture = new Image(new Texture("iron3.png"));
        humanTexture = new Image(new Texture("human.png"));
        woodLabel = new Label(String.format(String.format("%03d", wood.getValue())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        stoneLabel = new Label(String.format(String.format("%03d", stone.getValue())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        oreLabel = new Label(String.format(String.format("%03d", metal.getValue())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        humanLabel = new Label(String.format(String.format("%03d", population.getPopulation().size())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(woodTexture);
        table.add(woodLabel).width(20).pad(10);
        table.add(stoneTexture);
        table.add(stoneLabel).width(20).pad(10);
        table.add(metalTexture);
        table.add(oreLabel).width(20).pad(10);
        table.add(humanTexture);
        table.add(humanLabel).width(20).pad(10);
        table.padLeft(-400);

        stage.addActor(table);

    }

    public void showInterface() {
        if(wood.getValue() > 1000) woodLabel.setText(wood.getValue()/1000 + "k");
        else woodLabel.setText(wood.getValue());
        if(stone.getValue() > 1000) stoneLabel.setText(stone.getValue()/1000 + "k");
        else stoneLabel.setText(stone.getValue());
        if(metal.getValue() > 1000) oreLabel.setText(metal.getValue()/1000 + "k");
        else oreLabel.setText(metal.getValue());
        humanLabel.setText( population.getPopulation().size());
        game.batch.setProjectionMatrix(this.stage.getCamera().combined);
        this.stage.draw();
    }
}
