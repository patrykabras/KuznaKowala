package com.mygdx.game.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.camera.Camera;
import com.mygdx.game.data.buildings.MetalMine;
import com.mygdx.game.gird.GridRenderer;
import com.mygdx.game.hud.Hud;
import com.mygdx.game.map.MapGenerator;
import com.mygdx.game.map.MapRenderer;
import com.mygdx.game.screens.PauseScreen;

public class GameActive implements Screen {

    private final KuzniaGame game;
    private Camera mCamera;
    private MapRenderer mapRenderer;
    private GridRenderer gridRenderer;
    private MapGenerator test;
    private Hud hud;
    private Viewport gamePort;


    public GameActive(KuzniaGame game){
        this.game=game;
        mCamera = new Camera();
        test = new MapGenerator();
        mapRenderer = new MapRenderer();
        gridRenderer = new GridRenderer();
        hud = new Hud(game.batch,game);
//        gamePort = new FitViewport(720, 420, mCamera.getmCamera());
//        gamePort = new ExtendViewport(720,420,mCamera.getmCamera());
//        gamePort.apply();
    }
    @Override
    public void show() {

    }
    public void userInput(){
        //escape button to menu
        if(Gdx.input.isKeyPressed(131))
        {
            game.setScreen(new PauseScreen(game));
            this.pause();
        }
        if(Gdx.input.isKeyPressed(62)){
            System.out.println("SPACJA!");
            test = new MapGenerator();
            mapRenderer = new MapRenderer();
        }
        if(Gdx.input.isTouched()){

            Vector3 mousePosition = new Vector3(0,0,0);
            mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            float deltaX = (float)Gdx.input.getDeltaX();
            float deltaY = (float)Gdx.input.getDeltaY();
            mCamera.getmCamera().translate(-deltaX, deltaY, 0);
            Vector3 mouseClickPositon = mCamera.getmCamera().unproject(mousePosition);
            System.out.println("Pozycja Myszki: "+mouseClickPositon);
            System.out.println("Grid to:" + numberOfGrid(mousePosition));
        }
    }
    public int numberOfGrid(Vector3 mousePosition){
        float x = mousePosition.x;
        float y = mousePosition.y;
        int w = (int)x/gridRenderer.getGrid().getCellSize();
        int h = (int)y/gridRenderer.getGrid().getCellSize();
        return ((h)*gridRenderer.getGrid().getMapSize())+(w);
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
        int aspect = width/height;
        mCamera = new Camera(width * aspect,height * aspect);
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
