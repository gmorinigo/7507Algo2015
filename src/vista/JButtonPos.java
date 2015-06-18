package vista;

import javax.swing.JButton;

public class JButtonPos extends JButton{

    private int fila;
    private int columna;
    
    public JButtonPos(int fil, int col){
            super();
            fila = fil;
            columna = col;
    }
    
    public int dameFila(){
            return fila;
            
    }
    public int dameColumna(){
            return columna;
            
    }
    
    public void agregarFila(int fil){
            fila = fil;
            
    }
    public void AgregarColumna(int col){
            columna = col;
            
    }
}
