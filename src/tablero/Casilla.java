
package tablero;

import java.util.Arrays;
import java.util.Objects;
import piezas.Pieza;

public class Casilla {
    private int[] coordenada = new int[2];
    private boolean color;
    private boolean ocupado;
    private Pieza pieza = null;
    public static char[] corX = {'a','b','c','d','e','f','g','h','i','j','k',
                            'l' ,'m','n','p','1','r','s','t','u','v','w','x',
                            'y','z'};
    
    public Casilla(int[] coordenada, boolean color){
        this.color = color;
        this.coordenada = coordenada;
    }
    
    public Casilla(int[] coordenada, boolean color, Pieza pieza){
        this.color = color;
        this.coordenada = coordenada;
        this.pieza = pieza;
    }
    
    public boolean get_ocupado(){
        return this.ocupado;
    }
    
    public void ocupar(Pieza pieza){
        this.pieza = pieza;
        this.ocupado = true;
    }
    
    public void desocupar(){
        this.pieza = null;
        this.ocupado = false;
    }
    
    public int[] get_coordenada(){
        return this.coordenada;
    }
    
    public Pieza get_pieza(){
        return this.pieza;
    }
    
    public boolean get_color(){
        return this.color;
    }
    
    public String get_nombre_coordenada(){
        char Sx = this.corX[this.coordenada[0]];
        int Sy = this.coordenada[1]+1;
        return String.valueOf(Sx)+String.valueOf(Sy);
    }
    
}
