package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

abstract public class ExtractorDeGas extends Construccion{
	public ExtractorDeGas(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
	}

	public boolean reuneLosRequisitos(Jugador jugador) {
		return true;
	}

	public int costoGas() {
		return 0;
	}

	public boolean construccionRecolectoraDeGas(){
		return true;
	}
	
	
	public int costoMineral() {
		return 100;
	}
}
