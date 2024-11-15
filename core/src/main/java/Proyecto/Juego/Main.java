package Proyecto.Juego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Main extends Game {
    SpriteBatch batch;
    BitmapFont font;
    int width, height;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();  // Use default font
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        this.setScreen(new Mapa(this));  // Set the first screen
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
