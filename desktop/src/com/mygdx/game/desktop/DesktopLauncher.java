package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.app.KuzniaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Kuznia Project";
		config.width=720;
		config.height=420;
//		config.width=1440;
//		config.height=840;
		config.backgroundFPS=60;
		config.foregroundFPS=60;
		config.resizable=true;
		new LwjglApplication(new KuzniaGame(), config);
	}
}
