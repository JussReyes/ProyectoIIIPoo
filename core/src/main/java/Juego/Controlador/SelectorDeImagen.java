/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.imageio.ImageIO;
/**
 *
 * @author xande
 */
public class SelectorDeImagen {
    
    private String obtenerFormatoImagen(File archivo) {
        String nombreArchivo = archivo.getName();
        return nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
    }
    
    public void escalarYCopiar(File archivoSeleccionado, File directorioDestino, int maxAncho, int maxAlto) throws IOException {
        // Leer la imagen desde el archivo seleccionado
        BufferedImage imagenOriginal = ImageIO.read(archivoSeleccionado);
        if (imagenOriginal == null) {
            throw new IOException("El archivo no es una imagen válida: " + archivoSeleccionado.getName());
        }

        // Obtener las dimensiones originales de la imagen
        int anchoOriginal = imagenOriginal.getWidth();
        int altoOriginal = imagenOriginal.getHeight();

        // Calcular las dimensiones escaladas manteniendo la relación de aspecto
        double relacionAspecto = (double) anchoOriginal / altoOriginal;
        int anchoEscalado;
        int altoEscalado;

        if ((double) maxAncho / maxAlto > relacionAspecto) {
            // Ajustar por el alto máximo
            altoEscalado = maxAlto;
            anchoEscalado = (int) (altoEscalado * relacionAspecto);
        } else {
            // Ajustar por el ancho máximo
            anchoEscalado = maxAncho;
            altoEscalado = (int) (anchoEscalado / relacionAspecto);
        }

        // Crear una nueva imagen escalada usando Graphics2D
        BufferedImage imagenEscalada = new BufferedImage(anchoEscalado, altoEscalado, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagenEscalada.createGraphics();

        // Aplicar configuración para mejorar la calidad del escalado
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Dibujar la imagen escalada
        g2d.drawImage(imagenOriginal, 0, 0, anchoEscalado, altoEscalado, null);
        g2d.dispose();

        // Guardar la imagen escalada en el archivo destino
        File archivoDestino = new File(directorioDestino, "Big"+archivoSeleccionado.getName());
        if (!archivoDestino.exists()) {
            ImageIO.write(imagenEscalada, obtenerFormatoImagen(archivoSeleccionado), archivoDestino);
        }
    }

    public void escalarParaJuego(File archivoSeleccionado, File directorioDestino, int maxAncho, int maxAlto) throws IOException {
        // Leer la imagen desde el archivo seleccionado
        BufferedImage imagenOriginal = ImageIO.read(archivoSeleccionado);
        if (imagenOriginal == null) {
            throw new IOException("El archivo no es una imagen válida: " + archivoSeleccionado.getName());
        }

        // Obtener las dimensiones originales de la imagen
        int anchoOriginal = imagenOriginal.getWidth();
        int altoOriginal = imagenOriginal.getHeight();

        // Calcular las dimensiones escaladas manteniendo la relación de aspecto
        double relacionAspecto = (double) anchoOriginal / altoOriginal;
        int anchoEscalado;
        int altoEscalado;

        if ((double) maxAncho / maxAlto > relacionAspecto) {
            // Ajustar por el alto máximo
            altoEscalado = maxAlto;
            anchoEscalado = (int) (altoEscalado * relacionAspecto);
        } else {
            // Ajustar por el ancho máximo
            anchoEscalado = maxAncho;
            altoEscalado = (int) (anchoEscalado / relacionAspecto);
        }

        // Crear una nueva imagen escalada usando Graphics2D
        BufferedImage imagenEscalada = new BufferedImage(anchoEscalado, altoEscalado, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagenEscalada.createGraphics();

        // Aplicar configuración para mejorar la calidad del escalado
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Dibujar la imagen escalada
        g2d.drawImage(imagenOriginal, 0, 0, anchoEscalado, altoEscalado, null);
        g2d.dispose();

        // Guardar la imagen escalada en el archivo destino
        File archivoDestino = new File(directorioDestino, archivoSeleccionado.getName());
        if (!archivoDestino.exists()) {
            ImageIO.write(imagenEscalada, obtenerFormatoImagen(archivoSeleccionado), archivoDestino);
        }
    }


    
    public String copiarImagenAlRepositorio(String destinoDirectorio) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar una imagen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

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
                    escalarYCopiar(archivoSeleccionado, directorioDestino, 230, 230);

                // Retornar la ruta relativa del archivo copiado
                String rutaRelativa = archivoDestino.getPath();
                System.out.println("Archivo copiado a: " + rutaRelativa);
                String rutas = archivoSeleccionado.toPath().toString();
                rutas+="##";
                rutas+= rutaRelativa;
                return rutas;

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
