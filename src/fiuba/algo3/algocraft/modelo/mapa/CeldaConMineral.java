package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaConMineral extends Celda {
	public CeldaConMineral(int fila, int columna){
		super(fila, columna);
	}
	
	public boolean tieneMineral(){
		return true;
	}

	public boolean puedeMoverse(Unidad unaUnidad) {
		return false;
	}
	
	public boolean esAtacable() {
		return false;
	}
}
