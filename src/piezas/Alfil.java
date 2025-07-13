/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import tablero.Casilla;

/**
 *
 * @author danie
 */
public class Alfil extends Pieza implements Movimiento{
    
    public Alfil(boolean color, Casilla posicion, char imagen) {
        super(color, posicion, imagen, 'A');
    }
    
    @Override
    public int[][] get_movimientos(){
        return movimientos_diagonales(this.get_posicion());
    }
    
}
