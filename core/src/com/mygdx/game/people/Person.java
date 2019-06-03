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
    }

    public void drawPerson(Camera camera){
        sprite.setProjectionMatrix(camera.getmCamera().combined);
        sprite.begin();
        sprite.draw(personTexture, position.x, position.y);
//        if(workName.equals("Miner"))sprite.draw(personMiner, position.x, position.y);
        sprite.end();
    }


    public void destroy() {
        sprite.dispose();
    }

}
