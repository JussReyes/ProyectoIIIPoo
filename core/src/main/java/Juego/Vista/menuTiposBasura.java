package Juego.Vista;

import Juego.Controlador.Controlador;
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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class menuTiposBasura implements Screen, Fuentes {

    private Main game;
    private Controlador controlador;
    
    private Camera camara;
    private SpriteBatch batch;
    private Stage stage; 
    private Skin skin;
    
    private final Texture fondoTitulo = new Texture(Gdx.files.internal("BGTiposBasura.png"));
    private Label titulo1;
    private Label titulo2;
    
    private ImageButton salir;
    private final Texture upCancelar = new Texture(Gdx.files.internal("CancelBtn.png"));
    private final Texture overCancelar = new Texture(Gdx.files.internal("CancelOver.png"));
    
    private ImageButton papel;
    private final Texture upPapel = new Texture(Gdx.files.internal("papelUp.png"));
    private final Texture overPapel = new Texture(Gdx.files.internal("papelOver.png"));
    
    private ImageButton plastico;
    private final Texture upPlastico = new Texture(Gdx.files.internal("plasticoUp.png"));
    private final Texture overPlastico = new Texture(Gdx.files.internal("plasticoOver.png"));
    
    private ImageButton metal;
    private final Texture upMetal = new Texture(Gdx.files.internal("metalUp.png"));
    private final Texture overMetal = new Texture(Gdx.files.internal("metalOver.png"));
    
    private ImageButton vidrio;
    private final Texture upVidrio = new Texture(Gdx.files.internal("vidrioUp.png"));
    private final Texture overVidrio = new Texture(Gdx.files.internal("vidrioOver.png"));
    
    private ImageButton organico;
    private final Texture upOrganico = new Texture(Gdx.files.internal("organicosUp.png"));
    private final Texture overOrganico = new Texture(Gdx.files.internal("organicosOver.png"));
    
    private ImageButton biologico;
    private final Texture upBiologico = new Texture(Gdx.files.internal("BiologicoUp.png"));
    private final Texture overBiologico = new Texture(Gdx.files.internal("BiologicoOver.png"));
    
    private ImageButton general;
    private final Texture upGeneral = new Texture(Gdx.files.internal("generalUp.png"));
    private final Texture overGeneral = new Texture(Gdx.files.internal("generalOver.png"));
    
    
    public menuTiposBasura(Main game, Controlador cont) {
        this.game = game;
        controlador=cont;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));
        
        ImageButton.ImageButtonStyle cancelarEstilo = new ImageButton.ImageButtonStyle();
        cancelarEstilo.up = new TextureRegionDrawable (upCancelar);
        cancelarEstilo.over = new TextureRegionDrawable(overCancelar);
        salir = new ImageButton(cancelarEstilo);
        salir.setPosition(994, 633);
        salir.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Mapa(game,controlador));
            }
        });
        ImageButton.ImageButtonStyle papelEstilo = new ImageButton.ImageButtonStyle();
        papelEstilo.up = new TextureRegionDrawable (upPapel);
        papelEstilo.over = new TextureRegionDrawable(overPapel);
        papel = new ImageButton(papelEstilo);
        papel.setPosition(859, 70);
        
        ImageButton.ImageButtonStyle plasticoEstilo = new ImageButton.ImageButtonStyle();
        plasticoEstilo.up = new TextureRegionDrawable (upPlastico);
        plasticoEstilo.over = new TextureRegionDrawable(overPlastico);
        plastico = new ImageButton(plasticoEstilo);
        plastico.setPosition(481, 70);
        
        ImageButton.ImageButtonStyle vidrioEstilo = new ImageButton.ImageButtonStyle();
        vidrioEstilo.up = new TextureRegionDrawable (upVidrio);
        vidrioEstilo.over = new TextureRegionDrawable(overVidrio);
        vidrio = new ImageButton(vidrioEstilo);
        vidrio.setPosition(670, 70);
        
        ImageButton.ImageButtonStyle metalEstilo = new ImageButton.ImageButtonStyle();
        metalEstilo.up = new TextureRegionDrawable (upMetal);
        metalEstilo.over = new TextureRegionDrawable(overMetal);
        metal = new ImageButton(metalEstilo);
        metal.setPosition(670, 391);
        
        ImageButton.ImageButtonStyle biologicoEstilo = new ImageButton.ImageButtonStyle();
        biologicoEstilo.up = new TextureRegionDrawable (upBiologico);
        biologicoEstilo.over = new TextureRegionDrawable(overBiologico);
         biologico = new ImageButton(biologicoEstilo);
         biologico.setPosition(102, 70);
        
        ImageButton.ImageButtonStyle generalEstilo = new ImageButton.ImageButtonStyle();
        generalEstilo.up = new TextureRegionDrawable (upGeneral);
        generalEstilo.over = new TextureRegionDrawable(overGeneral);
        general = new ImageButton(generalEstilo);
        general.setPosition(859, 391);
        
        ImageButton.ImageButtonStyle organicoEstilo = new ImageButton.ImageButtonStyle();
        organicoEstilo.up = new TextureRegionDrawable (upOrganico);
        organicoEstilo.over = new TextureRegionDrawable(overOrganico);
        organico = new ImageButton(organicoEstilo);
        organico.setPosition(292, 70);

        
        titulo1 = new Label("TIPOS DE", Fuentes.titulos);
        titulo2 = new Label("BASURA", Fuentes.titulos);
        titulo1.setPosition(114, 555);
        titulo2.setPosition(149, 461);
        
        stage.addActor(titulo1);
        stage.addActor(titulo2);
        stage.addActor(salir);
        stage.addActor(plastico);
        stage.addActor(papel);
        stage.addActor(vidrio);
        stage.addActor(metal);
        stage.addActor(general);
        stage.addActor(organico);
        stage.addActor(biologico);
        
        
        
    }
        
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        batch.draw(fondoTitulo, 0, 338);
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
        fondoTitulo.dispose();
        Fuentes.titulos.font.dispose();
    }
}
