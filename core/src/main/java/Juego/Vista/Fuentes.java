/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Juego.Vista;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 *
 * @author reyes
 */
public interface Fuentes {

    LabelStyle titulos = new LabelStyle(new BitmapFont(Gdx.files.internal("Fuentes/Titulos.fnt")), 
                                                            new Color((float)15/255, (float)93/255, (float)145/255, 1f));
    
    LabelStyle normales = new LabelStyle(new BitmapFont(Gdx.files.internal("Fuentes/Normales.fnt")), 
                                                            Color.WHITE);
    
    LabelStyle bold = new LabelStyle(new BitmapFont(Gdx.files.internal("Fuentes/Bold.fnt")), 
                                                            Color.WHITE);
    
    LabelStyle error = new LabelStyle(new BitmapFont(Gdx.files.internal("Fuentes/Bold.fnt")), 
                                                            new Color((float)13/255, (float)91/255, (float)147/255, 1f));

    LabelStyle bold2 = new LabelStyle();                                                        
}
