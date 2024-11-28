/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego.Controlador;

import Juego.Modelo.Basura;
import Juego.Modelo.Notificacion;
import Juego.Modelo.Sugerencia;
import Juego.Modelo.Usuario;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author xande
 */
public class Controlador {
    private ArrayList<Usuario> usuarios;
    private Usuario usuarioActual;
    
    public Controlador(){
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        usuarios=MAU.cargarArchivoUsuarios();
    }
    
    public int iniciarSesion(String nombreUsuario, String pass) {
        for (Usuario user: usuarios) {
            if (nombreUsuario.equals(user.getNombre()))
                if (user.validarContrasena(pass)) {
                    usuarioActual=user;
                    return 0;
                }
                else
                    return 1;
        }
        return 2;
    }
    
    public void añadirUsuario(Usuario usuario) throws IllegalArgumentException {
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        for (Usuario user: usuarios) 
            if (user.getNombre().equals(usuario.getNombre()))
                throw new IllegalArgumentException("¡El usuario no está disponible!");
        usuarios.add(usuario);
        MAU.escribirArchivo(usuarios);
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    public void añadirSugerencia(String rutaImagen, String nombre, String recomendaciones, String descripcion, String basurero, String descomposicion, Usuario usuario) throws IllegalArgumentException {
        Sugerencia reco= new Sugerencia(rutaImagen, nombre, recomendaciones, descripcion, basurero, descomposicion, usuario);
        ManejadorArchivoSugerencias MAS = new ManejadorArchivoSugerencias();
        ArrayList<Sugerencia> recoms = MAS.cargarArchivoSugerencias();
        for (Sugerencia recom: recoms) 
            if (recom.getNombre().equals(reco.getNombre()))
                    throw new IllegalArgumentException("¡Esta basura ya está disponible!");
        recoms.add(reco);
        MAS.escribirArchivo(recoms);
    }
    
    public ArrayList<Sugerencia> getSugerencias(){
        ManejadorArchivoSugerencias MAS = new ManejadorArchivoSugerencias();
        return MAS.cargarArchivoSugerencias();
    }
    
    public void eliminarSugerencia(String nombre) {
        ManejadorArchivoSugerencias MAS = new ManejadorArchivoSugerencias();
        ArrayList<Sugerencia> recoms = MAS.cargarArchivoSugerencias();
        for (Sugerencia recom: recoms) 
            if (recom.getNombre().equals(nombre)) {
                recoms.remove(recom);
                MAS.escribirArchivo(recoms);
                return;
            }
        System.out.println("No se encontró :( :"+nombre);
    }
    
    public ArrayList<Basura> getBasuras(){
        ManejadorArchivoBasuras MAB = new ManejadorArchivoBasuras();
        return MAB.cargarArchivoBasuras();
    }
    
    public Notificacion getNoticia(){
        TextosNotificaciones manejador = new TextosNotificaciones();
        
        List<String> noticias = manejador.obtenerNoticias();
        Random random = new Random();
        
        String noticia = noticias.get(random.nextInt(noticias.size()));
        Notificacion notificacion = new Notificacion("noticia", noticia);
        return notificacion;
    }
    
    public void notificarAdmin(String nombreUsuario){
        Usuario admin;
        for (Usuario usuario : usuarios) {
            if (usuario.equals(new Usuario("Administrador"))) {
                admin = usuario;
                Notificacion notificacion = new Notificacion("sugerencia", "Nueva solicitud de diseño de " + nombreUsuario);
                admin.addNotificacion(notificacion);
                break;
            }
        }
    }
    
    public boolean nuevaBasura(Basura basura) {
        ArrayList<Basura> basuras = getBasuras();
        for (Basura bas: basuras) 
            if (bas.getNombre().equals(basura.getNombre())) {
                return false;
            }
        basuras.add(basura);
        ManejadorArchivoBasuras MAB = new ManejadorArchivoBasuras();
        MAB.escribirArchivo(basuras);
        return true;
    }
    
    public void subirNivelUsuarioActual(){
        usuarioActual.aumentarNivel();
        ManejadorArchivoUsuarios MAU = new ManejadorArchivoUsuarios();
        MAU.escribirArchivo(usuarios);
    }
    
    public String siguienteMensaje(String[][] mensajes, int nivel, int indiceMensaje) {
        System.out.println(mensajes[0][0]);
        if (indiceMensaje < mensajes[nivel].length - 1) {
            return mensajes[nivel][indiceMensaje + 1];
        } else {
            return null;
        }
    }

    public int siguienteIndice (int num){
        return num++;
    }
    
    public String[] procesarTXTortugalo(String rutaArchivo, int nivel) {
        String niveles = TextosTortuga.obtenerTextoNiveles(rutaArchivo, nivel);
        String[]fragmentosPorNivel = TextosTortuga.separarPorDelimitador(niveles);

        return fragmentosPorNivel;
    }
    
}
