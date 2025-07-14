
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
        switch(pieza){
            case 'C' -> new Caballo(this.get_color(), this.get_casilla());
            case 'D' -> new Dama(this.get_color(), this.get_casilla());
            case 'A' -> new Alfil(this.get_color(), this.get_casilla());
            case 'T' -> new Torre(this.get_color(), this.get_casilla());
        }
        this.estado = false;
        
    }
    
    @Override
    public int[][] get_capture_move(){
        ArrayList<ArrayList<Integer>> mov = new ArrayList<>();
        int camb, x = this.get_posicion()[0], y = this.get_posicion()[1];
        if (this.get_color())  camb = -1;
        else camb = 1;
        
        boolean is_capturable_d = x-1>=0 && y+camb>=0 
                && y+camb<Tablero.get_tablero().get_dimension_y();
        
        boolean is_capturable_a = (y+camb)>=0 
                && (x+1) < Tablero.get_tablero().get_dimension_x()
                && y+camb<Tablero.get_tablero().get_dimension_y();
        
        if (is_capturable_d){
            mov = save_move(mov, new int[]{x-1,y+camb});
        }
        if (is_capturable_a){
            mov = save_move(mov, new int[]{x+1,y+camb});
        }
        
        return convert_arraylist_to_int(mov);
    }
    
    private ArrayList<ArrayList<Integer>> capture_moves(
            ArrayList<ArrayList<Integer>> movimientos, 
            int cambio_y){
        int[] movimiento = new int[2];
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
        return movimientos;
    }
    
    @Override
    public int[][] get_movimientos() throws Exception{
        int fila_inicial;
        int cambio_y;
        if (super.get_color()){
            fila_inicial = Tablero.get_tablero().get_dimension_y()-2;
            cambio_y = -1;
        }
        else{
            fila_inicial = 1;
            cambio_y = +1;
        }
        
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        boolean is_initial_pos = super.get_posicion()[1] == fila_inicial;
        int[] movimiento = new int[2];
        
        
        movimientos = capture_moves(movimientos, cambio_y);
        
        movimiento[0] = super.get_posicion()[0]; 
        movimiento[1] = super.get_posicion()[1] + cambio_y;
        
        if (super.can_move_to(movimiento) && !Tablero.get_tablero().
                get_casilla(movimiento[0], movimiento[1]).get_ocupado()){
            movimientos = save_move(movimientos, movimiento);
        }
        
        if (!is_initial_pos || Tablero.get_tablero().
                get_casilla(movimiento[0], movimiento[1]).get_ocupado()) 
            return convert_arraylist_to_int(movimientos);
        
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
