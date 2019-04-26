package com.mygdx.game.data.buildings;

import com.mygdx.game.exceptions.NoSuchBuildingException;

public class BuildingFactory {

    public Building createNewBuilding(String type){
        type = type.toLowerCase();

        if (type.equalsIgnoreCase("StoneMine")){
            return new StoneMine();
        }else if (type.equalsIgnoreCase("MetalMine")){
            return new MetalMine();
        }else if (type.equalsIgnoreCase("WoodCutter")){
            return new WoodCutter();
        }else throw new NoSuchBuildingException("There's no such building type!");
    }
}
