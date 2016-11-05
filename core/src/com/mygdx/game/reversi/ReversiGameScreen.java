package com.mygdx.game.reversi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.javafx.scene.traversal.Direction;
import java.util.LinkedList;
import java.util.List;

public class ReversiGameScreen implements Screen {
    private final Stage stage;
    ReversiGameBase game;
    private int  boardWidth=8,boardHeight=8,pieceWidth=50,pieceHeiht=50;
    private boolean firstDraw=true;
    private Player player;
    int addedSphere;
    private final BoardPiece[]pieces=new BoardPiece[boardWidth*boardHeight];

    public ReversiGameScreen(ReversiGameBase reversiGameBase) {
        game = reversiGameBase;
        
        ScreenViewport screen = new ScreenViewport();
        stage = new Stage(screen);
        Gdx.input.setInputProcessor(stage);
        player=Player.WHITE;
    }

    private void DrawBaord() {
        
        int screenHeight = stage.getViewport().getScreenWidth();
        int screenWidth = stage.getViewport().getScreenWidth();
        int xTranslate=(screenWidth-(boardWidth*pieceWidth))/2;
        int yTranselate=(screenHeight-(boardHeight*pieceHeiht))/2;
        System.out.println("x="+xTranslate+";;y"+yTranselate);
        
        
        for(int i=0;i<boardHeight*boardWidth;i++){
            BoardPiece currentpBoardPiece=new BoardPiece(xTranslate+(i%boardWidth)*pieceWidth,yTranselate+(i/boardWidth)*pieceHeiht, pieceWidth, pieceHeiht,this,i);
            pieces[i]=currentpBoardPiece;
            stage.addActor(currentpBoardPiece);
        }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.7f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        if(firstDraw){
            DrawBaord();
            firstDraw=false;
        }
        

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        stage.dispose();

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    void switchPlayer() {
        if(player==Player.BLACK){
            player=Player.WHITE;
        }else{
            player=Player.BLACK;
        }
    }

    void doMove(int tileNumber) {
        addedSphere++;
        for(Directions dir:Directions.values()){
            int nextTileNumber=tileNumber;
            List<ReversiSphere>spheresToFlip=new LinkedList<>();
        while(true){
            nextTileNumber+=dir.getStep();
            if(nextTileNumber<0||nextTileNumber>=pieces.length){
                 break;
            }
               
                BoardPiece currentPiece = pieces[nextTileNumber];
                if(currentPiece.sphere==null){
                    break;
                }
                if(player==currentPiece.sphere.getPlayer()){
                    for(ReversiSphere s:spheresToFlip){
                        s.switchPlayer();
                    }
                    break;
                }
                   spheresToFlip.add(currentPiece.sphere);
        }
    }
        if(addedSphere>=pieces.length){
            showScore();
        }
    }

    private void showScore() {
        
    }

}
