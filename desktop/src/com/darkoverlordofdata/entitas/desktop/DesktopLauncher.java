package com.darkoverlordofdata.entitas.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.darkoverlordofdata.entitas.ShmupWarz;
import com.darkoverlordofdata.entitas.demo.GameController;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameController(), config);
	}
}
