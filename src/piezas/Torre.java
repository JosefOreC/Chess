/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import tablero.Casilla;


public class Torre extends Pieza implements Movimiento{
    
    public Torre(boolean color, Casilla posicion, char imagen) {
        super(color, posicion, imagen, 'T');
    }
    
    @Override
    public int[][] get_movimientos(){
        return movimientos_rectos(this.get_posicion());
    }
    
}
