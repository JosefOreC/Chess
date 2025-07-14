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
    
    private final int[][] camb_moves = {
        {-2,-1},
        {-2,1},
        {-1,-2},
        {-1,+2},
        {1,-2},
        {1,+2},
        {2,-1},
        {2,+1},
    };


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
        int x;
        int y;
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
     
        for(int[] mov : camb_moves){
            x = this.get_posicion()[0]+mov[0];
            y = this.get_posicion()[1]+mov[1];
            
            if (is_valid_move(x, y, this.get_posicion())[0])
                movimientos = save_move(movimientos, x, y);
            
        }
        return convert_arraylist_to_int(movimientos);
    }
    
}
