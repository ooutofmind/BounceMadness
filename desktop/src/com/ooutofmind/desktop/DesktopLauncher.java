package com.ooutofmind.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ooutofmind.BounceMadness;
import com.ooutofmind.Const;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Const.WIDTH * Const.SCALE;
		config.height = Const.HEIGHT * Const.SCALE;
		new LwjglApplication(new BounceMadness(), config);
	}
}
