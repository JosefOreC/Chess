
package piezas;

import tablero.Casilla;
import source.Echo;

public class Pieza {
    
    private final boolean color;
    protected boolean estado = true;
    protected Casilla casilla;
    private final boolean can_capture = true;
    private char imagen;
    
    public Pieza(boolean color, Casilla posicion, char imagen){
        this.color = color;
        this.casilla = posicion;
        this.imagen = imagen;
        this.casilla.ocupar(this);
    }
    
    public boolean can_capture(){
        return this.can_capture;
    }
    
    public void mover_pieza(Casilla casilla) throws java.lang.Exception{
        System.out.println(casilla == this.casilla);
        if (casilla.equals(this.casilla)) throw new IllegalArgumentException(
                "La pieza no se puede mover al mismo sitio");
        
        if (!this.estado) throw new IllegalArgumentException(
                "La pieza no está en juego");
        
        if (!casilla.get_ocupado()){
            if (Echo.echo()){
                System.out.print("Se movio la pieza de ");
                System.out.print(String.valueOf(this.get_posicion()[0])+ " "+
                        String.valueOf(this.get_posicion()[1]));
                System.out.print(" a ");
                System.out.println(String.valueOf(casilla.get_coordenada()[0])+ 
                        " "+
                String.valueOf(casilla.get_coordenada()[1]));
            }
            this.casilla.desocupar();
            this.casilla = casilla;
            casilla.ocupar(this);
            
            return;
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
        if (Echo.echo()){
                System.out.print("Se capturo la pieza de ");
                System.out.println(String.valueOf(casilla.get_coordenada()[0])
                +" "+
                String.valueOf(casilla.get_coordenada()[1]));
            }
        this.casilla = casilla;
        casilla.ocupar(this);
        
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