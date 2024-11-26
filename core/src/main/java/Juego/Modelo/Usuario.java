/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Diego
 */
public class Usuario implements Serializable{
    private String nombre;
    private String contrasena;
    private int nivel;
    private ArrayList<Notificacion>notificaciones = new ArrayList<>();

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.nivel = 1;
    }

    public boolean validarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }

    public void aumentarNivel() {
        this.nivel++;
    }

    public int getNivel() {
        return nivel;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getCantNotificaciones() {
        return notificaciones.size();
    }
    
    
}