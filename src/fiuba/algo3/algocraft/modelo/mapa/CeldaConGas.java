package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaConGas extends Celda {
	public CeldaConGas(int fila, int columna){
		super(fila, columna);
	}
	
	public boolean tieneGas(){
		return true;
	}

	public boolean puedeMoverse(Unidad unaUnidad) {
		return false;
	}
	
	public boolean esAtacable() {
		return false;
	}

	public boolean agregarConstruccion(Construccion construccion) {
		if( ! construccion.construccionRecolectoraDeGas() || this.celdaOcupada())
			return false;
		
		this.ocuparCelda();
		this.construccion = construccion;
		return true;
	}

	public boolean esPosbibleConstruir(Construccion construccion) {
		return (construccion.construccionRecolectoraDeGas() && ! this.celdaOcupada());
	}

	public String getNombreObjetoPosicionable() {
		return "CeldaConGas";
	}
}
