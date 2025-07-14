
package piezas;

import java.util.ArrayList;
import java.util.Arrays;
import tablero.*;

public interface Movimiento{
    
    
    default ArrayList<ArrayList<Integer>> save_move(
            ArrayList<ArrayList<Integer>> movimientos,
            int[] movimiento){
        ArrayList<Integer> movimiento_ = new ArrayList<>();
        movimiento_.add(movimiento[0]);
        movimiento_.add(movimiento[1]);
        movimientos.add(movimiento_);
        return movimientos;
    }
    
    default ArrayList<ArrayList<Integer>> save_move(
            ArrayList<ArrayList<Integer>> movimientos,
            int movimiento_x, int movimiento_y){
        ArrayList<Integer> movimiento_ = new ArrayList<>();
        movimiento_.add(movimiento_x);
        movimiento_.add(movimiento_y);
        movimientos.add(movimiento_);
        return movimientos;
    }
    
    default int[][] convert_arraylist_to_int(ArrayList<ArrayList<Integer>> 
            mov){
        int[][] lista_int = new int[mov.size()][2];
        for (int i=0; i<mov.size(); i++){
            lista_int[i][0] = mov.get(i).get(0);
            lista_int[i][1] = mov.get(i).get(1);
        }
        return lista_int;
    }
    
    default int[][] movimientos_diagonales(int[] coordenada_inicial){
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        int cambio_x = 1, cambio_y = 1;
        int mov_x, mov_y;
        boolean[] is_valid_move;
        
        for (int i = 0; i<2; i++){
            for (int j =0; j<2; j++){
                mov_x = coordenada_inicial[0];
                mov_y = coordenada_inicial[1];
                
                do{
                    mov_x += cambio_x;
                    mov_y += cambio_y;
                    
                    is_valid_move = is_valid_move(mov_x, mov_y, 
                            coordenada_inicial);
                    
                    if (is_valid_move[0])
                        movimientos = save_move(movimientos, mov_x, mov_y);
                    
                    
                }while(is_valid_move[0] && !is_valid_move[1]);
                
                cambio_y *= -1;
            }
            cambio_x *= -1;
        }
        
        return convert_arraylist_to_int(movimientos);
    }
    
    default int[][] movimientos_diagonales(int[] coordenada_inicial, 
            boolean color){
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        int cambio_x = 1, cambio_y = 1;
        int mov_x, mov_y;
        boolean[] is_valid_move;
        
        for (int i = 0; i<2; i++){
            for (int j =0; j<2; j++){
                mov_x = coordenada_inicial[0];
                mov_y = coordenada_inicial[1];
                
                do{
                    mov_x += cambio_x;
                    mov_y += cambio_y;
                    
                    is_valid_move = is_valid_move(mov_x, mov_y, 
                            color);
                    
                    if (is_valid_move[0])
                        movimientos = save_move(movimientos, mov_x, mov_y);
                    
                    
                }while(is_valid_move[0] && !is_valid_move[1]);
                
                cambio_y *= -1;
            }
            cambio_x *= -1;
        }
        
        return convert_arraylist_to_int(movimientos);
    }
    
    default boolean is_casilla_amenazada(int x, int y, boolean color) 
            throws Exception{
        int[] xy = {x,y};
        return is_casilla_amenazada(xy, color);
    }
    
    default boolean is_capture_in_casilla(int[][] movimientos, boolean color, 
            int[] pos_mov) 
            throws Exception{
        Casilla casilla;
        Pieza pieza;
        
        for (int[] mov: movimientos){
            casilla = Tablero.get_tablero().get_casilla(mov[0], mov[1]);
            if (!casilla.get_ocupado()) continue;
            pieza = casilla.get_pieza();
            int[][] movs;
            
            if (pieza.get_name()==' '){
                movs = pieza.get_capture_move();
            }else movs = pieza.get_movimientos_all();
            
            for (int[] m: movs){
                if (Arrays.equals(pos_mov, m)) return true;
            }
        }
        
        return false;
    }
    
    default boolean is_casilla_amenazada(int[] xy, boolean color) 
            throws Exception{
        
        int[][] pd = movimientos_diagonales(xy, color);
        int[][] pr = movimientos_rectos(xy, color);
        int[][] pc = movimientos_caballo(xy, color);
        int[][] movimientos = new int[pd.length+pr.length+pc.length][2]; 
        int i = 0;
        
        for(int[] mov: pr){
            movimientos[i][0] = mov[0];
            movimientos[i][1] = mov[1];
            i++;
        }
        
        for(int[] mov: pd){
            movimientos[i][0] = mov[0];
            movimientos[i][1] = mov[1];
            i++;
        }
        
        for(int[] mov: pc){
            movimientos[i][0] = mov[0];
            movimientos[i][1] = mov[1];
            i++;
        }
        
        return is_capture_in_casilla(movimientos, color, xy);
    }
    
