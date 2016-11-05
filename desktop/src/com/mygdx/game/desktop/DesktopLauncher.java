package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.reversi.ReversiGameBase;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height=450;
		config.width=450;
		config.title="my awesome game";
            LwjglApplication lwjglApplication = new LwjglApplication(new ReversiGameBase(), config);
            
	}
}
