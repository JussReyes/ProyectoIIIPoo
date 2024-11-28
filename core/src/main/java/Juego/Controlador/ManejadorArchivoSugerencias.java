/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;

import Juego.Modelo.Recomendacion;
import Juego.Modelo.Sugerencia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author xande
 */
public class ManejadorArchivoSugerencias {

    public ManejadorArchivoSugerencias() {
    }
    
    public int escribirArchivo(ArrayList<Sugerencia> lista) {
        
        String nombreArchivo = "sugerencias.dat";
        FileOutputStream archivoEscritura = null;
        ObjectOutputStream manejadorEscritura = null;
        try {
            // apertura del archivo
            manejadorEscritura  = new ObjectOutputStream(new FileOutputStream (nombreArchivo));
            
            //procesamiento
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("Escribiendo la sugerencia "+i);
                manejadorEscritura.writeObject(lista.get(i));
            }
            
            //cierre
            return 0;//IConstantes.EXITO;

        } catch (FileNotFoundException ex) {

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
    
    public ArrayList<Sugerencia> cargarArchivoSugerencias() {
        
        String nombreArchivo = "sugerencias.dat";
        ObjectInputStream manejadorLectura = null;
        ArrayList<Sugerencia> lista = new ArrayList<>();
        try {
            // apertura del archivo
            FileInputStream arc = new FileInputStream (nombreArchivo);
            manejadorLectura = new ObjectInputStream(arc);
            
            //procesamiento
            lista = new ArrayList<>();
            Object obj = manejadorLectura.readObject() ;          
            while (obj != null){
                lista.add ( (Sugerencia) obj);
                obj = manejadorLectura.readObject() ;
            }
            
            //cierre
            return lista;

        } catch (ClassNotFoundException ex) {
            System.out.println("No se pudo cargar el registro del archivo " + nombreArchivo);
            return new ArrayList<>();
        } catch (FileNotFoundException ex){
            System.out.println("No se encontroooo el archivo " + nombreArchivo);
            return new ArrayList<>();
        } catch (IOException ex) {
            System.out.println("fin del archivo" + nombreArchivo);
            return lista;
        } 
        finally {
            try {
                if (manejadorLectura != null)
                    manejadorLectura.close();
            } catch (IOException ex) {
                return new ArrayList<Sugerencia>();
            }
        }
    } 
    
}
