package com.mygdx.game.reversi;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bitfire.postprocessing.PostProcessor;
import com.bitfire.postprocessing.effects.Bloom;
import com.bitfire.utils.ShaderLoader;

public class ReversiGameScreen implements Screen {
    private final Stage stage;
    ReversiGameBase game;
    private int  boardWidth=8,boardHeight=8,pieceWidth=50,pieceHeiht=50;
    private boolean firstDraw=true;
    private Player player;
    int addedSphere;
    public PostProcessor postProcessor;
    public Bloom bloom;
    private final BoardPiece[]pieces=new BoardPiece[boardWidth*boardHeight];

    public ReversiGameScreen(ReversiGameBase reversiGameBase) {
        ShaderLoader.BasePath = "shaders/";
        game = reversiGameBase;
        Viewport fitViewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ScreenViewport screen = new ScreenViewport();
        stage = new Stage(screen);
        Gdx.input.setInputProcessor(stage);
        player=Player.WHITE;
        setUpPostProcessor();
    }

    private void setUpPostProcessor() {
        boolean isDesktop = (Gdx.app.getType() == ApplicationType.Desktop);
        postProcessor = new PostProcessor(false, true, isDesktop);
        bloom = new Bloom((int) (Gdx.graphics.getWidth() * 0.25f), (int) (Gdx.graphics.getHeight() * 0.25f));

        postProcessor.addEffect(bloom);
        //

        // initializeEffects();
    }

    private void DrawBaord(boolean redraw) {
        postProcessor.capture();
        pieceWidth = Gdx.graphics.getWidth() / 10;
        pieceHeiht = Gdx.graphics.getHeight() / 10;
        int screenHeight = Gdx.graphics.getHeight();
        int screenWidth = Gdx.graphics.getWidth();
        int xTranslate=(screenWidth-(boardWidth*pieceWidth))/2;
        int yTranselate=(screenHeight-(boardHeight*pieceHeiht))/2;
        
        if (redraw) {
            Array<Actor> actors = stage.getActors();
            for (int i = 0; i < actors.size; i++) {
                actors.get(i).setBounds(xTranslate + (i % boardWidth) * pieceWidth, yTranselate + (i / boardWidth) * pieceHeiht, pieceWidth, pieceHeiht);
            }
        } else {

            for (int i = 0; i < boardHeight * boardWidth; i++) {
                BoardPiece currentpBoardPiece = new BoardPiece(xTranslate + (i % boardWidth) * pieceWidth, yTranselate + (i / boardWidth) * pieceHeiht, pieceWidth, pieceHeiht, this, i);
                pieces[i] = currentpBoardPiece;
                stage.addActor(currentpBoardPiece);
            }
        }
        postProcessor.render();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.7f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        if(firstDraw){
            DrawBaord(false);
            firstDraw=false;
        }
        

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        DrawBaord(true);

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
            List<ReversiSphere> spheresToFlip = new LinkedList<ReversiSphere>();
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
                if (nextTileNumber % 8 == 0 || nextTileNumber % 8 == 7) {
                    break;
                }
        }
    }
        if(addedSphere>=pieces.length){
            showScore();
        }
    }

    private void showScore() {
        
    }

}
