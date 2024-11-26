/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Juego;

import Juego.Controlador.ManejadorArchivoUsuarios;
import Juego.Modelo.Usuario;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author xande
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    
    public static void pr1(){
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        ArrayList<Usuario> lista = new ArrayList<>();
        for (int i=1;i<4;i++) {
            lista.add(new Usuario("Thomas"+i, "12"+i));
        }
        MAU.escribirArchivo(lista);
    }
    
    
    public static void pr2(){
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        
        ArrayList<Usuario> lista=MAU.cargarArchivoUsuarios();
        System.out.println(lista.toString());
        
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("iniciando:");
        pr1();
        System.out.println("terminado.");
    }
    
}
