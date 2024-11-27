/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Diego
 */
public class TextosTortuga {

    public static String[] obtenerTextoNiveles(String rutaArchivo) {
        List<String> niveles = new ArrayList<>();
        String nivelActual = "";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("$")) { 
                    nivelActual += linea.replace("$", "").trim();
                    niveles.add(nivelActual);
                    nivelActual = "";
                } else {
                    nivelActual += linea + "\n";
                }
            }

            if (!nivelActual.isEmpty()) {
                niveles.add(nivelActual.trim());
            }
        } catch (IOException e) {
            
        }

        return niveles.toArray(new String[0]);
    }
}
