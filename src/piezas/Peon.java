
package piezas;

import tablero.*;
import source.Image;
import java.util.ArrayList;

public class Peon extends Pieza implements Movimiento{
    
    
    
    public Peon(boolean color, Casilla posicion, String imagen) {
        super(color, posicion, imagen, ' ');
    }
    
    public Peon(boolean color, Casilla posicion) {
        String imagen;
        if (!color) imagen = Image.image_peon_B;
        else imagen = Image.image_peon_N;
        super(color, posicion, imagen, ' ');
    }
    
    public void coronar(char pieza) throws Exception{
        Pieza pieza_c;
        switch(pieza){
            case 'C':
                pieza_c = new Caballo(this.get_color(), this.get_casilla());
                break;
            case 'D':
                pieza_c = new Dama(this.get_color(), this.get_casilla());
                break;
            case 'A':
                pieza_c = new Alfil(this.get_color(), this.get_casilla());
                break;
            case 'T':
                pieza_c = new Torre(this.get_color(), this.get_casilla());
                break;
        }
        this.estado = false;
        
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
            movimientos = save_move(movimientos, movimiento);
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
            movimientos = save_move(movimientos, movimiento);
        }
        
        if (!is_initial_pos) return convert_arraylist_to_int(movimientos);
        
        movimiento[0] = super.get_posicion()[0];
        movimiento[1] = super.get_posicion()[1]+2*cambio_y;
        
        if (super.can_move_to(movimiento) && !Tablero.get_tablero().
                get_casilla(movimiento[0], movimiento[1]).get_ocupado()){
            movimientos = save_move(movimientos, movimiento);
        }
        return convert_arraylist_to_int(movimientos);
    }
    
    @Override
    public String mover_pieza(Casilla casilla) throws Exception{
        if (casilla.get_coordenada()[1] == 7 || casilla.get_coordenada()[1]==0){
            throw new IllegalArgumentException("El peon va a cooronar, "
                    + "debe seleccionar una pieza de coronación.");
        }
        return super.mover_pieza(casilla);
    }
    
    public String mover_pieza(Casilla casilla, char pieza) throws Exception{
        
        String move = super.mover_pieza(casilla) + "=" + pieza; 
        this.coronar(pieza);
        return move;
    }
}
