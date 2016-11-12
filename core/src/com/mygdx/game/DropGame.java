package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class DropGame extends Game {
    SpriteBatch batch;
    private OrthographicCamera camera;
    ShaderProgram shadowMapShader, shadowRenderShader;
    BitmapFont font;
    Texture image;
    String vertexShader = "attribute vec4 a_position; uniform mat4 u_projTrans; varying vec3 normal;void main(){normal = gl_NormalMatrix * gl_Normal;gl_Position =  u_projTrans * a_position;    gl_Position = ftransform();}";
    String fragementShader = "uniform sampler2D u_texture;varying vec3 normal;varying vec2 v_texCoords;"
            + "void main(){    float intensity;    vec4 color;    vec3 n = normalize(normal);    intensity = dot(vec3(gl_LightSource[0].position),n);"
            + "    if (intensity > 0.95)        color = vec4(1.0,0.5,0.5,1.0);    else if (intensity > 0.5)        color = vec4(0.6,0.3,0.3,1.0); "
            + "   else if (intensity > 0.25)        color = vec4(0.4,0.2,0.2,1.0);    else        color = vec4(0.2,0.1,0.1,1.0);    gl_FragColor = color*texture2D(u_texture, v_texCoords);}";
    ShaderProgram program;
    @Override
    public void create() {
        final String VERT_SRC = Gdx.files.internal("pass.vert").readString();

        // renders occluders to 1D shadow map
        shadowMapShader = createShader(VERT_SRC, Gdx.files.internal("shadowMap.frag").readString());
        // samples 1D shadow map to create the blurred soft shadow
        shadowRenderShader = createShader(VERT_SRC, Gdx.files.internal("shadowRender.frag").readString());
        batch = new SpriteBatch();
        program = new ShaderProgram(Gdx.files.internal("radial-blur.vertex"), Gdx.files.internal("radial-blur.fragment"));
        // program = new ShaderProgram(vertexShader, fragementShader);
        System.out.println(program.getLog());
        if (!program.isCompiled()) {
            System.out.println(program.getLog());
            System.exit(-1);
        }
        // Use LibGDX's default Arial font.
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
        image = new Texture(Gdx.files.internal("badlogic.jpg"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1.25f, 0.25f, 0.25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(image, 50, 100);
        // batch.setShader(program);
        batch.draw(new Texture("light.png"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public static ShaderProgram createShader(String vert, String frag) {
        ShaderProgram prog = new ShaderProgram(vert, frag);
        if (!prog.isCompiled())
            throw new GdxRuntimeException("could not compile shader: " + prog.getLog());
        if (prog.getLog().length() != 0)
            Gdx.app.log("GpuShadows", prog.getLog());
        return prog;
    }
}
