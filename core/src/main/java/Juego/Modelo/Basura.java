/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

/**
 *
 * @author xande
 */
public abstract class Basura {
    String nombre;
    String descripcion;
    String rutaImagen;
    String recomendaciones;
    int tiempoDescomposicion;

    public Basura(String nombre, String descripcion, String rutaImagen, String recomendaciones, int tiempoDescomposicion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.recomendaciones = recomendaciones;
        this.tiempoDescomposicion = tiempoDescomposicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public int getTiempoDescomposicion() {
        return tiempoDescomposicion;
    }

    public void setTiempoDescomposicion(int tiempoDescomposicion) {
        this.tiempoDescomposicion = tiempoDescomposicion;
    }
    
    public abstract Enum<?> getTipoBasurero();
}
