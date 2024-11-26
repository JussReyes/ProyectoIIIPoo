package Juego.Vista;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Recomendaciones implements Screen, Fuentes {

    private Main game;
    
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontTitle;
    
    private Stage stage;
    private Skin skin;
    private SelectBox<String> selectBox;
    private TextField nombre;
    private TextField descripcion;
    private ImageButton imagen;
    private ImageTextButton enviar;
    
    private Texture pintura;
    private Texture fondo;
    
    private Label titulo;

    public Recomendaciones(Main game) {
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
        
        titulo = new Label("Diseño", Fuentes.titulos);
        titulo.setPosition(437, 600);
        
        selectBox = new SelectBox<>(skin);
        selectBox.setItems("Plástico", "Metal", "Papel", "Biológico", "Orgánico", "Vidrio", "General");
        
        selectBox.setSize(215, 30);
        selectBox.setAlignment(1);
        selectBox.setPosition(130, 314);
        
        pintura =  new Texture(Gdx.files.internal("BigPaint.png"));
        fondo = new Texture (Gdx.files.internal("BGRecomendaciones.png"));
        
        Texture image = new Texture(Gdx.files.internal("ImageButton.png"));
        imagen = new ImageButton(new TextureRegionDrawable(new TextureRegion(image)));
        imagen.setPosition(404, 314);
        imagen.setSize(134, 134);
        
        nombre = new TextField("", skin);
        nombre.setMessageText("Nombre");
        nombre.setPosition(130, 420);
        nombre.setSize(215, 30);
        
        descripcion = new TextArea("", skin);
        descripcion.setMessageText("\n           Describe el tipo de desecho");
        descripcion.setPosition(130, 147);
        descripcion.setSize(409, 100);
        
        enviar = new ImageTextButton("ENVIAR", skin);
        enviar.setPosition(740, 86);
        enviar.setSize(230, 68);
        
        stage.addActor(titulo);
        stage.addActor(selectBox);
        stage.addActor(imagen);
        stage.addActor(nombre);
        stage.addActor(descripcion);
        stage.addActor(enviar);
        
    }
        
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        batch.draw(fondo, 72, 66);
        batch.draw(pintura, 718, 192);
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
    public void resize(int width, int height) {
        // Handle resizing if needed
    }

    @Override
    public void hide() {
        // Clean up resources
    }

    @Override
    public void pause() {
        // Handle pause (if needed)
    }

    @Override
    public void resume() {
        // Handle resume (if needed)
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
