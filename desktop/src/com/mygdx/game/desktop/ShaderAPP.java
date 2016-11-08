package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DropGame;

public class ShaderAPP {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 450;
        config.width = 450;
        config.title = "Reversi";
        new LwjglApplication(new DropGame(), config);
    }

}
