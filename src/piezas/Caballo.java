/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import java.util.ArrayList;
import source.Image;
import tablero.Casilla;

/**
 *
 * @author danie
 */
public class Caballo extends Pieza implements Movimiento{

    public Caballo(boolean color, Casilla posicion, String imagen) {
        super(color, posicion, imagen, 'C');
    }
    
    public Caballo(boolean color, Casilla posicion) {
        String imagen;
        if (!color) imagen = Image.image_caballo_B;
        else imagen = Image.image_caballo_N;
        super(color, posicion, imagen, 'C');
    }
    
    @Override
    public int[][] get_movimientos(){
        return movimientos_caballo(this.get_posicion());
    }
    
    @Override
    public int[][] get_movimientos_all(){
        return movimientos_caballo_all(this.get_posicion());
    }
    
}
