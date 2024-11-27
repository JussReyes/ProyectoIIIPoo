/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
/**
 *
 * @author xande
 */
public class SelectorDeImagen {
    public String copiarImagenAlRepositorio(String destinoDirectorio) {
        // Crear un JFileChooser para seleccionar archivos
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar una imagen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        // Filtro para aceptar solo imágenes
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imágenes (JPG, PNG, BMP)", "jpg", "png", "bmp"));

        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File archivoSeleccionado = fileChooser.getSelectedFile();

            try {
                // Crear el directorio de destino si no existe
                File directorioDestino = new File(destinoDirectorio);
                if (!directorioDestino.exists()) {
                    directorioDestino.mkdirs();
                }

                // Copiar el archivo al directorio del proyecto
                File archivoDestino = new File(directorioDestino, archivoSeleccionado.getName());
                if (!archivoDestino.exists())
                    Files.copy(archivoSeleccionado.toPath(), archivoDestino.toPath());

                // Retornar la ruta relativa del archivo copiado
                String rutaRelativa = archivoDestino.getPath();
                System.out.println("Archivo copiado a: " + rutaRelativa);
                return rutaRelativa;

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error al copiar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
        return null; // Si no se seleccionó ningún archivo
    }
}
