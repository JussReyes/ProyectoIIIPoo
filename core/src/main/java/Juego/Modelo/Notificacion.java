/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.io.Serializable;

/**
 *
 * @author Diego
 */
public class Notificacion implements Serializable{
    private String tipo;
    private String texto;
    private boolean leida;
    
    public Notificacion(String tipo, String texto) {
        this.tipo = tipo;
        this.texto = texto;
        this.leida = false; 
    }
    public String getTipo() {
        return tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public boolean isLeida() {
        return leida;
    }
    
    public ImageTextButton getImageButton(){
        ImageTextButton boton = new ImageTextButton(texto, new Skin(Gdx.files.internal("CustumUI/UIRec.json")), tipo);
        return boton;
    }
    public void marcarComoLeida() {
        this.leida = true;
    }
    
    

}
