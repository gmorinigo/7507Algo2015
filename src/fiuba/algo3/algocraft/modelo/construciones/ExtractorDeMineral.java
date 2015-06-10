package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class ExtractorDeMineral extends Construccion {
	public ExtractorDeMineral(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
	}

	public boolean reuneLosRequisitos(Jugador jugador) {
		return true;
	}

	public int costoGas() {
		return 0;
	}

	public int costoMineral() {
		return 50;
	}

	public boolean construccionRecolectoraDeMineral(){
		return true;
	}
}
