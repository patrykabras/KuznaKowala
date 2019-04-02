package com.mygdx.game.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.OptionScreen;

public class KuzniaGame extends Game {
	public SpriteBatch batch;
	public static Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainScreen(this));
		music = Gdx.audio.newMusic(Gdx.files.internal("mb1.ogg"));
		music.setLooping(true);
		music.setVolume(0.02f);
		music.play();


	}


	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
	music.dispose();
	}
}
