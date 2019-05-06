package com.mygdx.game.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.camera.Camera;
import com.mygdx.game.data.buildings.Building;
import com.mygdx.game.data.buildings.BuildingFactory;
import com.mygdx.game.data.gridData.CellsHolder;
import com.mygdx.game.data.materials.Metal;
import com.mygdx.game.data.materials.Stone;
import com.mygdx.game.data.materials.Wood;
import com.mygdx.game.exceptions.NoSuchTerritoryTypeException;
import com.mygdx.game.gird.Cell;
import com.mygdx.game.gird.GridRenderer;
import com.mygdx.game.gird.MapGrid;
import com.mygdx.game.hud.Hud;
import com.mygdx.game.map.MapGenerator;
import com.mygdx.game.map.MapRenderer;
import com.mygdx.game.screens.PauseScreen;
//import javafx.concurrent.Task;

public class GameActive implements Screen {

    private final KuzniaGame game;
    BuildingFactory buildingFactory = new BuildingFactory();
    private CellsHolder cellsHolder = CellsHolder.getInstance();
    private Cell[][] cells = cellsHolder.getCells();
    private Camera mCamera;
    private MapRenderer mapRenderer;
    private GridRenderer gridRenderer;
    private MapGenerator test;
    private Hud hud;
    private Stone stone = Stone.getInstance();
    private Metal metal = Metal.getInstance();
    private Wood wood = Wood.getInstance();
    private int metalMineCost = 20;
    private int stoneMineCost = 20;
    private int woodCutterCost = 20;

    public GameActive(KuzniaGame game) {
        this.game = game;
        mCamera = new Camera();
        test = new MapGenerator();
        mapRenderer = new MapRenderer();
        gridRenderer = new GridRenderer();
    }

    @Override
    public void show() {

    }

    public void userInput() {
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

            buildInCell(mouseClickPositon, numbTerr);
        }
    }

    private void buildInCell(Vector3 mousePosition, int numbTerr) {
        int row = (int) mousePosition.x / MapGrid.getCellSize();
        int col = (int) mousePosition.y / MapGrid.getCellSize();

        System.out.println("Column: " + col);
        System.out.println("Row: " + row);

        Building building = null;
        try{
        if (cells[col][row].getBuilding() == null) {
            switch (numbTerr) {
                case 0:
                    if(stone.getValue()>=stoneMineCost) {
                        building = buildingFactory.createNewBuilding("stonemine");
                        stone.decreasedValue(stoneMineCost);
                        stoneMineCost+=20;
                    }
                    break;
                case 1:
                    if(metal.getValue()>=metalMineCost) {
                        building = buildingFactory.createNewBuilding("metalmine");
                        metal.decreasedValue(metalMineCost);
                        metalMineCost+=20;
                    }
                    break;
                case 3:
                    if(wood.getValue()>=woodCutterCost) {
                        building = buildingFactory.createNewBuilding("woodcutter");
                        wood.decreasedValue(woodCutterCost);
                        woodCutterCost+=20;
                    }
                    break;
                default:
                    System.out.println("Ten typ nie jest zdefiniowany");
            }

            building.setX(row*MapGrid.getCellSize()+8);
            building.setY(col*MapGrid.getCellSize()+8);
            cells[col][row].setBuilding(building);
            cellsHolder.setCells(cells);
        }}catch (NullPointerException e){
            System.out.println("Nothing happend");
        }
    }

    public int numberOfCell(Vector3 mousePosition) {
        float x = mousePosition.x;
        float y = mousePosition.y;
        int w = (int) x / MapGrid.getCellSize();
        int h = (int) y / MapGrid.getCellSize();
        return ((h) * MapGrid.getMapSize()) + (w);
    }

    float timeState = 0f;
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.startUp(mCamera);
        gridRenderer.start(mCamera);
        hud = new Hud(game.batch,game);
        hud.showInterface();

        /*odpowiada za renderowanie textur komorki*/
        for (int i = 0; i <50 ; i++) {
            for (int j = 0; j <50 ; j++) {
                if(cells[i][j].getBuilding()!=null){
                    cells[i][j].getBuilding().build(mCamera);
                }
            }
        }

        /*zmiana wartosci materialu*/
        timeState+=Gdx.graphics.getDeltaTime();
        if(timeState>=3f){

            timeState=0f;

            for (int i = 0; i <50 ; i++) {
                for (int j = 0; j <50 ; j++) {
                    if(cells[i][j].getBuilding()!=null){
                        cells[i][j].getBuilding().working();
                    }
                }
            }
        }

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
