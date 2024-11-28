/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;

import Juego.Modelo.Basura;
import Juego.Modelo.NoReciclables.Biologicos;
import Juego.Modelo.NoReciclables.General;
import Juego.Modelo.NoReciclables.Organicos;
import Juego.Modelo.Reciclables.Metal;
import Juego.Modelo.Reciclables.Papel;
import Juego.Modelo.Reciclables.Plastico;
import Juego.Modelo.Reciclables.Vidrio;
import Juego.Vista.Desecho;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xande
 */
public class ManejadorArchivoBasuras {
    public ManejadorArchivoBasuras() {
    }
    
    public int escribirArchivo(ArrayList<Basura> lista) {
        
        String nombreArchivo = "basuras.dat";
        FileOutputStream archivoEscritura = null;
        ObjectOutputStream manejadorEscritura = null;
        try {
            // apertura del archivo
            manejadorEscritura  = new ObjectOutputStream(new FileOutputStream (nombreArchivo));
            
            //procesamiento
            for (int i = 0; i < lista.size(); i++) {

                manejadorEscritura.writeObject(lista.get(i));
            }
            
            //cierre
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
    
    public ArrayList<Basura> cargarArchivoBasuras() {
        
        String nombreArchivo = "basuras.dat";
        ObjectInputStream manejadorLectura = null;
        ArrayList<Basura> lista = new ArrayList<Basura>();
        
        try {
            // apertura del archivo
            manejadorLectura = new ObjectInputStream(new FileInputStream (nombreArchivo));
            
            //procesamiento
            lista = new ArrayList<Basura>();
            Object obj = manejadorLectura.readObject() ;          
            while (obj != null){
                lista.add ( (Basura) obj);
                obj = manejadorLectura.readObject() ;
            }
            
            //cierre
            return lista;

        } catch (ClassNotFoundException ex) {
            System.out.println("No se pudo cargar el registro del archivo " + nombreArchivo);
            return new ArrayList<Basura>();
        } catch (IOException ex) {
            System.out.println("fin del archivo" + nombreArchivo);
            return lista;
        } 
        finally {
            try {
                if (manejadorLectura != null)
                    manejadorLectura.close();
            } catch (IOException ex) {
                return new ArrayList<Basura>();
            }
        }
    } 
    
    public ArrayList<Basura> getArrayBasuras(){
        FileHandle directory = Gdx.files.local("Basuras");

        ArrayList<Basura> basuras = new ArrayList<>();
        if (directory.isDirectory()) {
            
            for (FileHandle file : directory.list()) {
                ArrayList<String> caracteristicas = getLineas(file);
                Basura basura;
                System.out.println(caracteristicas.size());
                if (caracteristicas.size() == 6) {
                    
                    switch (caracteristicas.get(1)) {
                        case "Plástico":
                            basura = new Plastico(caracteristicas.get(2), caracteristicas.get(4), caracteristicas.get(0), caracteristicas.get(5));
                            break;
                        case "Metal":
                            basura = new Metal(caracteristicas.get(2), caracteristicas.get(4), caracteristicas.get(0), caracteristicas.get(5));
                            break;
                        case "Vidrio":
                            basura = new Vidrio(caracteristicas.get(2), caracteristicas.get(4), caracteristicas.get(0), caracteristicas.get(5));
                            break;
                        case "Papel":
                            basura = new Papel(caracteristicas.get(2), caracteristicas.get(4), caracteristicas.get(0), caracteristicas.get(5));
                            break;
                        case "Biológicos":
                            basura = new Biologicos(caracteristicas.get(2), caracteristicas.get(4), caracteristicas.get(0), caracteristicas.get(5));
                            break;
                        case "General":
                            basura = new General(caracteristicas.get(2), caracteristicas.get(4), caracteristicas.get(0), caracteristicas.get(5));
                            break;
                        case "Orgánicos":
                            basura = new Organicos(caracteristicas.get(2), caracteristicas.get(4), caracteristicas.get(0), caracteristicas.get(5));
                            break;
                        default:
                            basura = null;
                    }
                if (basura != null)
                basuras.add(basura);
                }
            }
            return basuras;
            
        } else {
            System.out.println("La ruta especificada no es un directorio.");
            return basuras;
        }
        
    }
    
    public ArrayList<Desecho> getArrayDesechos(){
        FileHandle directory = Gdx.files.local("Basuras"); 

        ArrayList<Desecho> desechos = new ArrayList<>();
        if (directory.isDirectory()) {
            
            for (FileHandle file : directory.list()) {
                ArrayList<String> caracteristicas = getLineas(file);
                Desecho desecho;
                if(caracteristicas.size() == 6){
                    
                    switch (caracteristicas.get(1)) {
                        case "Plástico":
                            desecho = new Desecho(caracteristicas.get(0), "Plástico");
                            break;
                        case "Metal":
                            desecho = new Desecho(caracteristicas.get(0), "Metal");
                            break;
                        case "Vidrio":
                            desecho = new Desecho(caracteristicas.get(0), "Vidrio");
                            break;
                        case "Papel":
                            desecho = new Desecho(caracteristicas.get(0), "Papel");
                            break;
                        case "Biológicos":
                            desecho = new Desecho(caracteristicas.get(0), "Biológico");
                            break;
                        case "General":
                            desecho = new Desecho(caracteristicas.get(0), "General");
                            break;
                        case "Orgánicos":
                            desecho = new Desecho(caracteristicas.get(0), "Orgánico");
                            break;
                        default:
                            desecho = null;
                    }
                if (desecho != null)
                   desechos.add(desecho);
                }
            }
            return desechos;
            
        } else {
            System.out.println("La ruta especificada no es un directorio.");
            return desechos;
        }
    }
    
    
   public ArrayList<String> getLineas(FileHandle archivo) {
       
        ArrayList<String> lineas = new ArrayList<>();

        if (!archivo.exists()) { 
            return null; 
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.read()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                    lineas.add(linea);
            }
            return lineas;

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }
   public void nuevaBasura(String ruta, String tipo, String nombre, String dias, String descripcion, String recomendacion){
        FileHandle file = Gdx.files.local("Basuras/" + nombre + ".txt");
        if (!file.exists()) {
            file.writeString(ruta + "\n" + tipo + "\n" + nombre + "\n" + dias + "\n" + descripcion + "\n" + recomendacion, false);
        }
   }
}
