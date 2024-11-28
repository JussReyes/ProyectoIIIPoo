package Juego.Vista;

import Juego.Controlador.Controlador;
import Juego.Controlador.SelectorDeImagen;
import Juego.Modelo.Recomendacion;
import Juego.Modelo.Usuario;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
    private String ruta="";

    private Main game;
    
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontTitle;
    
    private Stage stage;
    private Skin skin;
    private SelectBox<String> selectBox;
    private TextField nombre;
    private TextField descomposicion;
    
    private TextField descripcion;
    private TextField recomendaciones;
    private ImageButton imagen;
    private ImageTextButton enviar;
    
    private ImageButton salir;
    private final Texture upCancelar = new Texture(Gdx.files.internal("CancelBtn.png"));
    private final Texture overCancelar = new Texture(Gdx.files.internal("CancelOver.png"));

    
    private Texture pintura;
    private Texture fondo;

    public Recomendaciones(Main game, Controlador cont) {
        this.game = game;
        controlador=cont;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        font = game.font;

        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));
        
        
        pintura =  new Texture(Gdx.files.internal("BigPaint.png"));
        fondo = new Texture (Gdx.files.internal("RecomendacionesBG.png"));
        
        Texture image = new Texture(Gdx.files.internal("ImageButton.png"));
        imagen = new ImageButton(new TextureRegionDrawable(new TextureRegion(image)));
        imagen.setPosition(387, 385);
        //imagen.setSize(134, 134);
        
        imagen.addListener(new ChangeListener(){
            
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                SelectorDeImagen fileChooserExample = new SelectorDeImagen();
                String rutas = fileChooserExample.copiarImagenAlRepositorio("assets");
                
                if (rutas != null) {
                    String rutaRelativa = rutas.substring(rutas.lastIndexOf("##")+2);
                    String rutaImagen =rutas.substring(0, rutas.lastIndexOf("##"));
                    System.out.println("Ruta relativa en el proyecto: " + rutaRelativa);
                    Texture newImagen = new Texture(Gdx.files.internal(rutaImagen));
                    System.out.println(newImagen.getHeight());
                    imagen.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(newImagen));
                    ruta=rutaRelativa;
                }

            }
            
        });
        
        nombre = new TextField("", skin);
        nombre.setMessageText("Nombre");
        nombre.setPosition(91, 514);
        nombre.setSize(215, 30);
        
        selectBox = new SelectBox<>(skin);
        selectBox.setItems("Plástico", "Metal", "Papel", "Biológico", "Orgánico", "Vidrio", "General");
        
        selectBox.setSize(215, 30);
        selectBox.setAlignment(1);
        selectBox.setPosition(91, 442);
        
        descomposicion = new TextField("", skin);
        descomposicion.setMessageText("Tiempo en días");
        descomposicion.setPosition(91, 370);
        descomposicion.setSize(215, 30);
        
        descripcion = new TextArea("", skin);
        descripcion.setMessageText("\n                Describe el tipo de desecho");
        descripcion.setPosition(91, 215);
        descripcion.setSize(460, 87);
        
        
        recomendaciones = new TextArea("", skin);
        recomendaciones.setMessageText("\n     Recomendaciones para el tratamiento");
        recomendaciones.setPosition(91, 59);
        recomendaciones.setSize(460, 87);
        
        enviar = new ImageTextButton("ENVIAR", skin);
        enviar.setPosition(740, 86);
        enviar.setSize(230, 68);
        enviar.addListener(new ChangeListener(){
            
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                String nom = nombre.getText();
                String desc = descripcion.getText();
                String recom = recomendaciones.getText();
                int dias;
                
                
                try {
                    if (descomposicion.getText().isBlank())
                        throw new IllegalArgumentException("Ingrese el número de días");
                    try{
                        dias = Integer.parseInt(descomposicion.getText());
                    }
                    catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Ingrese un número de días para el tiempo de descomposición");
                    }

                    if (nom.isBlank())
                     throw new IllegalArgumentException("        "+"Ingrese el nombre");
                    if (desc.isBlank())
                        throw new IllegalArgumentException("      Ingrese la descripción");
                    if (ruta.isBlank())
                        throw new IllegalArgumentException("      Ingrese la imagen");


                    String basu = selectBox.getSelected();
                    controlador.añadirSugerencia(ruta, nombre.getText(), recomendaciones.getText(), 
                            descripcion.getText(),selectBox.getSelected(),descomposicion.getText(), controlador.getUsuarioActual());
                }
                
                catch (IllegalArgumentException ex) {
                    System.out.println("AJAAAAAAA FALTA CODE: "+ex.getMessage());
                }
            }
            
        });
        
        ImageButton.ImageButtonStyle cancelarEstilo = new ImageButton.ImageButtonStyle();
        cancelarEstilo.up = new TextureRegionDrawable (upCancelar);
        cancelarEstilo.over = new TextureRegionDrawable(overCancelar);
        salir = new ImageButton(cancelarEstilo);
        salir.setPosition(994, 633);
        salir.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new Mapa(game, controlador));
            }
        });
        

        stage.addActor(selectBox);
        stage.addActor(imagen);
        stage.addActor(nombre);
        stage.addActor(descomposicion);
        stage.addActor(descripcion);
        stage.addActor(recomendaciones);
        stage.addActor(enviar);
        
    }
        
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        batch.draw(fondo, 0, 0);
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
