/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Vista;

import Juego.Controlador.Controlador;
import Juego.Modelo.Basurero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class PantallaExplicacion implements Screen {
    
    private int nivel;
    private ArrayList<Basurero> inGameBasureros = new ArrayList<>();
    private int indiceMensaje = 0;
    
    private boolean basuraSeleccionada;
    
    private Controlador controlador;

    private Main game;
    private double time;
    private Stage stage;
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    
    private float widthArriba;
    private float yArriba;
    
    private float widthAbajo;
    private float yAbajo;
    private Label mensaje;
    
    private final Texture imgOceanoBack = new Texture( "OceanBack.png");
    private Sprite OceanoBack;
    
    float XBOceano = 0;
    float YBOceano = -70;
    
    private final Texture imgOceanoMid = new Texture( "OceanMiddle.png");
    private Sprite OceanoMid;
    
    float XMOceano = 0;
    float YMOceano = -55;
    
    private final Texture imgOceanoFront = new Texture( "OceanFront.png");
    private Sprite OceanoFront;
    
    float XFOceano = 0;
    float YFOceano = -20;
    
    private final Texture imgIsla = new Texture ("Island.png");
    private Sprite isla;
    
    private final Texture imgPlastico = new Texture("PlasticCan.png");
    private Sprite plastico;
    
    private final Texture imgVidrio = new Texture("GlassCan.png");
    private Sprite vidrio;
    
    private final Texture imgMetal = new Texture("MetalCan.png");
    private Sprite metal;
    
    private final Texture imgOrganico = new Texture("OrganicCan.png");
    private Sprite organico;
    
    private final Texture imgBiologico = new Texture("BiologicCan.png");
    private Sprite biologico;
    
    private final Texture imgPapel = new Texture("PaperCan.png");
    private Sprite papel;
    
    private final Texture imgGeneral = new Texture("GeneralCan.png");
    private Sprite general;
    
    private final Texture imgTortuga = new Texture ("Tortugalo.png");
    private Sprite tortuga;
    
    private ArrayList<Sprite> basureros = new ArrayList<>();

    
    public PantallaExplicacion(Main game, Controlador cont, int nivel) {
        this.nivel = nivel;
        this.game = game;
        controlador=cont;
    }
    
    public String formatearTexto(String texto){
        
        if (texto.length() <= 28) { 
            return texto;
        }  
        int ind = texto.lastIndexOf(' ', 28); 
        if (ind == -1) { 
            return texto;
        }
        String sub1 = texto.substring(0, ind).trim(); 
        String sub2 = texto.substring(ind + 1).trim(); 
        if (sub2.length() >= 30) { 
            sub2 = formatearTexto(sub2); 
        } 
        return sub1 + "\n" + sub2;
    }
   
    @Override
    public void show() {
        
        camara = game.camara;
        batch = game.batch;
        font = game.font;
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        OceanoBack = new Sprite(imgOceanoBack);
        OceanoMid = new Sprite(imgOceanoMid);
        OceanoFront = new Sprite(imgOceanoFront);
        
        isla = new Sprite(imgIsla);
        
        tortuga = new Sprite(imgTortuga);
        
        mensaje = new Label("Ayúdame a deshacerme de\n estos desechos", Fuentes.error); 
        mensaje.setPosition(353, 310);
        
        general = new Sprite(imgGeneral);
        basureros.add(general);
        
        papel = new Sprite(imgPapel);
        basureros.add(papel);
      
        if(nivel>1){
            vidrio = new Sprite(imgVidrio);
            basureros.add(vidrio);
        }
        
        if(nivel>2){
            organico = new Sprite(imgOrganico);
            basureros.add(organico);
        }
         
        if(nivel>3){
            plastico = new Sprite(imgPlastico);
            basureros.add(plastico);
        }
        
        if(nivel>4){
            biologico = new Sprite(imgBiologico);
            basureros.add(biologico);
        }

        if(nivel>5){
            metal = new Sprite(imgMetal);
            basureros.add(metal);
        }
        
        widthArriba = 121 * basureros.size() - 10;
        yArriba = 485;

        for (int i = 0; i < basureros.size(); i++) {
            Sprite basurero = basureros.get(i);

            basurero.setPosition(444 + 121 * i  + (355 -widthArriba) / 2, yArriba);          
        }

        stage.addActor(mensaje);
    }

    @Override
    public void render(float f) {
        
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        time += Gdx.graphics.getDeltaTime();
            
        batch.begin();
        
        for (int i = 0; i < basureros.size(); i++) {
            Sprite basurero = basureros.get(i);
            basurero.draw(batch);
        }

        XBOceano =  (XBOceano - 5) % 1540;
        OceanoBack.setPosition(XBOceano, YBOceano);
        OceanoBack.draw(batch);

        XMOceano =  (XMOceano - 3) % 1573;
        OceanoMid.setPosition(XMOceano, YMOceano);
        OceanoMid.draw(batch);

        XFOceano =  (XFOceano - 2) % 1512;
        OceanoFront.setPosition(XFOceano, YFOceano);
        OceanoFront.draw(batch);

        isla.setPosition(0, 0);
        isla.draw(batch);
        
        tortuga.setPosition(-16,10);
        tortuga.draw(batch);
        
       
        batch.end();
        
        stage.draw();
        
        String txtTortuga = "Tortugaloo.txt";
        String[] mensajesTortuga = controlador.procesarTXTortugalo(txtTortuga, nivel);
        try{
        if (Gdx.input.justTouched()) {
            System.out.println("Nivel: " + nivel);
            System.out.println("Indice: " + indiceMensaje);
            String textoMensaje = mensajesTortuga[indiceMensaje];
            indiceMensaje++;

            if(textoMensaje==null || "".equals(textoMensaje.trim())){
                System.out.println("Mensaje: "+textoMensaje);
                game.setScreen(new PantallaJuego(game, controlador,nivel));       
            }
            else{
                System.out.println("Mensaje: "+textoMensaje);
                textoMensaje = formatearTexto(textoMensaje);
                System.out.println("Formateado: "+textoMensaje);
                mensaje.setText(textoMensaje);
            }          
            camara.update();
            }
        }
        catch(IndexOutOfBoundsException e){

            game.setScreen(new PantallaJuego(game, controlador,nivel));  
        }
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
        imgOceanoBack.dispose();
        imgOceanoMid.dispose();
        imgOceanoFront.dispose();
        imgIsla.dispose();
    }
    
}
