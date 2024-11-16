/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 *
 * @author reyes
 */
public class Nivel extends Sprite{
    
    private EstadoNivel estado = EstadoNivel.BLOQUEADO;
    private Texture textura;
    private BitmapFont fuente =  new BitmapFont();
    private String numero;
    
    public Nivel(String numero) {
        super(new Texture("Uncompleted.png"));
        textura = new Texture("Uncompleted.png");
        this.numero = numero;
        
                
    }
    
   public  void setEstado(EstadoNivel estado){
        this.estado = estado;
        
        textura.dispose();
        
        switch (estado) {
            case BLOQUEADO :
                textura =  new Texture("Uncumpleted.png");
                break;
            case ACTUAL :
                textura = new Texture("Current.png");
                break;
            default :
                textura = new Texture("Completed.png");
                break;
        }
        this.setRegion(textura);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        fuente.setColor(Color.RED);
        fuente.draw(batch, numero, 100, 100);
    }
        
}
