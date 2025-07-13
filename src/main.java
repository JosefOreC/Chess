
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
        
        Peon peon1 = new Peon(false, Tablero.get_tablero().get_casilla(4, 1),
                            'n');
        
        Peon peon2 = new Peon(false, Tablero.get_tablero().get_casilla(7, 1),
                            'm');
        
        Peon peon3 = new Peon(true, Tablero.get_tablero().get_casilla(5, 2),
                            'o');
        
        Tablero.get_tablero().print_tablero();
        
        System.out.println("Movimientos de 1:");
        print_movs(peon1);
        System.out.println("Movimientos de 2:");
        print_movs(peon2);
        System.out.println("Movimientos de 3:");
        print_movs(peon3);
        
        System.out.println(peon1.mover_pieza(Tablero.get_tablero().get_casilla(4, 3)));
        
        Tablero.get_tablero().print_tablero(); 
    }
    public static void print_movs(Peon peon){
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
