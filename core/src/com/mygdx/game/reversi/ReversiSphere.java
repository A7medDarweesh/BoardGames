/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game.reversi;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author ahmed_darweeesh
 */
public class ReversiSphere extends Actor {
    private Player player;
    private final ShapeRenderer renderer ;
    void switchPlayer() {
        if(player==Player.BLACK){
            player=Player.WHITE;
        }else{
            player=Player.BLACK;
        }
    }
public ReversiSphere(int x, int y, int width, int height,Player player,ShapeRenderer renderer) {
        setBounds(x, y, width, height);
        this.player=player;
        this.renderer=renderer;
    }
    @Override
    public void act(float delta) {
        super.act(delta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
     
        renderer.setColor(player.getColor());
        
        renderer.ellipse(getX(), getY(), getWidth(), getHeight());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
}
