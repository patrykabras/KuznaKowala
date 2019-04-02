package com.mygdx.game.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

public class Camera {
    private OrthographicCamera mCamera;
    public Camera() {
        mCamera = new OrthographicCamera();
    }
    public Camera(int width, int height) {
        mCamera = new OrthographicCamera(width,height);
        mCamera.position.set(mCamera.viewportWidth / 2f, mCamera.viewportHeight / 2f, 0);
        mCamera.update();
    }

    public OrthographicCamera getmCamera() {
        return mCamera;
    }

    public void setmCamera(OrthographicCamera mCamera) {
        this.mCamera = mCamera;
    }

    public void userInput(){
        int speedOfCam = 10;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            mCamera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            mCamera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mCamera.translate(-speedOfCam, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mCamera.translate(speedOfCam, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mCamera.translate(0, -speedOfCam, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mCamera.translate(0, speedOfCam, 0);
        }
        mCamera.zoom = MathUtils.clamp(mCamera.zoom, 0.1f, 500/mCamera.viewportWidth);

        float effectiveViewportWidth = mCamera.viewportWidth * mCamera.zoom;
        float effectiveViewportHeight = mCamera.viewportHeight * mCamera.zoom;

        mCamera.position.x = MathUtils.clamp(mCamera.position.x, effectiveViewportWidth / 2f,  1600 - effectiveViewportWidth / 2f);
        mCamera.position.y = MathUtils.clamp(mCamera.position.y, effectiveViewportHeight / 2f, 1600 - effectiveViewportHeight / 2f);

    }

    public void update(){
        userInput();
        mCamera.update();
    }
}
