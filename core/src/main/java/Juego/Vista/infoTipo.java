/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Vista;

import Juego.Controlador.Controlador;
import Juego.Controlador.ManejadorArchivoBasuras;
import Juego.Modelo.Basura;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import sun.font.FontScaler;

/**
 *
 * @author xande
 */
public class infoTipo implements Screen{

    String tipo;
    private Main game;
    private Controlador controlador;
    private Camera camara;
    private SpriteBatch batch;
    private Stage stage; 
    private Skin skin;
    
    private Label titulo;
    
    private ImageButton salir;
    private final Texture upCancelar = new Texture(Gdx.files.internal("CancelBtn.png"));
    private final Texture overCancelar = new Texture(Gdx.files.internal("CancelOver.png"));
    
    private TextArea descripcion;
    private Label lblDescripcion;
    
    private Label lblRecomendaciones;
    private TextArea recomendaciones;
    
    private Label lblDescomposicion;
    private TextField descomposicion;
    
    private Label lblBasurero;        
    private TextField basurero;
    
    private Label nombre;
    
    private Label volver;
    private ShapeRenderer render;
    private VerticalGroup grupo;
    private ScrollPane scroll;
    
    private ArrayList<Basura> basurasTipo = new ArrayList<>();
    public infoTipo(Main game, Controlador cont, String tipo) {
        this.game=game;
        controlador=cont;
        this.tipo=tipo;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        Fuentes.titulos.fontColor.set(Color.WHITE);
        render = new ShapeRenderer();
        
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));

        ImageButton.ImageButtonStyle cancelarEstilo = new ImageButton.ImageButtonStyle();
        cancelarEstilo.up = new TextureRegionDrawable (upCancelar);
        cancelarEstilo.over = new TextureRegionDrawable(overCancelar);
        salir = new ImageButton(cancelarEstilo);
        salir.setPosition(994, 633);
        salir.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new Mapa(game,controlador));
            }
        });
        
        titulo = new Label("INFORMACIÓN", Fuentes.titulos);
        titulo.setPosition(295, 600);
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));
        
                
        nombre = new Label("Nombre", Fuentes.titulos);
        
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(Fuentes.titulos.font, "Nombre");
        float anchoTexto = glyphLayout.width; 
        float xPos = 383 + (722 - anchoTexto) / 2;
        nombre.setPosition(xPos, 600);
        
        lblBasurero = new Label("Basurero", Fuentes.bold);
        lblBasurero.setPosition(477, 551);
        
        basurero = new TextField("Tipo de basurero", skin);
        basurero.setPosition(477, 485);
        basurero.setSize(255, 56);
        basurero.setTouchable(Touchable.disabled);
        
        lblDescomposicion = new Label("Descomposición", Fuentes.bold);
        lblDescomposicion.setPosition(760, 551);        
        
        descomposicion = new TextField("Días para descomponerse", skin);
        descomposicion.setPosition(760, 485);
        descomposicion.setSize(255, 56);
        descomposicion.setTouchable(Touchable.disabled);
       
        lblDescripcion = new Label("Descripción", Fuentes.bold);
        lblDescripcion.setPosition(477, 236);
        
        descripcion = new TextArea("Texto de descripción..",skin);
        descripcion.setPosition(477, 70);
        descripcion.setSize(539, 156);
        descripcion.setTouchable(Touchable.disabled);
        
        lblRecomendaciones = new Label("Recomendaciones de desecho", Fuentes.bold);
        lblRecomendaciones.setPosition(477, 441);
        
        recomendaciones = new TextArea("Texto de las recomendaciones de desecho..",skin);
        recomendaciones.setPosition(477, 275);
        recomendaciones.setSize(539, 156);
        recomendaciones.setTouchable(Touchable.disabled);
        
        grupo = new VerticalGroup();
        grupo.space(20);
        
        ArrayList<Basura> basuras = controlador.getBasuras();
        System.out.println("Total "+basuras.size());

        for (Basura basura:basuras) {
            System.out.println(basura.toString());
            if (basura.getTipoBasurero().toString().equals(tipo))
                basurasTipo.add(basura);
        }
        System.out.println("Hay "+basurasTipo.size());
        for (int i=0;i<basurasTipo.size();i++) {
            Basura basura = basurasTipo.get(i);
            Texture im = new Texture(Gdx.files.internal(basura.getRutaImagen()));
            ImageButton imagen= new ImageButton(new TextureRegionDrawable(new TextureRegion(im)));
            imagen.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        nombre.setText(basura.getNombre());
                        basurero.setText(basura.getTipoBasurero().toString());
                        descomposicion.setText(String.valueOf(basura.getTiempoDescomposicion()));
                        recomendaciones.setText(basura.getRecomendaciones());
                        descripcion.setText(basura.getDescripcion());
                }   
            });

            grupo.addActor(imagen);
        }
        
        scroll = new ScrollPane(grupo, skin, "notificaciones");
        scroll.setPosition(20, 25);
        scroll.setSize(400, 670);
        
        stage.addActor(scroll);
        stage.addActor(salir);
        stage.addActor(nombre);
        stage.addActor(lblDescripcion);
        stage.addActor(descripcion);
        stage.addActor(lblRecomendaciones);
        stage.addActor(recomendaciones);
        stage.addActor(lblBasurero);
        stage.addActor(basurero);
        stage.addActor(lblDescomposicion);
        stage.addActor(descomposicion);
    }
    

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor((float)15/255, (float)93/255, (float)145/255, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
//        render.begin(ShapeRenderer.ShapeType.Filled);
//        render.setColor(Color.WHITE);
//        render.rect(299, 97, 75, 1);
        
//        render.end();
        
        camara.update();
    }

    @Override
    public void resize(int i, int i1) {
        //
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void dispose() {
        Fuentes.titulos.font.dispose();
    }
    
}
