package Juego.Vista;

import Juego.Controlador.Controlador;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PantallaJuego implements Screen {
    
    private Controlador controlador;

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
    
    private Texture imgBotella = new Texture("Bottle.png");
    private Texture imgLata = new Texture("Can.png");
    private Texture imgHoja = new Texture("Leaf.png");
    private Texture imgCopa = new Texture("Glass.png");
    private Texture imgPalto = new Texture("Plate.png");
    private Texture imgJeringa = new Texture("Syringe.png");
    private Texture imgBolaPapel = new Texture("Paper.png");
    
    private ArrayList<Texture> texturasDesechos = new ArrayList<>();
    private LinkedList<Sprite> desechos = new LinkedList<>();
    
    private ArrayList<Sprite> desechosUp = new ArrayList<>();
    private ArrayList<Sprite> desechosMid = new ArrayList<>();
    private ArrayList<Sprite> desechosDown = new ArrayList<>();
    
    private int dificultad = 25;
    
    private double time;
    private double spawnTime = 2;
    private double lastSpawnTime = 0;
    
    private Random random = new Random();

    public PantallaJuego(Main game, Controlador cont) {
        this.game = game;
        controlador=cont;
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
        
        texturasDesechos.add(imgBotella);
        texturasDesechos.add(imgBolaPapel);
        texturasDesechos.add(imgLata);
        
        for (int i = 0; i < dificultad; i++) {
            int indice = random.nextInt(texturasDesechos.size());
            Texture textura = texturasDesechos.get(indice);
            Sprite desecho = new Sprite(textura);
            float yPos;
            switch (random.nextInt(3)) {
                case 0:
                    yPos = 200;
                    break;
                case 1:
                    yPos = 120;
                    break;
                default:
                    yPos = 40;
            }
            desecho.setPosition(1200, yPos);
            desechos.add(desecho);
        }
        
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a color
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        time += Gdx.graphics.getDeltaTime();
        lastSpawnTime += Gdx.graphics.getDeltaTime();

        // Start drawing with SpriteBatch
        batch.begin();
        
        
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
        
        XBOceano =  (XBOceano - 5) % 1540;
        OceanoBack.setPosition(XBOceano, YBOceano);
        OceanoBack.draw(batch);
        
        for (Sprite desecho : desechosUp){
            float xPos = desecho.getX();
            float yPos = desecho.getY();
            
            if (xPos < 230){
                /// termina con fail
                yPos = 500;
                xPos = 230;
            }
            yPos = yPos + (float) Math.sin(time * 3);
            xPos -= 3;
            desecho.setPosition(xPos, yPos);
            desecho.draw(batch);
        }
        
        XMOceano =  (XMOceano - 3) % 1573;
        OceanoMid.setPosition(XMOceano, YMOceano);
        OceanoMid.draw(batch);
        
        for (Sprite desecho : desechosMid){
            float xPos = desecho.getX();
            float yPos = desecho.getY();
            
            if (xPos < 230){
                /// termina con fail
                yPos = 500;
                xPos = 230;
            }
            yPos = yPos + (float) Math.sin(time * 3);
            xPos -= 3;
            desecho.setPosition(xPos, yPos);
            desecho.draw(batch);
        }
        
        XFOceano =  (XFOceano - 2) % 1512;
        OceanoFront.setPosition(XFOceano, YFOceano);
        OceanoFront.draw(batch);
        
        for (Sprite desecho : desechosDown){
            float xPos = desecho.getX();
            float yPos = desecho.getY();
            
            if (xPos < 230){
                /// termina con fail
                yPos = 500;
                xPos = 230;
            }
            yPos = yPos + (float) Math.sin(time * 3);
            xPos -= 3;
            desecho.setPosition(xPos, yPos);
            desecho.draw(batch);
        }   

        isla.setPosition(0, 0);
        isla.draw(batch);
        
        if(desechos.isEmpty()){
            // terminacon win
        }
        else{
            if (lastSpawnTime > spawnTime) {
                Sprite desecho = desechos.removeLast();
                float yPos = desecho.getY();
                
                switch (Math.round(yPos)) {
                    case 200:
                        desechosUp.add(desecho);
                        break;
                    case 120:
                        desechosMid.add(desecho);
                        break;
                    default:
                        desechosDown.add(desecho);
                }
                lastSpawnTime = 0;
            }
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
