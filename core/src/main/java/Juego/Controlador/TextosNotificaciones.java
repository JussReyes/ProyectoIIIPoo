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
public class TextosNotificaciones {

   public List<String> obtenerNoticias() {
       
    List<String> noticias = new ArrayList<>();
    FileHandle archivo = Gdx.files.internal("notificaciones.txt"); 
    
    if (!archivo.exists()) { 
        return null; 
    }
    
    try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.read()))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            linea = linea.replace("\\", "\n");
                noticias.add(linea);
        }
        return noticias;

    } catch (IOException e) {
        
        e.printStackTrace();
        return null;
    }
}
    
}
