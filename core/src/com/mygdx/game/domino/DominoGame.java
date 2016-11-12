/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game.domino;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author ahmed_darweeesh
 */
public class DominoGame extends Game{
SpriteBatch batch;
    BitmapFont font;
    @Override
    public void create() {
        
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
