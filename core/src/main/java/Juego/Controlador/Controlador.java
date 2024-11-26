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
    ArrayList<Usuario> usuarios;
    
    public Controlador(){
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        try {
            usuarios=MAU.cargarArchivoUsuarios();
        }
        catch(IOException ex){
            usuarios=new ArrayList<>();
        }
    }
    
    public void añadirUsuario(Usuario user) {
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        try {
            usuarios=MAU.cargarArchivoUsuarios();
        }
        catch(IOException ex){
            usuarios=new ArrayList<>();
        }
        usuarios.add(user);
        MAU.escribirArchivo(usuarios);
    }
}
