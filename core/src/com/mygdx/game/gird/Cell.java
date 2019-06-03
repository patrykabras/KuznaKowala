package com.mygdx.game.gird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.data.buildings.Building;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Cell {
    private Building building = null;
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
