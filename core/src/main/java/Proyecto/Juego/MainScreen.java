package Proyecto.Juego;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScreen implements Screen {

    private Main game;
    private SpriteBatch batch;
    private BitmapFont font;

    public MainScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = game.batch;
        font = game.font;
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        
        // Draw some simple text in the center of the screen
        font.setColor(Color.BLUE);
        font.draw(batch, "Welcome to My Game!", Gdx.graphics.getWidth() / 2 - 20 / 2, 
                  Gdx.graphics.getHeight() / 2);
        
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