    default boolean[] is_valid_move(int mov_x, int mov_y, 
            int[] coordenada_inicial){
        
        boolean[] result = new boolean[2];
        boolean is_in_range, is_limit_piece, is_capturable_piece;
        
        is_in_range = mov_x >= 0 && 
                mov_x <Tablero.get_tablero().get_dimension_x() &&
                              mov_y >= 0 && 
                mov_y < Tablero.get_tablero().get_dimension_y();

        if (!is_in_range){
            result[0] = false;
            result[1] = false;
            return result;
        }

        is_limit_piece = Tablero.get_tablero().
                get_casilla(mov_x, mov_y).
                get_ocupado();

        if (is_limit_piece){
            is_capturable_piece = Tablero.get_tablero().
                                    get_casilla(mov_x, mov_y)
                                    .get_pieza().get_color()
                                    != Tablero.get_tablero().
                                get_casilla(coordenada_inicial[0], 
                                coordenada_inicial[1])
                                .get_pieza().get_color();
            if (!is_capturable_piece){
                result[0] = false;
                result[1] = true;
                return result;
            }
        }
        
        result[0] = true;
        result[1] = is_limit_piece;
        return result;
    }
    
    default boolean[] is_valid_move(int mov_x, int mov_y){
        
        boolean[] result = new boolean[2];
        boolean is_in_range, is_limit_piece;
        
        is_in_range = mov_x >= 0 && 
                mov_x <Tablero.get_tablero().get_dimension_x() &&
                              mov_y >= 0 && 
                mov_y < Tablero.get_tablero().get_dimension_y();

        if (!is_in_range){
            result[0] = false;
            result[1] = false;
            return result;
        }

        is_limit_piece = Tablero.get_tablero().
                get_casilla(mov_x, mov_y).
                get_ocupado();
        
        result[0] = true;
        result[1] = is_limit_piece;
        return result;
    }
    
    default boolean[] is_valid_move(int mov_x, int mov_y, 
            boolean color){
        
        boolean[] result = new boolean[2];
        boolean is_in_range, is_limit_piece, is_capturable_piece;
        
        is_in_range = mov_x >= 0 && 
                mov_x <Tablero.get_tablero().get_dimension_x() &&
                              mov_y >= 0 && 
                mov_y < Tablero.get_tablero().get_dimension_y();

        if (!is_in_range){
            result[0] = false;
            result[1] = false;
            return result;
        }

        is_limit_piece = Tablero.get_tablero().
                get_casilla(mov_x, mov_y).
                get_ocupado();

        if (is_limit_piece){
            is_capturable_piece = Tablero.get_tablero().
                                    get_casilla(mov_x, mov_y)
                                    .get_pieza().get_color()
                                    != color;
            if (!is_capturable_piece){
                result[0] = false;
                result[1] = true;
                return result;
            }
        }
        
        result[0] = true;
        result[1] = is_limit_piece;
        return result;
    }
    
    default int[][] movimientos_caballo(int[] coordenadas_iniciales){
        int x;
        int y;
        int[][] camb_moves = {
            {-2,-1},
            {-2,1},
            {-1,-2},
            {-1,+2},
            {1,-2},
            {1,+2},
            {2,-1},
            {2,+1},
        };
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        
        for(int[] mov : camb_moves){
            x = coordenadas_iniciales[0]+mov[0];
            y = coordenadas_iniciales[1]+mov[1];
            
            if (is_valid_move(x, y, coordenadas_iniciales)[0])
                movimientos = save_move(movimientos, x, y);
            
        }
        return convert_arraylist_to_int(movimientos);
    }
    
    default int[][] movimientos_caballo(int[] coordenadas_iniciales, 
            boolean color){
        int x;
        int y;
        int[][] camb_moves = {
            {-2,-1},
            {-2,1},
            {-1,-2},
            {-1,+2},
            {1,-2},
            {1,+2},
            {2,-1},
            {2,+1},
        };
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        
        for(int[] mov : camb_moves){
            x = coordenadas_iniciales[0]+mov[0];
            y = coordenadas_iniciales[1]+mov[1];
            
            if (is_valid_move(x, y, color)[0])
                movimientos = save_move(movimientos, x, y);
            
        }
        return convert_arraylist_to_int(movimientos);
    }
    
    default int[][] movimientos_rectos(int[] coordenada_inicial){
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        boolean[] is_valid_move;
        
        for (int i = 0; i < 2; i++) {
            int cx = (i == 0) ? 1 : -1;
            for (int mov_x = coordenada_inicial[0] + cx;
                 mov_x >= 0 && mov_x < Tablero.get_tablero().get_dimension_x();
                 mov_x += cx) {

                is_valid_move = is_valid_move(mov_x, coordenada_inicial[1], 
                        coordenada_inicial);

                if (is_valid_move[0]) {
                    movimientos = save_move(movimientos, mov_x, 
                            coordenada_inicial[1]);
                }

                if (is_valid_move[1]) {
                    break;
                }
            }
        }
        
        for (int i = 0; i < 2; i++) {
            int cy = (i == 0) ? 1 : -1;
            for (int mov_y = coordenada_inicial[1] + cy;
                 mov_y >= 0 && mov_y < Tablero.get_tablero().get_dimension_y();
                 mov_y += cy) {

                is_valid_move = is_valid_move(coordenada_inicial[0], mov_y, coordenada_inicial);

                if (is_valid_move[0]) {
                    movimientos = save_move(movimientos, coordenada_inicial[0], mov_y);
                }

                if (is_valid_move[1]) {
                    break;
                }
            }
        }
        
        return convert_arraylist_to_int(movimientos);
    }
    
