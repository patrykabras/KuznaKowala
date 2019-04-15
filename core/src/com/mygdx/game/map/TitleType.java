package com.mygdx.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TitleType {
    TiledMapTileLayer layer;
    TiledMapTileLayer.Cell cell;

    TitleType(TiledMap tiledMap) {
        this.layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
    }

    public TiledMapTileLayer.Cell getCell(int x,int y) {
        return cell = layer.getCell(x,y);
    }

}