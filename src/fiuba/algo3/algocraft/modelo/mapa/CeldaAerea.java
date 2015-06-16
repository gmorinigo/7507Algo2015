package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaAerea extends Celda {
	public CeldaAerea(int fila, int columna){
		super(fila, columna);
	}

	@Override
	public boolean puedeMoverse(Unidad unaUnidad) {
		return unaUnidad.esUnidadAerea();
	}
}
