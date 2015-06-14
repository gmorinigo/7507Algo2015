package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaAcuatica extends Celda {
	public CeldaAcuatica(int fila, int columna){
		super(fila, columna);
	}

	@Override
	public void aceptarUnidad(Unidad unidad) {
		unidad.dameMovimiento().mover(this);
	}
}
