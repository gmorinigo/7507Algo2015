package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class CentroDeMineral extends Construccion{
	
	//private int tamanio = 2;

	
	public CentroDeMineral(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
	}

	@Override
	public boolean reuneLosRequisitos(Jugador jugador2) {
		return true;
	}




}
