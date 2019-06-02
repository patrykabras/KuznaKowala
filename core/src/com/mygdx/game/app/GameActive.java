package com.mygdx.game.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.camera.Camera;
import com.mygdx.game.data.buildings.Building;
import com.mygdx.game.data.buildings.BuildingFactory;
import com.mygdx.game.data.gridData.CellsHolder;
import com.mygdx.game.data.materials.Metal;
import com.mygdx.game.data.materials.Stone;
import com.mygdx.game.data.materials.Wood;
import com.mygdx.game.gird.Cell;
import com.mygdx.game.gird.GridRenderer;
import com.mygdx.game.gird.MapGrid;
import com.mygdx.game.hud.Hud;
import com.mygdx.game.map.MapGenerator;
import com.mygdx.game.map.MapRenderer;
import com.mygdx.game.people.Person;
import com.mygdx.game.people.Population;
import com.mygdx.game.screens.PauseScreen;

import java.util.Random;

public class GameActive implements Screen {

    private final KuzniaGame game;
    private final int ACTUAL_WORKING_MAP_BEGGINIG = 2;
    private final int ACTUAL_WORKING_MAP_ENDING = 47;
    BuildingFactory buildingFactory = new BuildingFactory();
    float timeState = 0f;
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
    private int metalMineUpgradeCost = 50;
    private int stoneMineUpgradeCost = 50;
    private int woodMineUpgradeCost = 50;
    private final static int MAX_LVL = 3;
    private final static double UPGRADE_MULTIPLER = 1.5;
    private final static double COST_MULTIPLER = 1.5;
    private Population population;


    private enum TerrainType {
        DIRT(0), ROCK(1), SAND(2), GRASS(3);

        private final int value;

        private TerrainType(int x) {
            this.value = x;
        }

        public int getValue() {
            return value;
        }

        static TerrainType createFromInt(int option) {
            return TerrainType.values()[option];
        }
    }

    public GameActive(KuzniaGame game) {
        this.game = game;
        hud = new Hud(game.batch, game);
        mCamera = new Camera();
        test = new MapGenerator();
        mapRenderer = new MapRenderer();
        gridRenderer = new GridRenderer();
        population = new Population();
    }

    @Override
    public void show() {

    }

