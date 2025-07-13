
package piezas;

import java.util.ArrayList;
import tablero.*;


public class Pieza {
    
    private final boolean color;
    protected boolean estado = true;
    protected Casilla casilla;
    private final boolean can_capture = true;
    private char imagen;
    private char sigla = ' ';
    
    protected Pieza(boolean color, Casilla posicion, char imagen, char nombre){
        this.color = color;
        this.casilla = posicion;
        this.imagen = imagen;
        this.sigla = nombre;
        this.casilla.ocupar(this);
    }
    
    public boolean can_capture(){
        return this.can_capture;
    }
    
    public String mover_pieza(Casilla casilla) throws java.lang.Exception{
        if (casilla.equals(this.casilla)) throw new IllegalArgumentException(
                "La pieza no se puede mover al mismo sitio");
        
        if (!this.estado) throw new IllegalArgumentException(
                "La pieza no está en juego");
        
        if (!casilla.get_ocupado()){
            this.casilla.desocupar();
            this.casilla = casilla;
            casilla.ocupar(this);
            return String.valueOf(this.sigla)+casilla.get_nombre_coordenada();
        }
        
        if (casilla.get_pieza().get_color() == this.color) 
            throw new Exception("No se puede capturar una pieza del mismo color");
        
        if (!casilla.get_pieza().can_capture()){
            String message = "No se puede capturar a una pieza "+ 
                    casilla.get_pieza().getClass().getSimpleName();
            throw new Exception(message);
        }
        this.casilla.desocupar();
        casilla.get_pieza().capturar();
        this.casilla = casilla;
        casilla.ocupar(this);
        return String.valueOf(this.sigla)+"x"+casilla.get_nombre_coordenada();
    }
    
    public char get_name(){
        return this.sigla;
    }
    
    public int[][] get_movimientos() throws Exception{
        throw new Exception("No se tienen movimientos para esta pieza");
    }
    
    public int[] get_posicion(){
        return this.casilla.get_coordenada();
    }
    
    public boolean get_color(){
        return this.color;
    }
    
    protected ArrayList<ArrayList<Integer>> save_move(
            ArrayList<ArrayList<Integer>> movimientos,
            int[] movimiento){
        ArrayList<Integer> movimiento_ = new ArrayList<>();
        movimiento_.add(movimiento[0]);
        movimiento_.add(movimiento[1]);
        movimientos.add(movimiento_);
        return movimientos;
    }
    
    protected int[][] convert_arraylist_to_int(ArrayList<ArrayList<Integer>> 
            mov){
        int[][] lista_int = new int[mov.size()][2];
        for (int i=0; i<mov.size(); i++){
            lista_int[i][0] = mov.get(i).get(0);
            lista_int[i][1] = mov.get(i).get(1);
        }
        return lista_int;
    }
    
    protected boolean can_move_to(int[] coordenada){
        Casilla casilla_analizada = null;
        try{
            casilla_analizada = Tablero.get_tablero().
                    get_casilla(coordenada[0], coordenada[1]);
        } catch (Exception e){
            return false;
        }
        if (!casilla_analizada.get_ocupado()) return true;
        
        return casilla_analizada.get_pieza().get_color() != this.color;
    }
    
    public String get_color_name(){
        if (this.color) return "Negro";
        return "Blanco";
    }
    
    public boolean get_estado(){
        return this.estado;
    }
    
    public void capturar(){
        this.estado = false;
        this.casilla = null;
    }
    
    public char get_image(){
        return this.imagen;
    }
    
    public Casilla get_casilla() throws Exception{
        if (!this.estado) throw new Exception("Pieza fuera de juego");
        return this.casilla;
    }
    
}