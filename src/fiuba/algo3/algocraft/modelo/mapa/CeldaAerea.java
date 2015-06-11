package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaAerea extends Celda {
	public CeldaAerea(int fila, int columna){
		super(fila, columna);
	}

	public boolean puedeMoverse(Unidad unaUnidad) {
		// TODO AGREGAR A LAS UNIDADES AEREAS EL RETORNO
		if (unaUnidad.esUnidadAerea()){
			return true;
		}
		else{
			return false;			
		}
	}
}
