/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;

import Juego.Modelo.Usuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xande
 */
public class ManejadorArchivoUsuarios {

    public ManejadorArchivoUsuarios() {
    }           
  
    public int escribirArchivo(ArrayList<Usuario> lista) {
        
        String nombreArchivo = "usuarios.dat";
        FileOutputStream archivoEscritura = null;
        ObjectOutputStream manejadorEscritura = null;
        try {
            // apertura del archivo
            manejadorEscritura  = new ObjectOutputStream(new FileOutputStream (nombreArchivo));
            
            //procesamiento
            for (int i = 0; i < lista.size(); i++) {
                manejadorEscritura.writeObject(lista.get(i));
            }
            
            return 0;//IConstantes.EXITO;

        } catch (FileNotFoundException ex) {
            System.out.println("No se pudo crear el archivo " + nombreArchivo);
            return 1;//IConstantes.ERROR_ARCHIVO;
        } catch (IOException ex) {
            System.out.println("Tengo problemas para escribir el archivo " + nombreArchivo);
            return 1;//IConstantes.ERROR_ARCHIVO;
        } 
        finally {
            try {
                if (manejadorEscritura != null){
                    manejadorEscritura.flush();  // asegurarse que todos los datos abandonen el stream
                    manejadorEscritura.close();
                    System.out.println("cerré todo bien");

                }
            } catch (IOException ex) {
                return 1;//IConstantes.ERROR_ARCHIVO;
            }
        }
    }
    
    public ArrayList<Usuario> cargarArchivoUsuarios() {
        
        String nombreArchivo = "usuarios.dat";
        ObjectInputStream manejadorLectura = null;
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        
        try {
            // apertura del archivo
            manejadorLectura = new ObjectInputStream(new FileInputStream (nombreArchivo));
            
            //procesamiento
            lista = new ArrayList<Usuario>();
            Object obj = manejadorLectura.readObject() ;          
            while (obj != null){
                lista.add ( (Usuario) obj);
                obj = manejadorLectura.readObject() ;
            }
            
            //cierre
            return lista;

        } catch (ClassNotFoundException ex) {
            //System.out.println("No se pudo cargar el registro del archivo " + nombreArchivo);
            return new ArrayList<Usuario>();
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado. Creando uno nuevo con datos iniciales.");
            lista = new ArrayList<>(); // Método para crear contenido inicial
            lista.add(new Usuario("Admin", "admin123"));
            escribirArchivo(lista);
        } catch (IOException ex) {
            return lista;
        } 
        finally {
            try {
                if (manejadorLectura != null)
                    manejadorLectura.close();
            } catch (IOException ex) {
                return new ArrayList<Usuario>();
            }
            return lista;
        }
    } 

}
