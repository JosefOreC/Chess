/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piezas;

import java.util.ArrayList;
import tablero.Casilla;

/**
 *
 * @author danie
 */
public class Rey extends Pieza implements Movimiento{
    
    private boolean is_in_check = false;
    
    public Rey(boolean color, Casilla posicion, char imagen) {
        super(color, posicion, imagen, 'R');
        super.set_can_capture(false);
    }
    
    @Override
    public int[][] get_movimientos(){
       int x = super.get_posicion()[0]-1;
       boolean[] is_valid_move;
       ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
       
       for (; x<=super.get_posicion()[0]+1; x++){
           int y = super.get_posicion()[1]-1;
           
           for (; y<=super.get_posicion()[1]+1; y++){
               if (x==y) continue;
               is_valid_move = is_valid_move(x, y, super.get_posicion());
               if (!is_valid_move[0]) continue;
               //Logica para detectar si la casilla de x,y está amenzada
           }
       }
       
       return convert_arraylist_to_int(movimientos);
       
    }
}
