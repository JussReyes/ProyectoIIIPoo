package Juego.Vista;

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
import java.util.HashMap;
import java.util.Hashtable;

public class Mapa implements Screen {

    private Main game;
    
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    
    private final Texture imgMapa = new Texture( "mapa.jpg");
    private Sprite mapa;
    
    private final Texture imgPalmera = new Texture("Palmera.png");
    private Sprite palmera;
    
    private final Texture campanaEmpty = new Texture("NotificacionEmpty.png");
    private final Texture campanaFull = new Texture("NotificacionFull.png");
    private Texture imgNotificacion = campanaEmpty;
    private Sprite notificaciones;
    
    private final Texture imgSugerencias = new Texture("RecomendacionS.png");
    private Sprite sugerencias;
    
    
    private final Texture imgRflecha = new Texture("RFlecha.png");
    private Sprite RFlecha;
    
    private final Texture imgLFlecha = new Texture("LFlecha.png");
    private Sprite LFlecha;
    
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
    
    public Mapa(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        font = game.font;
        
        mapa = new Sprite(imgMapa);
        palmera = new Sprite(imgPalmera);
        notificaciones = new  Sprite(imgNotificacion);
        sugerencias = new Sprite(imgSugerencias);
       //ImageButtons??;
        
       
        
        LFlecha = new Sprite(imgLFlecha);
        RFlecha = new Sprite(imgRflecha);
        
        niveles = new HashMap<>();
        
        nivel1 = new Nivel("1");
        niveles.put(1, nivel1);
        nivel2 = new Nivel("2");
        niveles.put(2, nivel2);
        nivel3 = new Nivel("3");
        niveles.put(3, nivel3);
        nivel4 = new Nivel("4");
        niveles.put(4, nivel4);
        nivel5 = new Nivel("5");
        niveles.put(5, nivel5);
        nivel6 = new Nivel("6");
        niveles.put(6, nivel6);
        
        height = game.height;
        width = game.width;
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a color
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        
        if (xMap > -1105){
            xMap -= 1;
        }
        mapa.setPosition(xMap, yMap);
        mapa.draw(batch);
        
        palmera.setPosition(-203, 400);
        palmera.draw(batch);
        
        notificaciones.setPosition(154, 595);
        notificaciones.draw(batch);
        
        sugerencias.setPosition(85, 618);
        sugerencias.draw(batch);
        
        RFlecha.setPosition(991, 335);
        RFlecha.draw(batch);
        
        LFlecha.setPosition(50, 335);
        LFlecha.draw(batch);
        
        nivel1.setPosition(mapa.getX() + 100, 40);
        nivel1.setEstado(EstadoNivel.COMPLETADO);
        nivel1.draw(batch);
        
        nivel2.setPosition(mapa.getX() + 510, 50);
        nivel2.setEstado(EstadoNivel.COMPLETADO);
        nivel2.draw(batch);
        
        nivel3.setPosition(mapa.getX() + 870, 30);
        nivel3.setEstado(EstadoNivel.ACTUAL);
        nivel3.draw(batch);
        
        nivel4.setPosition(mapa.getX() + 1172, 72);
        nivel4.setEstado(EstadoNivel.BLOQUEADO);
        nivel4.draw(batch);
        
        nivel5.setPosition(mapa.getX() + 1550, 25);
        nivel5.setEstado(EstadoNivel.BLOQUEADO);
        nivel5.draw(batch);
        
        nivel6.setPosition(mapa.getX() + 1930, 45);
        nivel6.setEstado(EstadoNivel.BLOQUEADO);
        nivel6.draw(batch);
        
        
        batch.end();
        
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
        nivel1.dispose();
        nivel2.dispose();
        nivel3.dispose();
        imgMapa.dispose();
        imgPalmera.dispose();
        imgSugerencias.dispose();
        imgNotificacion.dispose();
        imgLFlecha.dispose();
        imgRflecha.dispose();
    }
}
