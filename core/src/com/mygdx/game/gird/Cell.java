package com.mygdx.game.gird;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.data.buildings.Building;
import com.mygdx.game.map.TitleType;

public class Cell {
    private Building building;
    private TiledMapTileLayer.Cell titleInfo;

    public TiledMapTileLayer.Cell getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(TiledMapTileLayer.Cell titleInfo) {
        this.titleInfo = titleInfo;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }


}
