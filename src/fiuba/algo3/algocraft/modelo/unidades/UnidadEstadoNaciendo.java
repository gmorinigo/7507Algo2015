package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;

public class UnidadEstadoNaciendo extends UnidadEstado{

	protected int turnosNecesariosParaCreacion;
	
	public UnidadEstadoNaciendo(int turnosNecesariosParaCreacion, Unidad contexto) {
		super(contexto);
		this.turnosNecesariosParaCreacion = turnosNecesariosParaCreacion;
	}
	
	public void avanzarEnElTurno() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		this.turnosNecesariosParaCreacion--;
		if(this.turnosNecesariosParaCreacion <= 0)
			this.contexto.finalizarNacimiento();
	}

	@Override
	public boolean esPosibleRealizarAccion() {
		return false;
	}

	@Override
	public boolean estaOperativa() {
		return false;
	}

}
