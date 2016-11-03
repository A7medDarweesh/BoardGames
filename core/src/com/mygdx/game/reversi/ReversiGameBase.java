package com.mygdx.game.reversi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ReversiGameBase extends Game{
    SpriteBatch batch;
    BitmapFont font;
    @Override
    public void create() {
        batch = new SpriteBatch();

        font = new BitmapFont();
        this.setScreen(new ReversiGameScreen(this));
        
    }
    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
