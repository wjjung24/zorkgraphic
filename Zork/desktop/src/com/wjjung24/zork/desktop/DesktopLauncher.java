package com.wjjung24.zork.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.wjjung24.zork.Zork;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.resizable = false;
		config.title = "Zork";
		config.width = 1080;
		config.height = 590;
		new Lwjgl3Application(new Zork(), config);
	}
}
