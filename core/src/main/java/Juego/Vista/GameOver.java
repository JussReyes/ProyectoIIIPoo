/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Vista;

import Juego.Controlador.Controlador;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 *
 * @author Thomas
 */
public class GameOver implements Screen, Fuentes{
    
    private Controlador controlador;
    
    private Main game;
    private boolean win;
    
    private Camera camara;
    private SpriteBatch batch;
    
    private Stage stage;
    private Skin skin;
    
    private Label mensaje1;
    private Label mensaje2;
    
    private ImageButton regresar;
    private final Texture imgRegresar = new Texture(Gdx.files.internal("BackBtn.png"));
    private final Texture imgSelectoRegresar = new Texture(Gdx.files.internal("SelectedBackBtn.png"));   
    
    private ImageButton jugar;
    private final Texture imgJugar = new Texture(Gdx.files.internal("PlayBtn.png"));
    private final Texture imgSelectoJugar = new Texture(Gdx.files.internal("SelectedPlayBtn.png"));
    
    private Texture fondo;
    private Texture sombra;
    private Texture tortuga;
    
    public GameOver(Main game, Controlador ct, boolean win) {
        this.game = game;
        this.win = win;
        controlador=ct;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        
        ImageButtonStyle BackEstilo = new ImageButtonStyle();
        BackEstilo.up = new TextureRegionDrawable(imgRegresar);
        BackEstilo.over = new TextureRegionDrawable(imgSelectoRegresar);
        
        regresar = new ImageButton(BackEstilo);
        regresar.setPosition(306, 82);
        regresar.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Mapa(game,controlador));
            }
        });
        
        ImageButtonStyle jugarEstilo = new ImageButtonStyle();
        jugarEstilo.up = new TextureRegionDrawable(imgJugar);
        jugarEstilo.over = new TextureRegionDrawable(imgSelectoJugar);
        
        jugar = new ImageButton(jugarEstilo);
        jugar.setPosition(567, 84);
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));

        fondo = new Texture (Gdx.files.internal("Frame.png"));
        sombra = new Texture (Gdx.files.internal("Shadow.png"));
        
        if (win) {
            tortuga = new Texture(Gdx.files.internal("Happy.png"));
            
            mensaje1 = new Label("¡Has superado", Fuentes.titulos);
            mensaje1.setPosition(330, 600);
            
            mensaje2 = new Label("este nivel!", Fuentes.titulos);
            mensaje2.setPosition(394, 540);

        }
        else{
            tortuga = new Texture(Gdx.files.internal("Sad.png"));
            
            mensaje1 = new Label("Has fallado", Fuentes.titulos);
            mensaje1.setPosition(378, 600);
            
            mensaje2 = new Label("este nivel", Fuentes.titulos);
            mensaje2.setPosition(403, 540);
            
        }
        
        stage.addActor(regresar);
        stage.addActor(jugar);
        stage.addActor(mensaje1);
        stage.addActor(mensaje2);
        
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0.851f, 0.851f, 0.851f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        batch.draw(fondo, 0, 0);
        batch.draw(sombra, 425, 285);
        batch.draw(tortuga, 449, 299);
        batch.end();
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
        // Handle screen transitions or input events (optional)
        if (Gdx.input.isTouched()) {
            // Switch to another screen (if desired)
            // game.setScreen(new AnotherScreen(game));
        }
         camara.update();
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
        batch.dispose();
        fondo.dispose();
        imgJugar.dispose();
        imgSelectoJugar.dispose();
        imgSelectoRegresar.dispose();
        imgRegresar.dispose();
        
    }
    
}
