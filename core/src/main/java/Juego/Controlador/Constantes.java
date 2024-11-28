/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Juego.Controlador;

import Juego.Modelo.Basurero;
import Juego.Vista.Desecho;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author reyes
 */
public interface Constantes {
    Basurero general = new Basurero("general", "GeneralCan.png");;
    Basurero papel = new Basurero("papel", "PaperCan.png");
    Basurero plastico = new Basurero("plastico", "PlasticCan.png");
    Basurero vidrio = new Basurero("vidrio", "GlassCan.png");
    Basurero metal = new Basurero("metal", "MetalCan.png");
    Basurero organico = new Basurero("organico", "OrganicCan.png");
    Basurero biologico = new Basurero("biologico", "BiologicCan.png");
    Basurero[] afrrayBasureros = {general, papel, vidrio, organico, plastico, biologico, metal};
    ArrayList<Basurero> basureros = new ArrayList<>(Arrays.asList(afrrayBasureros));
    
    Desecho botella = new Desecho("Bottle.png", "plastico");
    Desecho lata = new Desecho("Can.png", "metal");
    Desecho hoja = new Desecho("Leaf.png", "organico");
    Desecho copa = new Desecho("Glass.png", "vidrio");
    Desecho plato = new Desecho("Plate.png", "general");
    Desecho jeringa = new Desecho("Syringe.png", "biologico");
    Desecho bolaPapel = new Desecho("Paper.png", "papel");
    
}
