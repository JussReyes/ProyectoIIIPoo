/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

/**
 *
 * @author Diego
 */
public class Basurero {
    private String tipo;
    private String rutaImagen;

    public Basurero(String tipo, String rutaImagen) {
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
    }

    public String getTipo() {
        return tipo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }
}
