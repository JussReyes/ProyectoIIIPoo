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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
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
    private Label volver;
    private ShapeRenderer render;
    private VerticalGroup matriz;
    private ScrollPane scroll;
    
    public infoTipo(Main game, Controlador cont, String tipo) {
        this.game=game;
        controlador=cont;
        this.tipo=tipo;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        
        render = new ShapeRenderer();
        
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));

        volver = new Label("regresar", Fuentes.normales); 
        volver.setPosition(300, 100);
        volver.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Aquí debo de hacer un nuevo tipo de ventana del que creó Justin                    
                //game.setScreen(new Mapa(game, controlador));
            }
        });
        
        titulo = new Label("NOTIFICACIONES", Fuentes.titulos);
        titulo.setPosition(295, 600);
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));
        
        ArrayList<Basura> basuras=controlador.getBasuras();
        
        matriz = new VerticalGroup();
        matriz.space(10);
        for (int i=0;i<15;i++) {
            //Basura basura=basuras.get(i);
            HorizontalGroup fila = new HorizontalGroup();
            fila.space(10);
            
            Texture newImagen = new Texture(Gdx.files.internal("assets\\aHuevo.png"));
            ImageButton imagen= new ImageButton(new TextureRegionDrawable(new TextureRegion(newImagen)));
            imagen.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        System.out.println("ya");
                }   
            });
            
//            Label boton1 = new Label("Huevo "+i,Fuentes.normales);
            ImageTextButton boton1 = new ImageTextButton("Huevo "+i, skin, "noticia");
            TextArea boton2 = new TextArea("texto de la descripción.. sadlkcajlcadsl daldksc jaodsc askdclskdcj ldjcslak jdclak jsclcaj lajd",skin);
//            ImageTextButton boton2 = new ImageTextButton("texto de la descripción.. sadlkcajlcadsl daldksc jaodsc askdclskdcj ldjcslak jdclak jsclcaj lajd", skin);
            TextArea boton3 = new TextArea("Recomendaciones: dcj ldjcslak jdclak jsclcaj la",skin);
//            ImageTextButton boton3 = new ImageTextButton("Recomendaciones: dcj ldjcslak jdclak jsclcaj la", skin);
//            ImageTextButton boton4 = new ImageTextButton("Recomendaciones", skin);

            fila.addActor(imagen);
            fila.addActor(boton1);
            fila.addActor(boton2);
            fila.addActor(boton3);
            
            Table filaConBorde = new Table(skin);
            filaConBorde.setBackground("borde_azul");
            filaConBorde.pad(10);
            filaConBorde.add(fila);
            
            matriz.addActor(filaConBorde);
        }
        scroll = new ScrollPane(matriz, skin, "notificaciones");
        scroll.setPosition(50, 50);
        scroll.setSize(1000, 660);
        
        stage.addActor(scroll);
    }
    

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
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
        batch.dispose();
        Fuentes.titulos.font.dispose();
    }
    
}
