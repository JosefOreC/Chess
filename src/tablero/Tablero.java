
package tablero;

public class Tablero {
    private Casilla[][] casillas = new Casilla[8][8];
   
    private static Tablero tablero = null;    
    
    private Tablero(){
        boolean color = false;
        for(int y=0; y<8; y++){
            for (int x=0; x<8; x++){
                int[] coordenada = {x,y};
                casillas[x][y] = new Casilla(coordenada, color);
                System.out.println(String.valueOf(casillas[x][y].get_color())+" ("+String.valueOf(x)+
                        ","+String.valueOf(y)+")");
                color = !color;
            }
            color = !color;
        }
    }
    
    public static Tablero get_tablero(){
        if (tablero == null) tablero = new Tablero();
        return tablero;
    }
    
    public Casilla get_casilla(int x, int y){
        return this.casillas[x][y];
    }
    
    public void print_tablero(){
        for(int y=7; y>=0; y--){
            for(int x=0; x<8; x++){
                if (casillas[x][y].get_pieza() == null){
                        if (!casillas[x][y].get_color())
                                System.out.print("x");
                        else System.out.print(" ");
                        continue;
                }
                System.out.print(casillas[x][y].get_pieza().get_image());
            }
            System.out.println("");
        }
    }
    
}
