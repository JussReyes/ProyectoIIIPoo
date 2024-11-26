/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo.NoReciclables;

import Juego.Modelo.Enumeraciones.BasurerosNoReciclables;
import Juego.Modelo.NoReciclable;

/**
 *
 * @author xande
 */
public class Biologicos extends NoReciclable {
    public Biologicos(String nombre, String descripcion, String rutaImagen, String recomendaciones) {
        super(BasurerosNoReciclables.Biológicos, nombre, descripcion, rutaImagen, recomendaciones, -999);
    }
}
