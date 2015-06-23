package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;

abstract public class UnidadEstado {
	
	protected Unidad contexto;
	
	public UnidadEstado(Unidad contexto) {
		this.contexto = contexto;
	}
	
	abstract public void avanzarEnElTurno() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
	abstract public boolean esPosibleRealizarAccion();
	abstract public boolean estaOperativa();
}
