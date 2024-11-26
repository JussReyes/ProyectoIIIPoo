/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Vista;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 *
 * @author Thomas
 */
public class Login implements Screen, Fuentes{
    
    private Main game;
    
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer render;
    
    private Stage stage;
    private Skin skin;
    private Label crearCuenta;
    private Label titulo;
    
    private TextField usuario;
    private TextField contra; 
    private ImageTextButton ingresar;

    private Texture tortuga;
    private Texture fondo;
    
    public Login(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        font = game.font;
        render = new ShapeRenderer();
        
        titulo = new Label("Iniciar Sesión", Fuentes.titulos);
        titulo.setPosition(200, 600);
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));

        crearCuenta = new Label("Crear Cuenta", Fuentes.normales); 
        crearCuenta.setPosition(343, 120);
        crearCuenta.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new CrearCuenta(game));
            }
        });
       
        tortuga =  new Texture(Gdx.files.internal("Tortuga Normal 1.png"));
        fondo = new Texture (Gdx.files.internal("BGLogin.png"));
        
        ingresar = new ImageTextButton("Ingresar", skin);
        ingresar.setSize(258, 64);
        ingresar.setPosition(270, 170);
        ingresar.addListener(new ChangeListener(){
            
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                if((usuario.getText()).equals("Admin") && (contra.getText()).equals("123")){
                    game.setScreen(new Mapa(game));//Pruebilla humilde
                }
                 else{
                    if(!contra.getText().equals("123") && usuario.getText().equals("Admin")){
                    tortuga =  new Texture(Gdx.files.internal("TortugaError.png"));    
                    }
                 }
                
                
            }
        });
        
        
        usuario = new TextField("", skin);
        usuario.setPosition(218, 395);
        usuario.setSize(360, 42);
        usuario.setMaxLength(20);
        
        contra = new TextField("", skin);
        contra.setPasswordMode(true);
        contra.setPasswordCharacter('*');
        contra.setPosition(218, 297);
        contra.setSize(360, 42);
        contra.setMaxLength(20);
        
        stage.addActor(usuario);
        stage.addActor(contra);
        stage.addActor(ingresar);
        stage.addActor(titulo);
        stage.addActor(crearCuenta);
        
        
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0.851f, 0.851f, 0.851f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Start drawing with SpriteBatch
        batch.begin();

        batch.draw(fondo, 150, 50);
        batch.draw(tortuga, 680, 20);
        batch.end();
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
        render.begin(ShapeRenderer.ShapeType.Filled);
        render.setColor(Color.WHITE);
        render.rect(343, 120, 112, 1);
        render.end();
        
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
        font.dispose();
        render.dispose();
        tortuga.dispose();
    }
    
}
