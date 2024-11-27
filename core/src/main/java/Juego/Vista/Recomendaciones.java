package Juego.Vista;

import Juego.Controlador.Controlador;
import Juego.Modelo.Recomendacion;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Recomendaciones implements Screen, Fuentes {
    
    private Controlador controlador;

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
    
    private ShapeRenderer render;
    
    private Texture pintura;
    private Texture fondo;
    
    private Label titulo;
    private Label volver;

    public Recomendaciones(Main game, Controlador cont) {
        this.game = game;
        controlador=cont;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        font = game.font;
        
        render = new ShapeRenderer();

        
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
        enviar.addListener(new ChangeListener(){
            
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                String nom = nombre.getText();
                String desc = descripcion.getText();
                
                String ruta="";////////////////////////////
                try {
                   if (nom.isBlank())
                    throw new IllegalArgumentException("        "+"Ingrese el nombre");
                
                if (desc.isBlank())
                    throw new IllegalArgumentException("      Ingrese la descripción");
                
                if (ruta.isBlank())
                    throw new IllegalArgumentException("      Ingrese la imagen");
                
                
                String basu = selectBox.getSelected();
                Recomendacion reco = new Recomendacion(nom, ruta, basu, desc);
                }
                
                catch (IllegalArgumentException ex) {
                    
                }
            }
            
        });
        
        volver = new Label("regresar", Fuentes.normales); 
        volver.setPosition(290, 100);
        volver.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new Mapa(game, controlador));
            }
        });
        
        stage.addActor(titulo);
        stage.addActor(selectBox);
        stage.addActor(imagen);
        stage.addActor(nombre);
        stage.addActor(descripcion);
        stage.addActor(enviar);
        stage.addActor(volver);
        
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
        
        render.begin(ShapeRenderer.ShapeType.Filled);
        render.setColor(Color.WHITE);
        render.rect(289, 97, 75, 1);
        
        render.end();
        
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
