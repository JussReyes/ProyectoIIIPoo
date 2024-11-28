/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.io.Serializable;

/**
 *
 * @author reyes
 */
public class Sugerencia implements Serializable{
    Texture  imagen;
    String nombre;
    String recomendaciones;
    String descripcion;
    String basurero;
    String descomposicion;
    Usuario usuario;

    public Sugerencia(Texture imagen, String nombre, String recomendaciones, String descripcion, String basurero, String descomposicion, Usuario usuario) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.recomendaciones = recomendaciones;
        this.descripcion = descripcion;
        this.basurero = basurero;
        this.descomposicion = descomposicion;
        this.usuario = usuario;
    }
    
    public Sugerencia(String rutaImagen, String nombre, String recomendaciones, String descripcion, String basurero, String descomposicion, Usuario usuario) {
        this.imagen = new Texture(Gdx.files.internal(rutaImagen));
        this.nombre = nombre;
        this.recomendaciones = recomendaciones;
        this.descripcion = descripcion;
        this.basurero = basurero;
        this.descomposicion = descomposicion;
        this.usuario = usuario;
    }

    public Texture getImagen() {
        return imagen;
    }

    public void setImagen(Texture imagen) {
        this.imagen = imagen;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBasurero() {
        return basurero;
    }

    public void setBasurero(String basurero) {
        this.basurero = basurero;
    }

    public String getDescomposicion() {
        return descomposicion;
    }

    public void setDescomposicion(String descomposicion) {
        this.descomposicion = descomposicion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
