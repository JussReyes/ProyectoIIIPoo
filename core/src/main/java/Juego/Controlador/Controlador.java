/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;

import Juego.Modelo.Basura;
import Juego.Modelo.Recomendacion;
import Juego.Modelo.Usuario;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author xande
 */
public class Controlador {
    private ArrayList<Usuario> usuarios;
    private Usuario usuarioActual;
    
    public Controlador(){
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        usuarios=MAU.cargarArchivoUsuarios();
    }
    
    public int iniciarSesion(String nombreUsuario, String pass) {
        for (Usuario user: usuarios) {
            if (nombreUsuario.equals(user.getNombre()))
                if (user.validarContrasena(pass)) {
                    usuarioActual=user;
                    return 0;
                }
                else
                    return 1;
        }
        return 2;
    }
    
    public void añadirUsuario(Usuario usuario) throws IllegalArgumentException {
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        for (Usuario user: usuarios) 
            if (user.getNombre().equals(usuario.getNombre()))
                throw new IllegalArgumentException("¡El usuario no está disponible!");
        usuarios.add(usuario);
        MAU.escribirArchivo(usuarios);
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    public void añadirRecomendacion(String nombre, String imagen, String basurero, String descripcion) throws IllegalArgumentException {
        Recomendacion reco= new Recomendacion(nombre, imagen, basurero, descripcion);
        ManejadorArchivoSugerencias MAS = new ManejadorArchivoSugerencias();
        ArrayList<Recomendacion> recoms = MAS.cargarArchivoSugerencias();
        for (Recomendacion recom: recoms) 
            if (recom.getNombre().equals(reco.getNombre()))
                    throw new IllegalArgumentException("¡Esta basura ya está disponible!");
        recoms.add(reco);
        MAS.escribirArchivo(recoms);
    }
    
    public ArrayList<Recomendacion> getRecomendaciones(){
        ManejadorArchivoSugerencias MAS = new ManejadorArchivoSugerencias();
        return MAS.cargarArchivoSugerencias();
    }
    
    public void eliminarRecomendacion(String nombre) {
        ManejadorArchivoSugerencias MAS = new ManejadorArchivoSugerencias();
        ArrayList<Recomendacion> recoms = MAS.cargarArchivoSugerencias();
        for (Recomendacion recom: recoms) 
            if (recom.getNombre().equals(nombre)) {
                recoms.remove(recom);
                return;
            }
    }
    
    public ArrayList<Basura> getBasuras(){
        ManejadorArchivoBasuras MAB = new ManejadorArchivoBasuras();
        return MAB.cargarArchivoBasuras();
    }
    
    public boolean nuevaBasura(Basura basura) {
        ArrayList<Basura> basuras = getBasuras();
        for (Basura bas: basuras) 
            if (bas.getNombre().equals(basura.getNombre())) {
                return false;
            }
        basuras.add(basura);
        ManejadorArchivoBasuras MAB = new ManejadorArchivoBasuras();
        MAB.escribirArchivo(basuras);
        return true;
    }
    
    public void subirNivelUsuarioActual(){
        usuarioActual.aumentarNivel();
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        MAU.escribirArchivo(usuarios);
    }
    
}
