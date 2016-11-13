/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game.domino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameScreen;

/**
 *
 * @author ahmed_darweeesh
 */
public class DominoGameScreen implements Screen{
    Stage Stage;
    SpriteBatch drawBatch;
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.7f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int i, int i1) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
       
    }

    @Override
    public void dispose() {
       
    }
    
}
