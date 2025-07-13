
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tablero.*;
import piezas.*;
import javax.script.*;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class main {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) throws Exception{
        
        Torre peon1 = new Torre(false, Tablero.get_tablero().get_casilla(5, 5),
                            'T');
        
        Peon peon2 = new Peon(false, Tablero.get_tablero().get_casilla(5, 4),
                        'P');
        Peon peon3 = new Peon(false, Tablero.get_tablero().get_casilla(5, 6),
                        'P');
        Peon peon4 = new Peon(false, Tablero.get_tablero().get_casilla(4, 5),
                        'P');
        
        Peon peon5 = new Peon(false, Tablero.get_tablero().get_casilla(6, 5),
                        'P');
        
        Peon peon6 = new Peon(false, Tablero.get_tablero().get_casilla(4, 4),
                        'P');
        Peon peon7 = new Peon(false, Tablero.get_tablero().get_casilla(4, 6),
                        'P');
        
        Tablero.get_tablero().print_tablero();
        
        System.out.println("Movimientos de 1:");
        print_movs(peon1);
        
        
    }
    public static void print_movs(Pieza peon){
        char[] x = {'a','b','c','d','e','f','g','h'};
        try {
            int[][] mov = peon.get_movimientos();
            for (int[] mov1 : mov) {
                System.out.println("\t" + String.valueOf(x[mov1[0]]) + 
                        String.valueOf(mov1[1] + 1));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
