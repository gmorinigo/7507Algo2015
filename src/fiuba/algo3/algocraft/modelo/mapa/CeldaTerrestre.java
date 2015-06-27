package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaTerrestre extends Celda {
	public CeldaTerrestre(int fila, int columna){
		super(fila, columna);
	}

	public boolean puedeMoverse(Unidad unaUnidad) {
		return true;
	}

	public boolean esAtacable() {
		return this.celdaOcupada();
	}

	public boolean agregarConstruccion(Construccion construccion) {
		if( this.celdaOcupada())

			return false;
		
		this.ocuparCelda();
		this.construccion = construccion;
		return true;
	}

	public boolean esPosbibleConstruir(Construccion construccion) {
		return (! this.celdaOcupada());
	}

	protected boolean esCeldaTerrestre() {
		return true;
	}
	
	public String getNombreObjetoDibujable() {
		return "CeldaTerrestre";
	}
	

}
