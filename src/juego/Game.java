
package juego;

import piezas.*;
import tablero.*;


public class Game {
    public Tablero tablero;
    public Pieza[] piezas_blanco;
    public Pieza[] piezas_negro;
    private Pieza pieza_en_juego;
    private boolean turno = false;
    
    public Game(){
        this.tablero = Tablero.get_tablero();
        create_game();
    }
    
    
    
    public String do_move(String move) throws Exception{
        char[] l = move.toCharArray();
        int[] xy= new int[]{Casilla.get_num_index(l[1]),
             Integer.parseInt(Character.toString(l[2]))-1};
        return do_move(xy);
    }
    
    public String do_move(int[] move) throws Exception{
        turno = !turno;
        return this.pieza_en_juego.mover_pieza(tablero.get_casilla(move[0], 
                move[1]));
    }
    
    private void create_game(){
        this.piezas_blanco = create_pieces(false);
        this.piezas_negro = create_pieces(true);
        
    }
    
    public void select_pieza(String move){
        char[] l = move.toCharArray();
        int[] xy= new int[]{Casilla.get_num_index(l[0]),
             Integer.parseInt(Character.toString(l[1]))-1};
        select_pieza(xy);
    }
    
    public Pieza get_pieza_en_juego(){
        return pieza_en_juego;
    }
    
    public void select_pieza(int[] coordenada){
        this.pieza_en_juego = tablero.
                get_casilla(coordenada[0], coordenada[1]).get_pieza();
    }
    
    private Pieza get_pieza(int indice){
        if (this.turno) return piezas_negro[indice];
        else return piezas_blanco[indice];
    }
    
    private Pieza[] create_pieces(boolean color){
        Pieza[] piezas = new Pieza[16];
        int i = 0;
        
        for(Pieza p: create_peones(color)){
            piezas[i] = p;
            i++;
        }
        
        for (Pieza p: create_torres(color)){
            piezas[i] = p;
            i++;
        }
        
        for (Pieza p: create_alfiles(color)){
            piezas[i] = p;
            i++;
        }
        
        for (Pieza p: create_caballos(color)){
            piezas[i] = p;
            i++;
        }
        
        piezas[i] = create_rey(color);
        i++;
        
        piezas[i] = create_dama(color);           
        return piezas;
    }
    
    private Peon[] create_peones(boolean color){
        Peon[] peones = new Peon[8];
        int fila;
        if (color) fila = this.tablero.get_dimension_y()-2;
        else fila = 1;
        for(int i=0; i<8; i++) 
            peones[i] = new Peon(color, this.tablero.get_casilla(i, fila));
        return peones;
    }
    
    private Torre[] create_torres(boolean color){
        int fila;
        if (color) fila = this.tablero.get_dimension_y()-1;
        else fila = 0;
        
        Torre[] torres = new Torre[2];
        
        for(int i=0; i<8; i+=7)
            torres[i/7] = new Torre(color, this.tablero.get_casilla(i, fila));
        
        return torres;
    }
    
    private Alfil[] create_alfiles(boolean color){
        int fila;
        if (color) fila = this.tablero.get_dimension_y()-1;
        else fila = 0;
        int j = 0;
            
        Alfil[] alfiles = new Alfil[2];
        
        for(int i=2; i<=5; i+=3){
            alfiles[j] = new Alfil(color, this.tablero.get_casilla(i, fila));
            j++;
        }
        return alfiles;
    }
    
    private Caballo[] create_caballos(boolean color){
        int fila;
        if (color) fila = this.tablero.get_dimension_y()-1;
        else fila = 0;
        int j=0;
        Caballo[] caballos = new Caballo[2];
        
        for(int i=1; i<=6; i+=5){
            caballos[j] = new Caballo(color, this.tablero.get_casilla(i, fila));
            j++;
        }
        
        return caballos;
    }
    
    private Rey create_rey(boolean color){
        int fila;
        if (color) fila = this.tablero.get_dimension_y()-1;
        else fila = 0;
            
        return new Rey(color, this.tablero.get_casilla(4, fila));
    }
    
    private Dama create_dama(boolean color){
        int fila;
        if (color) fila = this.tablero.get_dimension_y()-1;
        else fila = 0;
            
        return new Dama(color, this.tablero.get_casilla(3, fila));
    }
    
}
