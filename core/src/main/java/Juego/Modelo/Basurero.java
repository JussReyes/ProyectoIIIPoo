/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Diego
 */
public class Basurero extends Sprite{
    private String tipo;
    private Texture imagen;

    public Basurero(String tipo, String rutaImagen) {
        super(new Texture(Gdx.files.internal(rutaImagen)));
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public Texture getImagen() {
        return imagen;
    }
    
    public void dispose(){
        this.getTexture().dispose();
    }
}
