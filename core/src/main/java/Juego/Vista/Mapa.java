package Juego.Vista;

import Juego.Controlador.Controlador;
import Juego.Modelo.EstadoNivel;
import Juego.Modelo.Nivel;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

public class Mapa implements Screen {
    
    private Controlador controlador;
    private boolean giroD=false;
    private boolean giroL=false;

    private Main game;
    
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    
    private final Texture imgMapa = new Texture( "mapa.jpg");
    private Sprite mapa;
    
    private final Texture imgPalmera = new Texture("Palmera.png");
    private Sprite palmera;
    
    private final Texture campanaEmpty = new Texture("NotificacionEmpty.png");
    private final Texture campanaFull = new Texture("NotificacionFull.png");
    private Texture imgNotificacion = campanaEmpty;
    private ImageButton notificaciones;
    private Label cantidadNotificaciones;
    
    private final Texture imgSugerencias = new Texture("RecomendacionS.png");
    private ImageButton sugerencias;
    
    
    private ImageButton RFlecha;
    private final Texture imgRflecha = new Texture("RFlecha.png");
    private final Texture imgRSflecha = new Texture("SelectedRight.png");
    
    private ImageButton LFlecha;
    private final Texture imgLFlecha = new Texture("LFlecha.png");
    private final Texture imgLSFlecha = new Texture("SelectedLeft.png");
    
    private Nivel nivel1;
    private Nivel nivel2;
    private Nivel nivel3;
    private Nivel nivel4;
    private Nivel nivel5;
    private Nivel nivel6;
    
    private HashMap<Integer, Nivel> niveles;
    
    private int height;
    private int width;

    float xMap = 0;
    float yMap = 0;
    
    public Mapa(Main game, Controlador cont) {
        this.game = game;
        controlador=cont;
        niveles = new HashMap<>();
        
        for (int i=1;i<7;i++)
            niveles.put(i, new Nivel(String.valueOf(i)));
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        font = game.font;
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        mapa = new Sprite(imgMapa);
        palmera = new Sprite(imgPalmera);
        
        String cantNotificaciones = String.valueOf((controlador.getUsuarioActual()).getCantNotificaciones());
        if(cantNotificaciones.length()>=2){
            cantNotificaciones = "+9";
        }
        setNotification(!cantNotificaciones.equals("0"));
        if(cantNotificaciones.equals("0")){
            cantNotificaciones = "";
        }
        cantidadNotificaciones = new Label(cantNotificaciones , Fuentes.bold); 
        
        //Notificaciones
        ImageButtonStyle notificacionesEstilo = new ImageButtonStyle();
        notificacionesEstilo.up = new TextureRegionDrawable(imgNotificacion);
        notificaciones = new ImageButton(notificacionesEstilo);
        notificaciones.setPosition(154, 595);
        
        notificaciones.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                   game.setScreen(new Notificaciones(game, controlador));
                   //Añadir controlador
                    
            }
        });
        
        //Sugerencias
        ImageButtonStyle sugerenciasEstilo = new ImageButtonStyle();
        sugerenciasEstilo.up = new TextureRegionDrawable(imgSugerencias);
        sugerencias = new ImageButton(sugerenciasEstilo);
        sugerencias.setPosition(85, 618);
        
        sugerencias.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                   game.setScreen(new Recomendaciones(game,controlador));
                    //Por que controlador??
            }
        });
        
        
       //ImageButtons??;
        
       
        ImageButtonStyle LEstilo = new ImageButtonStyle();
        LEstilo.up = new TextureRegionDrawable(imgLFlecha);
        LEstilo.over = new TextureRegionDrawable(imgLSFlecha);
        LFlecha = new ImageButton(LEstilo);
        LFlecha.setPosition(50, 335);
        
        LFlecha.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    giroL=true;
                    giroD = false;
                    
            }
        });
        
        ImageButtonStyle REstilo = new ImageButtonStyle();
        REstilo.up = new TextureRegionDrawable(imgRflecha);
        REstilo.over = new TextureRegionDrawable(imgRSflecha);
        RFlecha = new ImageButton(REstilo);
        RFlecha.setPosition(991, 335);
        
        RFlecha.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    giroD=true;
                    giroL=false;
            }
        });
        
        
        
