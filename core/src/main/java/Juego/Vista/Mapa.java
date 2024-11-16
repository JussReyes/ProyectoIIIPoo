package Juego.Vista;

import Juego.Modelo.EstadoNivel;
import Juego.Modelo.Nivel;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mapa implements Screen {

    private Main game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture mapa;
    
    private Texture imgPalmera = new Texture("Palmera.png");
    private Sprite palmera;
    
    private Texture imgNotificacion = new Texture("NotificacionEmpty.png");
    private Sprite notificaciones;
    
    private Texture imgSugerencias = new Texture("RecomendacionS.png");
    private Sprite sugerencias;
    
    private Nivel nivel1;
    
    private int height;
    private int width;

    public Mapa(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = game.batch;
        font = game.font;
        mapa = new Texture( "mapa.jpg");
        
        palmera = new Sprite(imgPalmera);
        notificaciones = new  Sprite(imgNotificacion);
        sugerencias = new Sprite(imgSugerencias);
        
        nivel1 = new Nivel("1");
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
        
        batch.draw(mapa, 0, 0);
        
        palmera.setPosition(-203, 400);
        palmera.draw(batch);
        
        notificaciones.setPosition(154, 595);
        notificaciones.draw(batch);
        
        sugerencias.setPosition(85, 618);
        sugerencias.draw(batch);
        
        nivel1.setPosition(106, 43);
        nivel1.setEstado(EstadoNivel.COMPLETADO);
        nivel1.draw(batch);
        
        batch.end();
        
        // Handle screen transitions or input events (optional)
        if (Gdx.input.isTouched()) {
            // Switch to another screen (if desired)
            // game.setScreen(new AnotherScreen(game));
        }
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
