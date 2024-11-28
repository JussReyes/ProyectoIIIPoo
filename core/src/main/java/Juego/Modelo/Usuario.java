/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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
    
    public Usuario(String nombre){
        this.nombre = nombre;
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
        
        if(notificaciones == null){
            return 0;
        }
        return notificaciones.size();
    }
    
    public void addNotificacion(Notificacion notificacion){
        notificaciones.add(notificacion);
    }
    
    public ArrayList<Notificacion> getNotificaciones(){
        return notificaciones;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.nombre, other.nombre);
    }
    
    
}