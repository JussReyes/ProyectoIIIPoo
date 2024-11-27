/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Vista;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.Objects;

/**
 *
 * @author reyes
 */
public class Desecho extends Sprite{
    
    private String tipoBasurero;
    
    public Desecho(String rutaImagen, String tipoBasurero) {
        super(new Texture(Gdx.files.internal(rutaImagen)));
        this.tipoBasurero = tipoBasurero;
    }
    public Desecho(Desecho copia){
        this.set(copia);
        this.tipoBasurero = copia.tipoBasurero;
    }

    public String getTipoBasurero() {
        return tipoBasurero;
    }

    public void setTipoBasurero(String tipoBasurero) {
        this.tipoBasurero = tipoBasurero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Desecho other = (Desecho) obj;
        return Objects.equals(this.getX(), other.getX()) && Objects.equals(this.getY(), other.getY());
    }
    
    public void dispose(){
        this.getTexture().dispose();
    }
    
}
