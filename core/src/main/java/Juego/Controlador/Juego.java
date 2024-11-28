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
public interface Juego {
    Basurero general = new Basurero("General", "GeneralCan.png");;
    Basurero papel = new Basurero("Papel", "PaperCan.png");
    Basurero plastico = new Basurero("Plástico", "PlasticCan.png");
    Basurero vidrio = new Basurero("Vidrio", "GlassCan.png");
    Basurero metal = new Basurero("Metal", "MetalCan.png");
    Basurero organico = new Basurero("Orgánicos", "OrganicCan.png");
    Basurero biologico = new Basurero("Biológicos", "BiologicCan.png");
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
