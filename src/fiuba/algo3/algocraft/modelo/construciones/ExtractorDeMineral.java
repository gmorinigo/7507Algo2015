package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

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

	@Override
	public void finDeTurno(Turno turno) {
		// TODO Auto-generated method stub
		
	}
}