//        nivel1 = new Nivel("1");
//        
//        nivel2 = new Nivel("2");
////        niveles.put(2, nivel2);
//        nivel3 = new Nivel("3");
////        niveles.put(3, nivel3);
//        nivel4 = new Nivel("4");
////        niveles.put(4, nivel4);
//        nivel5 = new Nivel("5");
////        niveles.put(5, nivel5);
//        nivel6 = new Nivel("6");
//        niveles.put(6, nivel6);
        
        height = game.height;
        width = game.width;
        
        stage.addActor(RFlecha);
        stage.addActor(LFlecha);
        stage.addActor(notificaciones);
        stage.addActor(cantidadNotificaciones);
        stage.addActor(sugerencias);

    }

    @Override
    public void render(float delta) {
        // Clear the screen with a color
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        
        if (giroD) {
            if (xMap > -1100){
                xMap -= 10;
            }
            if (xMap==-1100)
                giroD=false;
        }
        if (giroL) {
            if (xMap<0)
                xMap +=10;
            if (xMap==0)
                giroL=false;
        }
        mapa.setPosition(xMap, yMap);
        mapa.draw(batch);
        
        palmera.setPosition(-203, 400);
        palmera.draw(batch);
        
        
        cantidadNotificaciones.setPosition(notificaciones.getX()+27 -(4*(cantidadNotificaciones.getText().length()-1)) , notificaciones.getY()+32);    
        
        ArrayList<Integer> xS = new ArrayList<>(Arrays.asList(100, 510, 870, 1172, 1550, 1930));
        ArrayList<Integer> yS = new ArrayList<>(Arrays.asList(40, 50, 30, 72, 25, 45));
        
        int nivelActual=controlador.getUsuarioActual().getNivel();
        
        for (int i =1; i<=niveles.size();i++) {
            Nivel nivel = niveles.get(i);
            nivel.setPosition(mapa.getX() +xS.get(i-1), yS.get(i-1));
            if (i<nivelActual)
                nivel.setEstado(EstadoNivel.COMPLETADO);
            else if (i==nivelActual)
                nivel.setEstado(EstadoNivel.ACTUAL);
            else 
                nivel.setEstado(EstadoNivel.BLOQUEADO);
            nivel.draw(batch);
        }
        
//        nivel1.setPosition(mapa.getX() + 100, 40);
//        nivel1.setEstado(EstadoNivel.COMPLETADO);
//        nivel1.draw(batch);
//        
//        nivel2.setPosition(mapa.getX() + 510, 50);
//        nivel2.setEstado(EstadoNivel.COMPLETADO);
//        nivel2.draw(batch);
//        
//        nivel3.setPosition(mapa.getX() + 870, 30);
//        nivel3.setEstado(EstadoNivel.ACTUAL);
//        nivel3.draw(batch);
//        
//        nivel4.setPosition(mapa.getX() + 1172, 72);
//        nivel4.setEstado(EstadoNivel.BLOQUEADO);
//        nivel4.draw(batch);
//        
//        nivel5.setPosition(mapa.getX() + 1550, 25);
//        nivel5.setEstado(EstadoNivel.BLOQUEADO);
//        nivel5.draw(batch);
//        
//        nivel6.setPosition(mapa.getX() + 1930, 45);
//        nivel6.setEstado(EstadoNivel.BLOQUEADO);
//        nivel6.draw(batch);
        
        
        batch.end();
        
        stage.act(Gdx.graphics.getDeltaTime());
        
        stage.draw();
        
        // Handle screen transitions or input events (optional)
        if (Gdx.input.isTouched()) {
            // Switch to another screen (if desired)
            // game.setScreen(new AnotherScreen(game));
            
         camara.update();
        }
    }
    
    
    public void setNotification(boolean tieneNotificacion){
        if (tieneNotificacion){
            imgNotificacion = campanaFull;
        }
        else{
            imgNotificacion = campanaEmpty;
        }
    }
    
    public Nivel getNivel(int nivel){
        return niveles.get(nivel);
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
        for (int i=1;i<7;i++) {
            (niveles.get(i)).dispose();
        }
//        nivel1.dispose();
//        nivel2.dispose();
//        nivel3.dispose();
        imgMapa.dispose();
        imgPalmera.dispose();
        imgSugerencias.dispose();
        imgNotificacion.dispose();
        imgLFlecha.dispose();
        imgRflecha.dispose();
        imgRSflecha.dispose();
        imgLSFlecha.dispose();
    }
}
