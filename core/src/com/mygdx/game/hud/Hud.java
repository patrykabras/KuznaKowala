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
import com.mygdx.game.app.GameActive;
import com.mygdx.game.app.KuzniaGame;
import com.mygdx.game.data.materials.Metal;
import com.mygdx.game.data.materials.Stone;
import com.mygdx.game.data.materials.Wood;
import com.mygdx.game.people.Population;


public class Hud {
    private final KuzniaGame game;
    public Stage stage;
    private GameActive gameActive;
    Label woodLabel;
    Label woodCost;
    Label stoneLabel;
    Label stoneCost;
    Label oreLabel;
    Label oreCost;
    Label humanLabel;
    Label humanCost;
    private Viewport viewport;
    private Wood wood = Wood.getInstance();
    private Stone stone = Stone.getInstance();
    private Metal metal = Metal.getInstance();
    private Population population;
    private Image woodTexture;
    private Image woodFactory;
    private Image stoneTexture;
    private Image stoneFactory;
    private Image metalTexture;
    private Image metalFactory;
    private Image humanTexture;
    private Image humanFactory;

    public Hud(SpriteBatch sb, KuzniaGame game, Population population, GameActive gameActive) {
        this.game = game;
        this.population = population;
        this.gameActive = gameActive;

        viewport = new FitViewport(720, 420, new OrthographicCamera());
        stage = new Stage(viewport, sb);


        Table hudTable = new Table();
        hudTable.top();
        hudTable.setFillParent(true);

        Table upgradeTable = new Table();
        upgradeTable.top().right().padTop(50).padRight(20);
        upgradeTable.setFillParent(true);

        woodTexture = new Image(new Texture("wood.png"));
        woodFactory = new Image(new Texture("wood1.png"));
        stoneTexture = new Image(new Texture("stone.png"));
        stoneFactory = new Image(new Texture("stone1.png"));
        metalTexture = new Image(new Texture("iron3.png"));
        metalFactory = new Image(new Texture("iron1.png"));
        humanTexture = new Image(new Texture("human.png"));
        humanFactory = new Image(new Texture("humanSpawner1.png"));
        woodLabel = new Label(String.format(String.format("%03d", wood.getValue())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        woodCost = new Label(String.format("%03d", gameActive.getWoodCutterCost()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        stoneLabel = new Label(String.format(String.format("%03d", stone.getValue())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        stoneCost = new Label(String.format("%03d", gameActive.getStoneMineCost()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        oreLabel = new Label(String.format(String.format("%03d", metal.getValue())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        oreCost = new Label(String.format("%03d", gameActive.getMetalMineCost()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        humanLabel = new Label(String.format(String.format("%03d", population.getPopulation().size())), new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        hudTable.add(woodTexture);
        hudTable.add(woodLabel).width(20).pad(10);
        hudTable.add(stoneTexture);
        hudTable.add(stoneLabel).width(20).pad(10);
        hudTable.add(metalTexture);
        hudTable.add(oreLabel).width(20).pad(10);
        hudTable.add(humanTexture);
        hudTable.add(humanLabel).width(20).pad(10);
        hudTable.padRight(-450);

        upgradeTable.add(new Label("next", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        upgradeTable.add(woodFactory).pad(5);
        upgradeTable.add(woodCost);
        upgradeTable.row();
        upgradeTable.add(new Label("next", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        upgradeTable.add(stoneFactory).pad(5);
        upgradeTable.add(stoneCost);
        upgradeTable.row();
        upgradeTable.add(new Label("next", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        upgradeTable.add(metalFactory).pad(5);
        upgradeTable.add(oreCost);
        upgradeTable.row();
        upgradeTable.add(new Label("next", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        upgradeTable.add(humanFactory).pad(5);

        stage.addActor(hudTable);
        stage.addActor(upgradeTable);

    }

    public void showInterface() {
        if(wood.getValue() > 1000) woodLabel.setText(wood.getValue()/1000 + "k");
        else woodLabel.setText(wood.getValue());
        if(stone.getValue() > 1000) stoneLabel.setText(stone.getValue()/1000 + "k");
        else stoneLabel.setText(stone.getValue());
        if(metal.getValue() > 1000) oreLabel.setText(metal.getValue()/1000 + "k");
        else oreLabel.setText(metal.getValue());
        if(population.getPopulation().size() > 1000) humanLabel.setText( population.getPopulation().size()/1000 + "k");
        else humanLabel.setText( population.getPopulation().size());

        woodCost.setText(gameActive.getWoodCutterCost());
        stoneCost.setText(gameActive.getStoneMineCost());
        oreCost.setText(gameActive.getMetalMineCost());
        game.batch.setProjectionMatrix(this.stage.getCamera().combined);
        this.stage.draw();
    }
}
