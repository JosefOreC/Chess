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
public class Rey extends Pieza implements Movimiento{
    
    private boolean is_in_check = false;
    
    public Rey(boolean color, Casilla posicion, String imagen) {
        super(color, posicion, imagen, 'R');
        super.set_can_capture(false);
    }
    
    public Rey(boolean color, Casilla posicion) {
        String imagen;
        if (!color) imagen = Image.image_rey_B;
        else imagen = Image.image_rey_N;
        super(color, posicion, imagen, 'R');
    }
    
    @Override
    public int[][] get_movimientos() throws Exception{
       int x = super.get_posicion()[0]-1;
       boolean[] is_valid_move;
       ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
       
       for (; x<=super.get_posicion()[0]+1; x++){
           int y = super.get_posicion()[1]-1;
           
           for (; y<=super.get_posicion()[1]+1; y++){
               if (x==y) continue;
               is_valid_move = is_valid_move(x, y, super.get_posicion());
               if (!is_valid_move[0]) continue;
               this.casilla.desocupar();
               if (is_casilla_amenazada(x, y, this.get_color())){
                   this.casilla.ocupar(this);
                   continue;
               } 
                   
               this.casilla.ocupar(this);
               movimientos = save_move(movimientos, x,y);
           }
       }
       
       return convert_arraylist_to_int(movimientos);
    }
    
    @Override
    public int[][] get_movimientos_all() throws Exception{
       int x = super.get_posicion()[0]-1;
       boolean[] is_valid_move;
       ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
       
       for (; x<=super.get_posicion()[0]+1; x++){
           int y = super.get_posicion()[1]-1;
           
           for (; y<=super.get_posicion()[1]+1; y++){
               if (x==y) continue;
               is_valid_move = is_valid_move(x, y);
               if (!is_valid_move[0]) continue;
               movimientos = save_move(movimientos, x,y);
           }
       }
       
       return convert_arraylist_to_int(movimientos);
    } 
    
    @Override
    public String mover_pieza(Casilla casilla) throws Exception{
        this.is_in_check = false;
        return super.mover_pieza(casilla);
    }
}
