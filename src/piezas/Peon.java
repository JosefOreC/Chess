
package piezas;

import tablero.*;
import java.util.ArrayList;

public class Peon extends Pieza{
    
    
    
    public Peon(boolean color, Casilla posicion, char imagen) {
        super(color, posicion, imagen, ' ');
    }
    
    public void coronar(){
        
    }
    
    @Override
    public int[][] get_movimientos() throws Exception{
        int fila_inicial;
        int cambio_y;
        if (super.get_color()){
            fila_inicial = 6;
            cambio_y = -1;
        }
        else{
            fila_inicial = 1;
            cambio_y = +1;
        }
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        boolean is_initial_pos = super.get_posicion()[1] == fila_inicial;
        int[] movimiento = new int[2];
        
        movimiento[0] = super.get_posicion()[0]; 
        movimiento[1] = super.get_posicion()[1] + cambio_y;
        
        if (super.can_move_to(movimiento) && !Tablero.get_tablero().
                get_casilla(movimiento[0], movimiento[1]).get_ocupado()){
            movimientos = super.save_move(movimientos, movimiento);
        }
        
        movimiento[0] = super.get_posicion()[0]+1;
        movimiento[1] = super.get_posicion()[1]+cambio_y;
        
        if (super.can_move_to(movimiento) && Tablero.get_tablero().
                get_casilla(movimiento[0], movimiento[1]).get_ocupado()){
            movimientos = this.save_move(movimientos, movimiento);
        }
        
        movimiento[0] = super.get_posicion()[0]-1;
        movimiento[1] = super.get_posicion()[1]+cambio_y;
        
        if (super.can_move_to(movimiento) && Tablero.get_tablero().
                get_casilla(movimiento[0], movimiento[1]).get_ocupado()){
            movimientos = super.save_move(movimientos, movimiento);
        }
        
        if (!is_initial_pos) return super.convert_arraylist_to_int(movimientos);
        
        movimiento[0] = super.get_posicion()[0];
        movimiento[1] = super.get_posicion()[1]+2*cambio_y;
        
        if (super.can_move_to(movimiento) && !Tablero.get_tablero().
                get_casilla(movimiento[0], movimiento[1]).get_ocupado()){
            movimientos = super.save_move(movimientos, movimiento);
        }
        return super.convert_arraylist_to_int(movimientos);
    }
    
}
