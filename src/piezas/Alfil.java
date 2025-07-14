/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import tablero.Casilla;
import source.Image;
/**
 *
 * @author danie
 */
public class Alfil extends Pieza implements Movimiento{
    
    public Alfil(boolean color, Casilla posicion, String imagen) {
        super(color, posicion, imagen, 'A');
    }
    
    public Alfil(boolean color, Casilla posicion) {
        String imagen;
        if (!color) imagen = Image.image_alfil_B;
        else imagen = Image.image_alfil_N;
        super(color, posicion, imagen, 'A');
    }
    
    @Override
    public int[][] get_movimientos(){
        return movimientos_diagonales(this.get_posicion());
    }
    
    @Override
    public int[][] get_movimientos_all(){
        return movimientos_diagonales_all(this.get_posicion());
    }
    
}
