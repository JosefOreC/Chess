
import java.util.Scanner;
import tablero.*;
import piezas.Pieza;
import javax.script.*;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class main {

    /**
     * @param args the command line arguments
     */
    
        
    public static void main(String[] args) throws Exception {
        Tablero.get_tablero();
        Pieza[] piezas = new Pieza[32];
        
        for(int i=0; i<8; i++){
            piezas[i] = new Pieza(false, Tablero.get_tablero().get_casilla(i, 0), '🧩');
        }
        
        
        for(int i=8; i<16; i++){
            piezas[i] = new Pieza(true, Tablero.get_tablero().get_casilla(i-8, 1), '🧩');
        }
        
        Tablero.get_tablero().print_tablero();
        
        nam(piezas[12],0,0);
        
        Tablero.get_tablero().print_tablero();
        
        System.out.println("0"+ piezas[0].get_casilla().get_nombre_coordenada());
        System.out.println("1"+ piezas[1].get_casilla().get_nombre_coordenada());
        System.out.println("8"+ piezas[8].get_casilla().get_nombre_coordenada());
        
                
    }
    
    private static void nam(Pieza pieza, int x, int y) {
        try{
            pieza.mover_pieza(Tablero.get_tablero().get_casilla(x, y));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
