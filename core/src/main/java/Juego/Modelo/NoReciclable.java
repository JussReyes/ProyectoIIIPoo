/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

import Juego.Modelo.Enumeraciones.BasurerosNoReciclables;

/**
 *
 * @author xande
 */
public abstract class NoReciclable extends Basura {
    BasurerosNoReciclables tipoBasurero;

    public NoReciclable(BasurerosNoReciclables tipoBasurero, String nombre, String descripcion, String rutaImagen, String recomendaciones, int tiempoDescomposicion) {
        super(nombre, descripcion, rutaImagen, recomendaciones, tiempoDescomposicion);
        this.tipoBasurero = tipoBasurero;
    }

    @Override
    public BasurerosNoReciclables getTipoBasurero() {
        return tipoBasurero;
    }

    public void setTipoBasurero(BasurerosNoReciclables tipoBasurero) {
        this.tipoBasurero = tipoBasurero;
    }
}
