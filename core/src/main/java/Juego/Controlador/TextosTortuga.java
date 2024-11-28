/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Diego
 */
public class TextosTortuga {

   public static String obtenerTextoNiveles(String rutaArchivo, int nivelDeseado) {
    List<String> niveles = new ArrayList<>();
    String nivelActual = "";
    int contadorDeNiveles = 0;
    
    
    FileHandle archivo = Gdx.files.internal(rutaArchivo); 
    if (!archivo.exists()) { 
        return null; 
    }
    try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.read()))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.contains("$")) {
                contadorDeNiveles++;
                nivelActual += linea.replace("$", "").trim();
                if (contadorDeNiveles == nivelDeseado) {
                    return nivelActual;  
                }
                niveles.add(nivelActual);
                nivelActual = "";
            } else {
                nivelActual += linea;
            }
        }

        if (contadorDeNiveles < nivelDeseado) {
            return null;
        }
       

    } catch (IOException e) {
        
        e.printStackTrace();
        return null;
    }

    return null;
}
    
    
    public static String[] separarPorDelimitador(String niveles) {
        return niveles.split(">");
    }
}