    default int[][] movimientos_rectos(int[] coordenada_inicial,
                                    boolean color){
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        boolean[] is_valid_move;
        
        for (int i = 0; i < 2; i++) {
            int cx = (i == 0) ? 1 : -1;
            for (int mov_x = coordenada_inicial[0] + cx;
                 mov_x >= 0 && mov_x < Tablero.get_tablero().get_dimension_x();
                 mov_x += cx) {

                is_valid_move = is_valid_move(mov_x, coordenada_inicial[1], 
                        color);

                if (is_valid_move[0]) {
                    movimientos = save_move(movimientos, mov_x, 
                            coordenada_inicial[1]);
                }

                if (is_valid_move[1]) {
                    break;
                }
            }
        }
        
        for (int i = 0; i < 2; i++) {
            int cy = (i == 0) ? 1 : -1;
            for (int mov_y = coordenada_inicial[1] + cy;
                 mov_y >= 0 && mov_y < Tablero.get_tablero().get_dimension_y();
                 mov_y += cy) {

                is_valid_move = is_valid_move(coordenada_inicial[0], mov_y, 
                        color);

                if (is_valid_move[0]) {
                    movimientos = save_move(movimientos, coordenada_inicial[0], 
                            mov_y);
                }

                if (is_valid_move[1]) {
                    break;
                }
            }
        }
        
        return convert_arraylist_to_int(movimientos);
    }
    
    // TODOS LOS MOVIMIENTOS SIN CONCIDERAR CAPTURA
    
    default int[][] movimientos_rectos_all(int[] coordenada_inicial){
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        boolean[] is_valid_move;
        
        for (int i = 0; i < 2; i++) {
            int cx = (i == 0) ? 1 : -1;
            for (int mov_x = coordenada_inicial[0] + cx;
                 mov_x >= 0 && mov_x < Tablero.get_tablero().get_dimension_x();
                 mov_x += cx) {

                is_valid_move = is_valid_move(mov_x, coordenada_inicial[1]);

                if (is_valid_move[0]) {
                    movimientos = save_move(movimientos, mov_x, 
                            coordenada_inicial[1]);
                }

                if (is_valid_move[1]) {
                    break;
                }
            }
        }
        
        for (int i = 0; i < 2; i++) {
            int cy = (i == 0) ? 1 : -1;
            for (int mov_y = coordenada_inicial[1] + cy;
                 mov_y >= 0 && mov_y < Tablero.get_tablero().get_dimension_y();
                 mov_y += cy) {

                is_valid_move = is_valid_move(coordenada_inicial[0], mov_y);

                if (is_valid_move[0]) {
                    movimientos = save_move(movimientos, coordenada_inicial[0], 
                            mov_y);
                }

                if (is_valid_move[1]) {
                    break;
                }
            }
        }
        
        return convert_arraylist_to_int(movimientos);
    }
    
    default int[][] movimientos_caballo_all(int[] coordenadas_iniciales){
        int x;
        int y;
        int[][] camb_moves = {
            {-2,-1},
            {-2,1},
            {-1,-2},
            {-1,+2},
            {1,-2},
            {1,+2},
            {2,-1},
            {2,+1},
        };
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        
        for(int[] mov : camb_moves){
            x = coordenadas_iniciales[0]+mov[0];
            y = coordenadas_iniciales[1]+mov[1];
            
            if (is_valid_move(x, y)[0])
                movimientos = save_move(movimientos, x, y);
            
        }
        return convert_arraylist_to_int(movimientos);
    }
    
    default int[][] movimientos_diagonales_all(int[] coordenada_inicial){
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        int cambio_x = 1, cambio_y = 1;
        int mov_x, mov_y;
        boolean[] is_valid_move;
        
        for (int i = 0; i<2; i++){
            for (int j =0; j<2; j++){
                mov_x = coordenada_inicial[0];
                mov_y = coordenada_inicial[1];
                
                do{
                    mov_x += cambio_x;
                    mov_y += cambio_y;
                    
                    is_valid_move = is_valid_move(mov_x, mov_y);
                    
                    if (is_valid_move[0])
                        movimientos = save_move(movimientos, mov_x, mov_y);
                    
                    
                }while(is_valid_move[0] && !is_valid_move[1]);
                
                cambio_y *= -1;
            }
            cambio_x *= -1;
        }
        
        return convert_arraylist_to_int(movimientos);
    }
    
    
    
    
}





