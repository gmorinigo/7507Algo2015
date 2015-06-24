package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
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

	@Override
	public boolean agregarConstruccion(Construccion construccion) {
		if( ! construccion.construccionRecolectoraDeMineral() || this.celdaOcupada())
			return false;
		
		this.ocuparCelda();
		this.construccion = construccion;
		return true;
	}

	@Override
	public boolean esPosbibleConstruir(Construccion construccion) {
		return (construccion.construccionRecolectoraDeMineral() && ! this.celdaOcupada());
	}
	
	public String getNombreObjetoPosicionable() {
		return "CeldaConMineral";
	}
}
