package fiuba.algo3.algocraft;

public class Celda {
	private Posicion posicion;
	
	public Celda(int fila, int columna) {
		Posicion unaPosicion = new Posicion(fila,columna);
		posicion = unaPosicion;
	}

}
