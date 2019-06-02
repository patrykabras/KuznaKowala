package com.mygdx.game.people;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.camera.Camera;

public class Person {
    private Vector3 position;
    private SpriteBatch sprite= new SpriteBatch();
    private final Texture personTexture = new Texture("person.png");

    public Person(Vector3 position, SpriteBatch sprite) {
        this.position = position;
        this.sprite = sprite;
    }
    public Person(Vector3 position) {
        this.position = position;
    }

    public void drawPerson(Camera camera){
        sprite.setProjectionMatrix(camera.getmCamera().combined);
        sprite.begin();
        sprite.draw(personTexture, position.x, position.y);
        sprite.end();
    }


    public void destroy() {
        sprite.dispose();
    }

}
