package Proyecto.Juego;

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
    private Texture imgNotificacion = new Texture("Campana.png");
    private Sprite notificaciones;
    private Sprite sugerencias;
    private int height;
    private int width;

    public Mapa(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = game.batch;
        font = game.font;
        mapa = new Texture( "mapa.png");
        palmera = new Sprite(imgPalmera);
        notificaciones = new  Sprite(imgNotificacion);
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
        
        batch.draw(mapa, 0, 0, width, height);
        palmera.setPosition(-203, 400);
        palmera.draw(batch);
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
