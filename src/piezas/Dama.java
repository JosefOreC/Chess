
package piezas;

import source.Image;
import tablero.Casilla;

public class Dama extends Pieza implements Movimiento{
    
    public Dama(boolean color, Casilla posicion, String imagen) {
        super(color, posicion, imagen, 'D');
    }
    
    public Dama(boolean color, Casilla posicion) {
        String imagen;
        if (!color) imagen = Image.image_dama_B;
        else imagen = Image.image_dama_N;
        super(color, posicion, imagen, 'D');
    }
    
    @Override
    public int[][] get_movimientos(){
        
        int[][] mov_d = movimientos_diagonales(super.get_posicion()),
                mov_r = movimientos_rectos(super.get_posicion());
        
        int [][] movimientos = new int[mov_d.length+mov_r.length][2];
        
        for(int i=0; i<mov_d.length; i++) {
            movimientos[i][0] = mov_d[i][0];
            movimientos[i][1] = mov_d[i][1];
        }
        
        for(int i=mov_d.length; i<mov_r.length+mov_d.length; i++) {
            movimientos[i][0] = mov_r[i-mov_d.length][0];
            movimientos[i][1] = mov_r[i-mov_d.length][1];
        }
        return movimientos;
    }
    
    @Override
    public int[][] get_movimientos_all(){
        
        int[][] mov_d = movimientos_diagonales_all(super.get_posicion()),
                mov_r = movimientos_rectos_all(super.get_posicion());
        
        int [][] movimientos = new int[mov_d.length+mov_r.length][2];
        
        for(int i=0; i<mov_d.length; i++) {
            movimientos[i][0] = mov_d[i][0];
            movimientos[i][1] = mov_d[i][1];
        }
        
        for(int i=mov_d.length; i<mov_r.length+mov_d.length; i++) {
            movimientos[i][0] = mov_r[i-mov_d.length][0];
            movimientos[i][1] = mov_r[i-mov_d.length][1];
        }
        return movimientos;
    }
    
    
}
