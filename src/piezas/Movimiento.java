
package piezas;

import java.util.ArrayList;
import tablero.Tablero;

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
    
    default boolean[] is_valid_move(int mov_x, int mov_y, 
            int[] coordenada_inicial){
        
        boolean[] result = new boolean[2];
        boolean is_in_range, is_limit_piece, is_capturable_piece;
        
        is_in_range = mov_x >= 0 && mov_x <=7 &&
                              mov_y >= 0 && mov_y <=7;

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
    
    default int[][] movimientos_rectos(int[] coordenada_inicial){
        ArrayList<ArrayList<Integer>> movimientos = new ArrayList<>();
        int cambio_x = 1, cambio_y = 1;
        boolean[] is_valid_move;
        
        for (int i = 0; i < 2; i++) {
            int cx = (i == 0) ? 1 : -1;
            for (int mov_x = coordenada_inicial[0] + cx;
                 mov_x >= 0 && mov_x <= 7;
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
                 mov_y >= 0 && mov_y <= 7;
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
    
    
}
