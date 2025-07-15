
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tablero.*;
import piezas.*;
import juego.*;

import javax.script.*;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class main {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) throws Exception{
        
        Game g = new Game();
        
        Tablero.get_tablero().print_tablero();
        Scanner len = new Scanner(System.in);
        String lec;
        while (true) {
            System.out.print("Seleccione la pieza: ");
            lec = len.nextLine();
            g.select_pieza(lec);
            System.out.print("Seleccione el movimiento: ");
            lec = len.nextLine();
            System.out.println(g.do_move(g.get_pieza_en_juego().get_name()+lec));
            Tablero.get_tablero().print_tablero();
        }
        
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
