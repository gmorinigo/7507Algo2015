package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaTerrestre extends Celda {
	public CeldaTerrestre(int fila, int columna){
		super(fila, columna);
	}

	@Override
	public boolean puedeMoverse(Unidad unaUnidad) {
		return true;
	}
}
