/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

import java.io.Serializable;

/**
 *
 * @author Diego
 */
public class Notificacion implements Serializable{
    private TipoNotificacion tipo;
    private String texto;
    private boolean leida;

    public Notificacion(TipoNotificacion tipo, String texto) {
        this.tipo = tipo;
        this.texto = texto;
        this.leida = false; 
    }
    public TipoNotificacion getTipo() {
        return tipo;
    }

    public String getTexto() {
        return texto;
    }

    public boolean isLeida() {
        return leida;
    }

    public void marcarComoLeida() {
        this.leida = true;
    }

}
