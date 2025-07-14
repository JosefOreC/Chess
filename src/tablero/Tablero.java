
package tablero;

public class Tablero {
    private Casilla[][] casillas;
    private int[] dimensiones = new int[2];
    private static Tablero tablero = null;    
    
    
    public Tablero(){
        this.dimensiones[0] = 8;
        this.dimensiones[1] = 8;
        boolean color = false;
        this.casillas = new Casilla[this.dimensiones[0]][this.dimensiones[1]];
        for(int y=0; y<8; y++){
            for (int x=0; x<8; x++){
                int[] coordenada = {x,y};
                casillas[x][y] = new Casilla(coordenada, color);
                color = !color;
            }
            color = !color;
        }
    }
    
    private Tablero(int dimension_x, int dimension_y){
        this.dimensiones[0] = dimension_x;
        this.dimensiones[1] = dimension_y;
        boolean color = false;
        this.casillas = new Casilla[this.dimensiones[0]][this.dimensiones[1]];
        for(int y=0; y<dimensiones[1]; y++){
            for (int x=0; x<dimensiones[0]; x++){
                int[] coordenada = {x,y};
                casillas[x][y] = new Casilla(coordenada, color);
                color = !color;
            }
            color = !color;
        }
    }
    
    public static Tablero get_tablero(){
        if (tablero == null) tablero = new Tablero();
        return tablero;
    }
    
    public static Tablero get_tablero(int dimension_x, int dimension_y){
        if (tablero == null) tablero = new Tablero(dimension_x, dimension_y);
        return tablero;
    }
    
    public static void delete_tablero(){
        if(tablero == null) return;
        tablero = null;
    }
    
    public int get_dimension_x(){
        return this.dimensiones[0];
    }
    
    public int get_dimension_y(){
        return this.dimensiones[1];
    }
    
    public Casilla get_casilla(int x, int y){
        return this.casillas[x][y];
    }
    
    public void print_tablero(){
        for(int y=dimensiones[1]-1; y>=0; y--){
            for(int x=0; x<=dimensiones[0]; x++){
                if (x==dimensiones[0]){
                    System.out.println(y+1);
                    continue;
                }
                if (casillas[x][y].get_pieza() == null){
                        if (!casillas[x][y].get_color())
                                System.out.print("- ");
                        else System.out.print(": ");
                        continue;
                }
                System.out.print(casillas[x][y].get_pieza().get_image());
                System.out.print(" ");
            }
            if (y==0){
                for (int i =0; i<get_dimension_x(); i++) System.out.print(
                        Casilla.corX[i] + " ");
            }
             System.out.println("");
        }
        
    }
    
}
