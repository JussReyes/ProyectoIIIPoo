/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

import Juego.Modelo.Enumeraciones.BasurerosReciclables;

/**
 *
 * @author xande
 */
public class Reciclable extends Basura {
    BasurerosReciclables tipoBasurero;

    public Reciclable(BasurerosReciclables tipoBasurero, String nombre, String descripcion, String rutaImagen, String recomendaciones, int tiempoDescomposicion) {
        super(nombre, descripcion, rutaImagen, recomendaciones, tiempoDescomposicion);
        this.tipoBasurero = tipoBasurero;
    }
    
    @Override
    public BasurerosReciclables getTipoBasurero(){
        return tipoBasurero;
    }
    
    public void setTipoBasurero(BasurerosReciclables tipoBasurero) {
        this.tipoBasurero = tipoBasurero;
    }
}
