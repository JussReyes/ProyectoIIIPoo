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
public class General extends NoReciclable {
    
    public General(String nombre, String descripcion, String rutaImagen, String recomendaciones) {
        super(BasurerosNoReciclables.General, nombre, descripcion, rutaImagen, recomendaciones, 9999);
    }
}