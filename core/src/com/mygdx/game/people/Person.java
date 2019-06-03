package com.mygdx.game.people;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.camera.Camera;

public class Person {
    private Vector3 position;
    private Vector3 homePositon;
    private Vector3 workPositon;
    private String  workName;
    private SpriteBatch sprite= new SpriteBatch();
    private final Texture personTexture = new Texture("person.png");
    private final Texture personMiner = new Texture("personMiner.png");
    private final Texture personLamber = new Texture("personLamber.png");
    private final Texture personSmith = new Texture("personSmith.png");


    public Person(Vector3 position, SpriteBatch sprite) {
        this.workName = "Normal";
        this.position = position;
        this.homePositon = position;
        this.sprite = sprite;
    }
    public Person(Vector3 position) {
        this.position = position;
        this.workName = "Normal";
    }

    public void drawPerson(Camera camera){
        sprite.setProjectionMatrix(camera.getmCamera().combined);
        sprite.begin();
        if(workPositon != null) {
            float workX = this.workPositon.x;
            float workY = this.workPositon.y;
            float positionX = this.position.x;
            float positionY = this.position.y;
            if(positionX <= workX){
                position.x++;
            }else{
                position.x--;
            }
            if(positionY <= workY){
                position.y++;
            }else {
                position.y--;
            }
//            if(positionX == workX){
//
//            }
//            if(positionY == workY){
//
//            }
        }
        if(this.workName == "Normal")sprite.draw(personTexture, position.x, position.y);
        else if(this.workName == "stonemine")sprite.draw(personMiner, position.x, position.y);
        sprite.end();
    }


    public void destroy() {
        sprite.dispose();
    }

    public void givePurpose(String name){
        this.workName = name;
    }
    public void setWorkPositon(Vector3 position){
        this.workPositon = position;
    }

}
