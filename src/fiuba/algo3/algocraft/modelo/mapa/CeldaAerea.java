package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaAerea extends Celda {
	public CeldaAerea(int fila, int columna){
		super(fila, columna);
	}

	public boolean puedeMoverse(Unidad unaUnidad) {
		return unaUnidad.esUnidadAerea();
	}

	public boolean esAtacable() {
		return this.celdaOcupada();
	}

	public boolean agregarConstruccion(Construccion construccion) {
		return false;
	}

	public boolean esPosbibleConstruir(Construccion construccion) {
		return false;
	}

	public String getNombreObjetoPosicionable() {
		return "CeldaAerea";
	}
}
