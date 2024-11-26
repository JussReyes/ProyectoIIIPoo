package Juego.Vista;

import Juego.Controlador.Controlador;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Notificaciones implements Screen, Fuentes {

    private Main game;
    private Controlador controlador;
    
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage; 
    private Skin skin;
    
    private Label titulo;
    private Label volver;
    
    private ShapeRenderer render;
    
    private VerticalGroup grupo;
    private Texture campana = new Texture (Gdx.files.internal("NotifBell.png"));
    private ScrollPane scroll;
    

    public Notificaciones(Main game, Controlador cont) {
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
        volver = new Label("regresar", Fuentes.normales); 
        volver.setPosition(300, 100);
        volver.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new Mapa(game, controlador));
            }
        });
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
        
        stage.addActor(titulo);
        stage.addActor(scroll);
        stage.addActor(volver);
        
        
        
    }
        
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        batch.draw(campana, 692, 109);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
        render.begin(ShapeRenderer.ShapeType.Filled);
        render.setColor(Color.WHITE);
        render.rect(299, 97, 75, 1);
        
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
