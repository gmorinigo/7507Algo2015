package fiuba.algo3.algocraft.modelo.mapa;

public class Posicion {
	private int fila;
	private int columna;
	
	public Posicion(int fila,int columna){
		this.fila = fila;
		this.columna = columna;
	}
	
	public int dameFila(){
		return fila;
	}
	
	public int dameColumna(){
		return columna;
	}

	public boolean compararPosicion(Posicion unaPosicion) {
		return ((fila == unaPosicion.dameFila()) && (columna == unaPosicion.dameColumna())); 
	}
}