    public void userInput() {
        //escape button to menu
        if (Gdx.input.isKeyPressed(131)) {
            game.setScreen(new PauseScreen(game, this));
            this.pause();
        }
        if (Gdx.input.isKeyPressed(62)) {
            System.out.println("SPACJA!");
            test = new MapGenerator();
            mapRenderer = new MapRenderer();
            resetCellsAndValues();
        }
        if (Gdx.input.isKeyPressed(35)){ // G tworzy ludzi na mapie
            Random gener = new Random();
            population.addNewPerson(new Person(new Vector3(gener.nextInt(1000),gener.nextInt(1000),0)));
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {

            Vector3 mousePosition = new Vector3(0, 0, 0);
            mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            float deltaX = (float) Gdx.input.getDeltaX();
            float deltaY = (float) Gdx.input.getDeltaY();
            mCamera.getmCamera().translate(-deltaX, deltaY, 0);
            Vector3 mouseClickPositon = mCamera.getmCamera().unproject(mousePosition);
            TerrainType option = TerrainType.createFromInt(test.getTerrainInfo()[numberOfCell(mousePosition)]);
            System.out.println("Pozycja Myszki: " + mouseClickPositon);
            System.out.println("Grid to:" + numberOfCell(mousePosition));
            switch (option) {
                case DIRT:
                    System.out.println("Terren To Dirt");
                    break;
                case ROCK:
                    System.out.println("Terren To Rock");
                    break;
                case SAND:
                    System.out.println("Terren To Sand");
                    break;
                case GRASS:
                    System.out.println("Terren To Grass");
                    break;
                default:
                    System.out.println("Nie ma takiego terenu");
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                checkAndBuildOrUpgrade(mouseClickPositon, option);
            } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
                removeBuildingFromCell(mouseClickPositon);

        }

    }

    private void removeBuildingFromCell(Vector3 mousePosition) {
        int row = (int) mousePosition.x / MapGrid.getCellSize();
        int col = (int) mousePosition.y / MapGrid.getCellSize();

        System.out.println("Column: " + col);
        System.out.println("Row: " + row);

        try {
            if (col >= ACTUAL_WORKING_MAP_BEGGINIG
                    && col <= ACTUAL_WORKING_MAP_ENDING
                    && row >= ACTUAL_WORKING_MAP_BEGGINIG &&
                    row <= ACTUAL_WORKING_MAP_ENDING) {

                cells[col][row].setBuilding(null);
                cellsHolder.setCells(cells);
            }
        } catch (NullPointerException e) {
            System.out.println("Nothing happned");
        }
    }

    private void checkAndBuildOrUpgrade(Vector3 mousePosition, TerrainType option) {
        int row = (int) mousePosition.x / MapGrid.getCellSize();
        int col = (int) mousePosition.y / MapGrid.getCellSize();
        System.out.println("Column: " + col);
        System.out.println("Row: " + row);

        if (cells[col][row].getBuilding() != null
                && col >= ACTUAL_WORKING_MAP_BEGGINIG
                && col <= ACTUAL_WORKING_MAP_ENDING
                && row >= ACTUAL_WORKING_MAP_BEGGINIG &&
                row <= ACTUAL_WORKING_MAP_ENDING) {
            upgradeBuilding(col, row, option);
        } else if (cells[col][row].getBuilding() == null
                && col >= ACTUAL_WORKING_MAP_BEGGINIG
                && col <= ACTUAL_WORKING_MAP_ENDING
                && row >= ACTUAL_WORKING_MAP_BEGGINIG &&
                row <= ACTUAL_WORKING_MAP_ENDING) {
            buildBuilding(col, row, option);
        }
    }

    private void upgradeBuilding(int col, int row, TerrainType option) {
        switch (option) {
            case DIRT:
                if (stone.getValue() > stoneMineUpgradeCost && cells[col][row].getBuilding().getLvl()< MAX_LVL) {
                    cells[col][row].getBuilding().upgrade();
                    stone.decreasedValue(stoneMineUpgradeCost);
                    stoneMineUpgradeCost *= UPGRADE_MULTIPLER;
                }
                break;
            case ROCK:
                if (metal.getValue() > metalMineUpgradeCost && cells[col][row].getBuilding().getLvl()< MAX_LVL) {
                    cells[col][row].getBuilding().upgrade();
                    metal.decreasedValue(metalMineUpgradeCost);
                    metalMineUpgradeCost *= UPGRADE_MULTIPLER;
                }
                break;
            case GRASS:
                if (wood.getValue() > woodMineUpgradeCost && cells[col][row].getBuilding().getLvl()< MAX_LVL) {
                    cells[col][row].getBuilding().upgrade();
                    wood.decreasedValue(woodMineUpgradeCost);
                    woodMineUpgradeCost *= UPGRADE_MULTIPLER;
                }
                break;
            case SAND:
                    if(cells[col][row].getBuilding().getLvl()< MAX_LVL)
                    cells[col][row].getBuilding().upgrade();
                break;
            default:
                System.out.println("Nie ma takiego typu");
        }
        System.out.println("Poziom tego budynku to " + cells[col][row].getBuilding().getLvl());
    }

    private void buildBuilding(int col, int row, TerrainType option) {
        Building building = null;
        try {
            if (cells[col][row].getBuilding() == null
                    && col >= ACTUAL_WORKING_MAP_BEGGINIG
                    && col <= ACTUAL_WORKING_MAP_ENDING
                    && row >= ACTUAL_WORKING_MAP_BEGGINIG &&
                    row <= ACTUAL_WORKING_MAP_ENDING) {
                switch (option) {
                    case DIRT:
                        if (stone.getValue() >= stoneMineCost) {
                            building = buildingFactory.createNewBuilding("stonemine");
                            stone.decreasedValue(stoneMineCost);
                            stoneMineCost *= COST_MULTIPLER;
                        }
                        break;
                    case SAND:
                            building = buildingFactory.createNewBuilding("humansettling");
                        break;
                    case ROCK:
                        if (metal.getValue() >= metalMineCost) {
                            building = buildingFactory.createNewBuilding("metalmine");
                            metal.decreasedValue(metalMineCost);
                            metalMineCost *= COST_MULTIPLER;
                        }
                        break;
                    case GRASS:
                        if (wood.getValue() >= woodCutterCost) {
                            building = buildingFactory.createNewBuilding("woodcutter");
                            wood.decreasedValue(woodCutterCost);
                            woodCutterCost *= COST_MULTIPLER;
                        }
                        break;
                    default:
                        System.out.println("Ten typ nie jest zdefiniowany");
                }

                building.setX(row * MapGrid.getCellSize());
                building.setY(col * MapGrid.getCellSize());
                cells[col][row].setBuilding(building);
                cellsHolder.setCells(cells);
                System.out.println("Poziom tego budynku to " + cells[col][row].getBuilding().getLvl());
            }
        } catch (NullPointerException e) {
            System.out.println("Nothing happend");
        }
    }

    private void resetCellsAndValues() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                cells[i][j].setBuilding(null);
            }
        }
        cellsHolder.setCells(cells);
        metal.reset();
        wood.reset();
        stone.reset();
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

        /*odpowiada za renderowanie textur komorki*/
        for (int i = ACTUAL_WORKING_MAP_BEGGINIG; i <= ACTUAL_WORKING_MAP_ENDING; i++) {
            for (int j = ACTUAL_WORKING_MAP_BEGGINIG; j <= ACTUAL_WORKING_MAP_ENDING; j++) {
                if (cells[i][j].getBuilding() != null) {
                    cells[i][j].getBuilding().build(mCamera);
                }
            }
        }
        population.drawPeople(mCamera);

        /*zmiana wartosci materialu*/
        timeState += Gdx.graphics.getDeltaTime();
        if (timeState >= 3f) { //Co ile sekund ma sie odswiezac

            timeState = 0f;

            for (int i = ACTUAL_WORKING_MAP_BEGGINIG; i <= ACTUAL_WORKING_MAP_ENDING; i++) {
                for (int j = ACTUAL_WORKING_MAP_BEGGINIG; j <= ACTUAL_WORKING_MAP_ENDING; j++) {
                    if (cells[i][j].getBuilding() != null) {
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
