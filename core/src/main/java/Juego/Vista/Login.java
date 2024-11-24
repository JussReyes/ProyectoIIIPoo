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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 *
 * @author Thomas
 */
public class Login implements Screen {
    
    private Main game;
    
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    private Skin skin;
    private Label titulo;
    private Label crearCuenta;
    private TextField usuario;
    private TextField contra; 
    private ImageButton ingresar;

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
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));

        titulo = new Label("Iniciar Sesión", skin, "title"); //Poner como imagen porque muy pixeleado :0
        titulo.setPosition(200, 610);
        titulo.setFontScale((float) 2.2, (float) 2.2);

 
        crearCuenta = new Label("Crear Cuenta", skin, "extra"); 
        crearCuenta.setPosition(330, 100);
        
        
       
        
        tortuga =  new Texture(Gdx.files.internal("Tortuga Normal 1.png"));
        fondo = new Texture (Gdx.files.internal("BGLogin.png"));
        
        Texture image = new Texture(Gdx.files.internal("btnIngresar.png"));
        ingresar = new ImageButton(new TextureRegionDrawable(new TextureRegion(image)));
        ingresar.setPosition(265, 150);
         ingresar.addListener(new ChangeListener(){
            
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                if((usuario.getText()).equals("Admin") && (contra.getText()).equals("123")){
                    game.setScreen(new Mapa(game));//Pruebilla humilde
                }
                 else{
                    if(!contra.getText().equals("123") && usuario.getText().equals("Admin")){
                    tortuga =  new Texture(Gdx.files.internal("TortugaContraIncorrecta.png"));    
                    }
                 }
                //Poner demás casos :0
                
            }
        });
        
        
        
        

        
        usuario = new TextField("", skin);
        usuario.setColor(0.851f, 0.851f, 0.851f, 1.0f);
        usuario.setPosition(212, 395);
        usuario.setSize(360, 42);
        usuario.setMaxLength(20);
        
        contra = new TextField("", skin);
        contra.setPasswordMode(true);
        contra.setPasswordCharacter('*');
        contra.setPosition(212, 297);
        contra.setSize(360, 42);
        contra.setMaxLength(20);
        
        stage.addActor(titulo);
        stage.addActor(usuario);
        stage.addActor(contra);
        stage.addActor(ingresar);
        stage.addActor(crearCuenta);
        
        
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0.851f, 0.851f, 0.851f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        usuario.setColor(0.851f, 0.851f, 0.851f, 1);
        contra.setColor(0.851f, 0.851f, 0.851f, 1);

        // Start drawing with SpriteBatch
        batch.begin();

        batch.draw(fondo, 150, 50);
        batch.draw(tortuga, 680, 20);
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
        font.dispose();
    }
    
}
