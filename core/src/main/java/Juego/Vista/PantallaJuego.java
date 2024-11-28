package Juego.Vista;

import Juego.Controlador.Controlador;
import Juego.Modelo.Basura;
import Juego.Modelo.Basurero;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PantallaJuego implements Screen, Juego {
    
    private boolean pausado = false;
    boolean spriteArrastrado = false;
    Desecho spriteSeleccionado = null;
    
    private int nivel;
    
    private float mouseX;
    private float mouseY;
    private float spriteX; 
    private float spriteY;
    private boolean basuraSeleccionada;
    
    private Controlador controlador;

    private Main game;
    
    private Stage stage;
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
    private final Texture imgPlay = new Texture ("btnPlay.png");
    private ImageButton pausa;
    private ImageButton play;
    
    private Rectangle areaBasureros = new Rectangle(385, 330, 470, 300);
    
    private float widthArriba;
    private float yArriba;
    
    private float widthAbajo;
    private float yAbajo;
    
    
    private ArrayList<Desecho> tiposDesechos = new ArrayList<>();
    private LinkedList<Desecho> desechos = new LinkedList<>();
    private ArrayList<Basurero> inGameBasureros = new ArrayList<>();
    
    private ArrayList<Desecho> desechosUp = new ArrayList<>();
    private ArrayList<Desecho> desechosMid = new ArrayList<>();
    private ArrayList<Desecho> desechosDown = new ArrayList<>();
    private ArrayList<Desecho> desechosLanzados = new ArrayList<>();
    
    private int dificultad = 25;
    
    private double time;
    private double spawnTime = 2;
    private double lastSpawnTime = 0;
    
    private Random random = new Random();

    public PantallaJuego(Main game, Controlador cont, int nivel) {
        this.nivel = nivel % 7;
        this.game = game;
        controlador=cont;
    }

    @Override
    public void show() {
        basuraSeleccionada = false;
        camara = game.camara;
        batch = game.batch;
        font = game.font;
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        OceanoBack = new Sprite(imgOceanoBack);
        OceanoMid = new Sprite(imgOceanoMid);
        OceanoFront = new Sprite(imgOceanoFront);
        
        isla = new Sprite(imgIsla);
       
        
        for (int i = 0; i < nivel + 1; i++) 
            inGameBasureros.add(Juego.basureros.get(i));
        
        if(!pausado){
            ImageButton.ImageButtonStyle notificacionesEstilo = new ImageButton.ImageButtonStyle();
            notificacionesEstilo.up = new TextureRegionDrawable(imgPausa);
            pausa = new ImageButton(notificacionesEstilo);
            pausa.setPosition(110, 573);
            pausa.addListener(new ChangeListener(){
            
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
               pausar();
            }});
        
        tiposDesechos.add(botella);
        tiposDesechos.add(bolaPapel);
        tiposDesechos.add(lata);
        tiposDesechos.add(plato);
        tiposDesechos.add(hoja);
        tiposDesechos.add(jeringa);
        tiposDesechos.add(copa);
        
        for (int i = 0; i < dificultad; i++) {
            int indice = random.nextInt(tiposDesechos.size());
            Desecho desecho = new Desecho(tiposDesechos.get(indice));
            
            boolean esta = false;
            for (Basurero basurero : inGameBasureros) 
                if (basurero.getTipo().equals(desecho.getTipoBasurero())) 
                    esta = true;
            
            if (!esta)
                desecho.setTipoBasurero("general");
            
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
        
        if (inGameBasureros.size() < 4) {
            widthArriba = 121 * inGameBasureros.size() - 10;
            yArriba = 412;
        }
        else{
            widthArriba = 353;
            yArriba = 500;
            
            widthAbajo = 121  * (inGameBasureros.size() - 3) - 10;
            yAbajo = 325;
        }
        for (int i = 0; i < inGameBasureros.size(); i++) {
            Sprite basurero = inGameBasureros.get(i);
            
            if (i < 3){
                basurero.setPosition(444 + 121 * i  + (355 -widthArriba) / 2, yArriba);
            }
            else{
                basurero.setPosition(444 + 121*(i -3) + (355 - widthAbajo) / 2, yAbajo);
            }
        }
        
    }
         stage.addActor(pausa);
    }
    
    private void pausar(){
        pausado = !pausado;
        stage.getActors().removeValue(pausa, true);
        int x;
        int y;
        ImageButton.ImageButtonStyle estiloBoton = new ImageButton.ImageButtonStyle();
        if (!pausado) {
            estiloBoton.up = new TextureRegionDrawable(new TextureRegion(imgPausa));
            x =110;
            y = 573;
        } else {
            estiloBoton.up = new TextureRegionDrawable(new TextureRegion(imgPlay));
            x =290;
            y = 100;
        }

        pausa = new ImageButton(estiloBoton);
        pausa.setPosition(x, y);

        pausa.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pausar();
            }
        });

        stage.addActor(pausa);
    }
    
    

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!pausado){
            time += Gdx.graphics.getDeltaTime();
            lastSpawnTime += Gdx.graphics.getDeltaTime();     
          
            // Start drawing with SpriteBatch
            batch.begin();
            
            
            for (int i = 0; i < inGameBasureros.size(); i++) {
                Sprite basurero = inGameBasureros.get(i);
                basurero.draw(batch);
            }


            XBOceano =  (XBOceano - 5) % 1540;
            OceanoBack.setPosition(XBOceano, YBOceano);
            OceanoBack.draw(batch);

            for (Sprite desecho : desechosUp){
                float xPos = desecho.getX();
                float yPos = desecho.getY();

                if (xPos < 230){
                    dispose();
                    game.setScreen(new GameOver(game,controlador, false));
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
                    dispose();
                    game.setScreen(new GameOver(game,controlador, false));
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
                   dispose();
                   game.setScreen(new GameOver(game,controlador, false));
                }
                
                yPos = yPos + (float) Math.sin(time * 3);
                xPos -= 3;
                desecho.setPosition(xPos, yPos);
                desecho.draw(batch);
            }   

            isla.setPosition(0, 0);
            isla.draw(batch);

            if(desechosLanzados.isEmpty() && desechos.isEmpty()){
                controlador.subirNivelUsuarioActual();
                game.setScreen(new GameOver(game, controlador, true));
            }
            else{
                if (lastSpawnTime > spawnTime && !desechos.isEmpty()) {
                    Desecho desecho = desechos.removeLast();
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
                    desechosLanzados.add(desecho);
                    lastSpawnTime = 0;
                }
            }

            batch.end();

            stage.act(Gdx.graphics.getDeltaTime());

            stage.draw();


            // Handle screen transitions or input events (optional)
            if (Gdx.input.isTouched()) {
                // Switch to another screen (if desired)
                // game.setScreen(new AnotherScreen(game));
             camara.update();
            } 
            if(Gdx.input.justTouched()&&!spriteArrastrado){
                mouseX = Gdx.input.getX();
                mouseY = Gdx.graphics.getHeight()- Gdx.input.getY();

                for (Desecho sprite: desechosLanzados){
                    spriteX = sprite.getX(); 
                    spriteY = sprite.getY();

                    float anchoSprite = sprite.getWidth(); 
                    float alturaSprite = sprite.getHeight(); 
                    if (mouseX >= spriteX && mouseX <= spriteX + anchoSprite && mouseY >= spriteY && mouseY <= spriteY + alturaSprite) { 
                        spriteArrastrado = true;
                        spriteSeleccionado = sprite;
                        break;

                    }
                }

            }
            if (Gdx.input.isTouched() && spriteArrastrado && spriteSeleccionado != null) {
                spriteSeleccionado.setCenter(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            }

            if (!Gdx.input.isTouched()&&spriteArrastrado) {
                if (areaBasureros.contains(spriteSeleccionado.getBoundingRectangle())) {
                    
                    for (Basurero basurero: inGameBasureros) {
                        if (basurero.getBoundingRectangle().overlaps(spriteSeleccionado.getBoundingRectangle()) 
                                && basurero.getTipo().equals(spriteSeleccionado.getTipoBasurero())) {
                            
                            desechosUp.remove(spriteSeleccionado);
                            desechosMid.remove(spriteSeleccionado);
                            desechosDown.remove(spriteSeleccionado);
                            desechosLanzados.remove(spriteSeleccionado);
                            spriteArrastrado = false;
                        }
                    }
                }
            }
            if(!Gdx.input.isTouched() && spriteSeleccionado != null){

                spriteSeleccionado.setCenter(spriteX, spriteY);
                spriteArrastrado = false;
                spriteSeleccionado = null;
            }
            stage.draw();

        }
        stage.draw();
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
        font.dispose();
        imgOceanoBack.dispose();
        imgOceanoMid.dispose();
        imgOceanoFront.dispose();
        imgIsla.dispose();
        imgPausa.dispose();
    }
}
