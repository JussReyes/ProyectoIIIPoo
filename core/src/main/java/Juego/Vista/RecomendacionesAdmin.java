package Juego.Vista;

import Juego.Controlador.Controlador;
import Juego.Controlador.SelectorDeImagen;
import Juego.Modelo.Basura;
import Juego.Modelo.NoReciclables.General;
import Juego.Modelo.NoReciclables.Organicos;
import Juego.Modelo.Reciclables.Metal;
import Juego.Modelo.Reciclables.Papel;
import Juego.Modelo.Reciclables.Plastico;
import Juego.Modelo.Reciclables.Vidrio;
import Juego.Modelo.Sugerencia;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecomendacionesAdmin implements Screen, Fuentes {
    
    private Controlador controlador;
    private String ruta="";
    private ArrayList<Sugerencia> recos;
    private Sugerencia recoActual;

    private Main game;
    
    private Camera camara;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont fontTitle;
    
    private Stage stage;
    private Skin skin;
    private SelectBox<String> selectBox;
    private TextField nombre;
    private TextField descomposición;
    
    private TextField descripcion;
    private TextField recomendaciones;
    private ImageButton imagen;
    private ImageButton aceptar;
    
    private ImageButton salir;
    private final Texture upCancelar = new Texture(Gdx.files.internal("CancelBtn.png"));
    private final Texture overCancelar = new Texture(Gdx.files.internal("CancelOver.png"));
    
    private final Texture upAceptar = new Texture(Gdx.files.internal("AcceptBtn.png"));
    private final Texture overAceptar = new Texture(Gdx.files.internal("AcceptOver.png"));

    
    private Texture pintura;
    private Texture fondo;

    public RecomendacionesAdmin(Main game, Controlador cont) {
        this.game = game;
        controlador=cont;
    }




    private Texture image;

    private void llenarDatos(){
        stage.getActors().removeValue(imagen, true);
        recos=controlador.getSugerencias();
        if (!recos.isEmpty()&&recos!=null) {
            System.out.println("No estaba vacío");
            recoActual=recos.getLast();
            ruta=recoActual.getImagen();
            nombre.setText(recoActual.getNombre());
            selectBox.setSelected(recoActual.getBasurero());
            descripcion.setText(recoActual.getDescripcion());
            descomposición.setText(recoActual.getDescomposicion());
            recomendaciones.setText(recoActual.getRecomendaciones());
        }
        else {
            System.out.println("Estaba vacío");
            if (recos==null) {
                System.out.println("También era nulo");
            }
            ruta="ImageButton.png";
            nombre.setText("");
            selectBox.setSelected("");
            descripcion.setText("");
            descomposición.setText("");
            recomendaciones.setText("");
        }
        image = new Texture(Gdx.files.internal(ruta));
        imagen = new ImageButton(new TextureRegionDrawable(new TextureRegion(image)));
        imagen.setSize(153, 153);
        imagen.setPosition(380, 385);
        imagen.addListener(new ChangeListener(){
            
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                SelectorDeImagen fileChooserExample = new SelectorDeImagen();
                String rutas = fileChooserExample.copiarImagenAlRepositorio("assets");
                
                if (rutas != null) {
                    String rutaRelativa = rutas.substring(rutas.lastIndexOf("##")+2);
                    String rutaImagen =rutas.substring(0, rutas.lastIndexOf("##"));
                    System.out.println("Ruta relativa en el proyecto: " + rutaRelativa);
                    Texture newImagen = new Texture(Gdx.files.internal(rutaImagen));
                    System.out.println(newImagen.getHeight());
                    imagen.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(newImagen));
                    ruta=rutaRelativa;
                }

            }
            
        });
        stage.addActor(imagen);
    }
    
    @Override
    public void show() {
        camara = game.camara;
        batch = game.batch;
        font = game.font;
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("CustumUI/UIRec.json"));
        
        
        pintura =  new Texture(Gdx.files.internal("BigPaint.png"));
        fondo = new Texture (Gdx.files.internal("RecomendacionesBG.png"));
        

        nombre = new TextField("", skin);
        nombre.setMessageText("Nombre");
        nombre.setPosition(91, 514);
        nombre.setSize(215, 30);

        selectBox = new SelectBox<>(skin);
        selectBox.setItems("Plástico", "Metal", "Papel", "Biológico", "Orgánico", "Vidrio", "General");
        selectBox.setSize(215, 30);
        selectBox.setAlignment(1);
        selectBox.setPosition(91, 442);

        descomposición = new TextField("", skin);
        descomposición.setMessageText("Tiempo en días");
        descomposición.setPosition(91, 370);
        descomposición.setSize(215, 30);
        
        descripcion = new TextArea("", skin);
        descripcion.setMessageText("\n                Describe el tipo de desecho");
        descripcion.setPosition(91, 215);
        descripcion.setSize(460, 87);
        
        
        recomendaciones = new TextArea("", skin);
        recomendaciones.setMessageText("\n     Recomendaciones para el tratamiento");
        recomendaciones.setPosition(91, 59);
        recomendaciones.setSize(460, 87);
        
        llenarDatos();
        
        
        ImageButton.ImageButtonStyle aceptarEstilo = new ImageButton.ImageButtonStyle();
        aceptarEstilo.up = new TextureRegionDrawable (upAceptar);
        aceptarEstilo.over = new TextureRegionDrawable(overAceptar);
        aceptar = new ImageButton(aceptarEstilo);
        aceptar.setPosition(900, 86);
        aceptar.setSize(50, 50);
        aceptar.addListener(new ChangeListener(){
            
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
                String nom = nombre.getText();
                String desc = descripcion.getText();
                String recom = recomendaciones.getText();
                int dias;
                
                
                try {
                    if (descomposición.getText().isBlank())
                        throw new IllegalArgumentException("Ingrese el número de días");
                    try{
                        dias = Integer.parseInt(descomposición.getText());
                    }
                    catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Ingrese un número de días para el tiempo de descomposición");
                    }

                    if (nom.isBlank())
                     throw new IllegalArgumentException("        "+"Ingrese el nombre");
                    if (desc.isBlank())
                        throw new IllegalArgumentException("      Ingrese la descripción");
                    if (ruta.isBlank())
                        throw new IllegalArgumentException("      Ingrese la imagen");


                    String basu = selectBox.getSelected();
                    Basura reco;
                    switch (basu) {
                        case ("Papel"):{
                            reco= new Papel(nom, desc, ruta, recom);
                            break;
                        }
                        case("Vidrio"):{
                            reco= new Vidrio(nom, desc, ruta, recom);
                            break;
                        }
                        case("Metal"):{
                            reco= new Metal(nom, desc, ruta, recom);
                            break;
                        }
                        case("Plástico"):{
                            reco= new Plastico(nom, desc, ruta, recom);
                            break;
                        }
                        case("Orgánico"):{
                            reco= new Organicos(nom, desc, ruta, recom);
                            break;
                        }
                        case("Biológico"):{
                            reco= new Vidrio(nom, desc, ruta, recom);
                            break;
                        }
                        default:{
                            reco= new General(nom, desc, ruta, recom);
                        }
                    }

                    reco.setTiempoDescomposicion(dias);
                    if (recoActual!=null){
                            controlador.eliminarSugerencia(recoActual.getNombre());
                            recoActual=null;
                            System.out.println("Listo, eliminada");
                    }
                    
                    
                    
                    if (!controlador.nuevaBasura(reco))
                        throw new IllegalArgumentException("Este tipo de basura ya existe");
                    else
                        System.out.println("Listo, no existía esa basura y ya se agregó");
                    SelectorDeImagen SDI = new SelectorDeImagen();
                    File archivoSeleccionado = new File(reco.getRutaImagen());
                    File destino = new File("");
                    SDI.escalarParaJuego(archivoSeleccionado, destino, 95,95);
                    llenarDatos();
                    
                }
                
                catch (IllegalArgumentException ex) {
                    llenarDatos();
                    System.out.println("AJAAAAAAA FALTA CODE: "+ex.getMessage());
                } catch (IOException ex) {
                    Logger.getLogger(RecomendacionesAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        
        ImageButton.ImageButtonStyle cancelarEstilo = new ImageButton.ImageButtonStyle();
        cancelarEstilo.up = new TextureRegionDrawable (upCancelar);
        cancelarEstilo.over = new TextureRegionDrawable(overCancelar);
        salir = new ImageButton(cancelarEstilo);
        salir.setPosition(994, 633);
        salir.addListener(new ClickListener(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new Mapa(game, controlador));
            }
        });
        

        stage.addActor(selectBox);
        stage.addActor(imagen);
        stage.addActor(nombre);
        stage.addActor(descomposición);
        stage.addActor(descripcion);
        stage.addActor(recomendaciones);
        stage.addActor(aceptar);
        stage.addActor(salir);
        
    }
        
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.996f, 0.632f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start drawing with SpriteBatch
        batch.begin();
        batch.draw(fondo, 0, 0);
        batch.draw(pintura, 718, 192);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        
        // Handle screen transitions or input events (optional)
        if (Gdx.input.isTouched()) {
            // Switch to another screen (if desired)
            // game.setScreen(new AnotherScreen(game));
        }
         camara.update();
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
