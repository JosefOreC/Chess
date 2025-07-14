/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import source.Image;
import tablero.Casilla;


public class Torre extends Pieza implements Movimiento{
    
    public Torre(boolean color, Casilla posicion, String imagen) {
        super(color, posicion, imagen, 'T');
    }
    
    public Torre(boolean color, Casilla posicion) {
        String imagen;
        if (!color) imagen = Image.image_torre_B;
        else imagen = Image.image_torre_N;
        super(color, posicion, imagen, 'T');
    }
    
    @Override
    public int[][] get_movimientos(){
        return movimientos_rectos(this.get_posicion());
    }
    
    @Override
    public int[][] get_movimientos_all(){
        return movimientos_rectos_all(this.get_posicion());
    }
    
}
