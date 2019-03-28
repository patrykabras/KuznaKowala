package com.mygdx.game.screens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapRenderer {
    private TiledMap map;
    private OrthogonalTiledMapRenderer render;

    public MapRenderer() {
//        map = new TmxMapLoader().load("mapTuT.tmx");
        map = new TmxMapLoader().load("generTest.tmx");
        render = new OrthogonalTiledMapRenderer(map);
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public OrthogonalTiledMapRenderer getRender() {
        return render;
    }

    public void setRender(OrthogonalTiledMapRenderer render) {
        this.render = render;
    }
    public void startUp(Camera camera){
        render.setView(camera.getmCamera());
        render.render();
    }
}
