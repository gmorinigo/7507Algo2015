package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaConGas extends Celda {
	public CeldaConGas(int fila, int columna){
		super(fila, columna);
	}
	
	public boolean tieneGas(){
		return true;
	}

	@Override
	public void aceptarUnidad(Unidad unidad) {
		unidad.dameMovimiento().mover(this);
	}
}
