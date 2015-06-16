package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaTerrestre extends Celda {
	public CeldaTerrestre(int fila, int columna){
		super(fila, columna);
	}

	@Override
	public boolean puedeMoverse(Unidad unaUnidad) {
		return true;
	}

	@Override
	public boolean esAtacable() {
		return this.celdaOcupada();
	}

	@Override
	public boolean agregarConstruccion(Construccion construccion) {
		if( this.celdaOcupada())

			return false;
		
		this.ocuparCelda();
		this.construccion = construccion;
		return true;
	}

	@Override
	public boolean esPosbibleConstruir(Construccion construccion) {
		return (! this.celdaOcupada());
	}
}
