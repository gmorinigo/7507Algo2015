package fiuba.algo3.algocraft.modelo.turnos;

import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;

public interface TurnoObserver {
	/*
	 * 
	 */
	public void finDeTurno(Turno turno) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;

//	void finDeTurno();
}
