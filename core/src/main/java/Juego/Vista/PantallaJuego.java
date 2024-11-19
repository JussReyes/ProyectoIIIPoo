package Juego.Vista;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class PantallaJuego implements Screen {

    private Main game;
    
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    
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
    
    private final Texture imgPausa = new Texture ("pause.png");
    private Sprite pausa;
    
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
    
    private ArrayList<Sprite> basureros = new ArrayList<>();
    
    private float widthArriba;
    private float yArriba;
    
    private float widthAbajo;
    private float yAbajo;
    
    public PantallaJuego(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        font = game.font;
        
        OceanoBack = new Sprite(imgOceanoBack);
        OceanoMid = new Sprite(imgOceanoMid);
        OceanoFront = new Sprite(imgOceanoFront);
        
        isla = new Sprite(imgIsla);
        
        papel = new Sprite(imgPapel);
        basureros.add(papel);
        
        vidrio = new Sprite(imgVidrio);
        basureros.add(vidrio);
        
        plastico = new Sprite(imgPlastico);
        basureros.add(plastico);
        
        organico = new Sprite(imgOrganico);
        basureros.add(organico);
        
        biologico = new Sprite(imgBiologico);
        basureros.add(biologico);
        
        metal = new Sprite(imgMetal);
        basureros.add(metal);
        
        general = new Sprite(imgGeneral);
        basureros.add(general);
        
        pausa = new Sprite(imgPausa);
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a color
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        
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
        
        pausa.setPosition(110, 573);
        pausa.draw(batch);
        
        if (basureros.size() < 4) {
            widthArriba = 121 * basureros.size() - 10;
            yArriba = 412;
        }
        else{
            widthArriba = 353;
            yArriba = 500;
            
            widthAbajo = 121  * (basureros.size() - 3) - 10;
            yAbajo = 325;
        }
        
        for (int i = 0; i < basureros.size(); i++) {
            Sprite basurero = basureros.get(i);
            
            if (i < 3){
                basurero.setPosition(444 + 121 * i  + (355 -widthArriba) / 2, yArriba);
            }
            else{
                basurero.setPosition(444 + 121*(i -3) + (355 - widthAbajo) / 2, yAbajo);
            }
            basurero.draw(batch);
        }
        
        batch.end();
        
        // Handle screen transitions or input events (optional)
        if (Gdx.input.isTouched()) {
            // Switch to another screen (if desired)
            // game.setScreen(new AnotherScreen(game));
            
         camara.update();
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
        imgOceanoBack.dispose();
        imgOceanoMid.dispose();
        imgOceanoFront.dispose();
        imgIsla.dispose();
        imgPausa.dispose();
    }
}
