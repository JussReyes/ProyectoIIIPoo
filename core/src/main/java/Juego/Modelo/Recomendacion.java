/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

/**
 *
 * @author xande
 */
public class Recomendacion {
    private String nombre;
    private String basurero;
    private String descripcion;
    private String imagen; 

    public Recomendacion() {
    }

    public Recomendacion(String nombre, String imagen, String basurero, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.basurero = basurero;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getBasurero() {
        return basurero;
    }

    public void setBasurero(String basurero) {
        this.basurero = basurero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
