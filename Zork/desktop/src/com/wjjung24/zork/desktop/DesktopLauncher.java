package com.wjjung24.zork.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.wjjung24.zork.Zork;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.title = "Zork";
		config.width = 1080;
		config.height = 720;
		new LwjglApplication(new Zork(), config);
	}
}
