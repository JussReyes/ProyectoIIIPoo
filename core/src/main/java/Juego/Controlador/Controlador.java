/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;

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
        
        try {
            usuarios=MAU.cargarArchivoUsuarios();
        }
        catch(IOException ex){
            usuarios=new ArrayList<>();
        }
        if (usuarios==null)
            usuarios=new ArrayList<>();
    }
    
    public void añadirUsuario(Usuario user) {
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
//        try {
//            usuarios=MAU.cargarArchivoUsuarios();
//        }
//        catch(IOException ex){
//        }
//        if (usuarios==null)
//            usuarios=new ArrayList<>();
        usuarios.add(user);
        MAU.escribirArchivo(usuarios);
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
    
    
}
