package com.mygdx.game.reversi;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BoardPiece extends Actor {
    private ShapeRenderer renderer = new ShapeRenderer();
    private static final int STROKE_WIDTH = 5;
    public BoardPiece(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
    }

    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        renderer.translate(getX(), getY(), 0);

        renderer.begin(ShapeType.Filled);
        renderer.setColor(Color.BLACK);
        renderer.rect(getX(), getY(), getWidth(), getHeight());
        renderer.setColor(Color.GREEN);
        renderer.rect(getX() + STROKE_WIDTH, getY() + STROKE_WIDTH, getWidth() - (STROKE_WIDTH * 2), getHeight() - (STROKE_WIDTH * 2));
        renderer.end();

        batch.begin();

    }
}
