/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo.Reciclables;

import Juego.Modelo.Enumeraciones.BasurerosReciclables;
import Juego.Modelo.Reciclable;

/**
 *
 * @author xande
 */
public class Metal extends Reciclable {
    public Metal (String nombre, String descripcion, String rutaImagen, String recomendaciones) {
        super(BasurerosReciclables.Metal, nombre, descripcion, rutaImagen, recomendaciones, 100*365);
    }
}
