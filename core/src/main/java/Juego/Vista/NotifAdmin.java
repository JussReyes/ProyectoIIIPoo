package Juego.Vista;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;

public class NotifAdmin implements Screen, Fuentes {

    private Main game;
    
    private Camera camara;
    private SpriteBatch batch;
    private Stage stage; 
    private Skin skin;
    
    private Label titulo;
    
    private VerticalGroup grupo;
    private ScrollPane scroll;
    
    
    private final Texture fondoSugerencia = new Texture(Gdx.files.internal("AdminRecBG.png"));
    
    private TextField descripcion;
    
    private ImageButton aceptar;
    private final Texture upAceptar = new Texture(Gdx.files.internal("AcceptBtn.png"));
    private final Texture overAceptar = new Texture(Gdx.files.internal("AcceptOver.png"));
    
    private ImageButton eliminar;
    private ImageButton salir;
    private final Texture upCancelar = new Texture(Gdx.files.internal("CancelBtn.png"));
    private final Texture overCancelar = new Texture(Gdx.files.internal("CancelOver.png"));

    public NotifAdmin(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));
        
        titulo = new Label("NOTIFICACIONES", Fuentes.titulos);
        titulo.setPosition(295, 600);
        
        ImageTextButton boton1 = new ImageTextButton("MIT descubre nuevo material que podría destronar\n"
                                                                                        + "al plástico como material de preferencia por las\n"
                                                                                        + "grandes compañias...  Seguir leyendo", skin, "noticia");
        ImageTextButton boton2 = new ImageTextButton("MIT descubre nuevo material que podría destronar\n"
                                                                                        + "al plástico como material de preferencia por las\n"
                                                                                        + "grandes compañias...  Seguir leyendo", skin, "noticia");
        ImageTextButton boton3 = new ImageTextButton("MIT descubre nuevo material que podría destronar\n"
                                                                                        + "al plástico como material de preferencia por las\n"
                                                                                        + "grandes compañias...  Seguir leyendo", skin, "noticia");
        ImageTextButton boton4 = new ImageTextButton("MIT descubre nuevo material que podría destronar\n"
                                                                                        + "al plástico como material de preferencia por las\n"
                                                                                        + "grandes compañias...  Seguir leyendo", skin, "noticia");
        
        ImageTextButton boton5 = new ImageTextButton("¡Tu diseño ha sido aceptado!", skin, "solicitud");
        ImageTextButton boton6 = new ImageTextButton("¡Tu diseño ha sido aceptado!", skin, "solicitud");
        grupo = new VerticalGroup();
        grupo.space(20);
        grupo.addActor(boton1);
        grupo.addActor(boton2);
        grupo.addActor(boton3);
        grupo.addActor(boton4);
        grupo.addActor(boton5);
        grupo.addActor(boton6);
        scroll = new ScrollPane(grupo, skin, "notificaciones");
        scroll.setPosition(75, 67);
        scroll.setSize(550, 492);
        
        
        ImageButtonStyle aceptarEstilo = new ImageButtonStyle();
        aceptarEstilo.up = new TextureRegionDrawable (upAceptar);
        aceptarEstilo.over = new TextureRegionDrawable(overAceptar);
        aceptar = new ImageButton(aceptarEstilo);
        aceptar.setPosition(863, 89);
        
        ImageButtonStyle cancelarEstilo = new ImageButtonStyle();
        cancelarEstilo.up = new TextureRegionDrawable (upCancelar);
        cancelarEstilo.over = new TextureRegionDrawable(overCancelar);
        eliminar = new ImageButton(cancelarEstilo);
        eliminar.setPosition(806, 89);
        
        salir = new ImageButton(cancelarEstilo);
        salir.setPosition(994, 633);
        
        descripcion = new TextArea("Esto es una prueba de la descripción que el usuario va a poner como la descripción del objeto", skin);
        descripcion.setSize(290, 90);
        ScrollPane descripcionScroll = new ScrollPane(descripcion);
        descripcionScroll.setPosition(708, 146);
        descripcionScroll.setSize(303, 100);
        
        stage.addActor(titulo);
        stage.addActor(scroll);
        stage.addActor(aceptar);
        stage.addActor(eliminar);
        stage.addActor(descripcionScroll);
        stage.addActor(salir);
        
    }
        
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        batch.draw(fondoSugerencia, 680, 61);
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
        upAceptar.dispose();
        overAceptar.dispose();
        upCancelar.dispose();
        overCancelar.dispose();
        Fuentes.titulos.font.dispose();
    }
}
