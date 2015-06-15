package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaConMineral extends Celda {
	public CeldaConMineral(int fila, int columna){
		super(fila, columna);
	}
	
	public boolean tieneMineral(){
		return true;
	}
/*
	@Override
	public void aceptarUnidad(Unidad unidad) {
		unidad.dameMovimiento().mover(this);
	}*/
}
