
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
        
              
        Caballo peon1 = new Caballo(true, Tablero.get_tablero()
                .get_casilla(3, 2));
        
        Caballo peon4 = new Caballo(false, Tablero.get_tablero()
                .get_casilla(1, 0));
        
        Peon peon5 = new Peon(false, Tablero.get_tablero()
                .get_casilla(6, 1));
        
        Rey peon2 = new Rey(true, Tablero.get_tablero().get_casilla(6, 2));
        
        Tablero.get_tablero().print_tablero();
        
        System.out.println("Movimientos de 1:");
        print_movs(peon2);
        System.out.println("Movimientos de 2:");
        print_movs(peon5);
        
        
    }
    public static void print_movs(Pieza peon){
        try {
            int[][] mov = peon.get_movimientos();
            for (int[] mov1 : mov) {
                System.out.println("\t" + String.valueOf(Casilla.corX[mov1[0]]) 
                        + String.valueOf(mov1[1] + 1));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void print_movs_all(Pieza pieza){
        try {
            int[][] mov = pieza.get_capture_move();
            for (int[] mov1 : mov) {
                System.out.println("\t" + String.valueOf(Casilla.corX[mov1[0]]) 
                        + String.valueOf(mov1[1] + 1));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
