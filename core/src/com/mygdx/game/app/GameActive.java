package com.mygdx.game.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.camera.Camera;
import com.mygdx.game.data.buildings.BuildingFactory;
import com.mygdx.game.data.gridData.CellsHolder;
import com.mygdx.game.gird.Cell;
import com.mygdx.game.gird.GridRenderer;
import com.mygdx.game.gird.MapGrid;
import com.mygdx.game.hud.Hud;
import com.mygdx.game.map.MapGenerator;
import com.mygdx.game.map.MapRenderer;
import com.mygdx.game.screens.PauseScreen;

public class GameActive implements Screen {

    private final KuzniaGame game;
    BuildingFactory buildingFactory = new BuildingFactory();
    CellsHolder cellsHolder = CellsHolder.getInstance();
    Cell[][] cells = new Cell[46][46];
    private Camera mCamera;
    private MapRenderer mapRenderer;
    private GridRenderer gridRenderer;
    private MapGenerator test;
    private Hud hud;

    public GameActive(KuzniaGame game) {
        this.game = game;
        mCamera = new Camera();
        test = new MapGenerator();
        mapRenderer = new MapRenderer();
        gridRenderer = new GridRenderer();
        hud = new Hud(game.batch, game);
    }

    @Override
    public void show() {

    }

    public void userInput() {
        cells = cellsHolder.getCells();
        //escape button to menu
        if (Gdx.input.isKeyPressed(131)) {
            game.setScreen(new PauseScreen(game));
            this.pause();
        }
        if (Gdx.input.isKeyPressed(62)) {
            System.out.println("SPACJA!");
            test = new MapGenerator();
            mapRenderer = new MapRenderer();
        }
        if (Gdx.input.isTouched()) {

            Vector3 mousePosition = new Vector3(0, 0, 0);
            mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            float deltaX = (float) Gdx.input.getDeltaX();
            float deltaY = (float) Gdx.input.getDeltaY();
            mCamera.getmCamera().translate(-deltaX, deltaY, 0);
            Vector3 mouseClickPositon = mCamera.getmCamera().unproject(mousePosition);
            System.out.println("Pozycja Myszki: " + mouseClickPositon);
            System.out.println("Grid to:" + numberOfCell(mousePosition));
            int numbTerr = test.getTerrainInfo()[numberOfCell(mousePosition)];
            if (numbTerr == 0) {
                System.out.println("Terren To Dirt");
            } else if (numbTerr == 1) {
                System.out.println("Terren To Rock");
            } else if (numbTerr == 2) {
                System.out.println("Terren To Sand");
            } else if (numbTerr == 3) {
                System.out.println("Terren To Grass");
            }
        }
    }

    public int numberOfCell(Vector3 mousePosition) {
        float x = mousePosition.x;
        float y = mousePosition.y;
        int w = (int) x / MapGrid.getCellSize();
        int h = (int) y / MapGrid.getCellSize();
        return ((h) * MapGrid.getMapSize()) + (w);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.startUp(mCamera);
        gridRenderer.start(mCamera);
        hud.showInterface();
        userInput();
        mCamera.update();
        //draw();
    }

    @Override
    public void resize(int width, int height) {
        int aspect = width / height;
        mCamera = new Camera(width * aspect, height * aspect);
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
