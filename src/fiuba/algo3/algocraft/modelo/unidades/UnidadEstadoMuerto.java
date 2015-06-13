package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadEstadoMuerto extends UnidadEstado{

	public UnidadEstadoMuerto(Unidad contexto) {
		super(contexto);
	}

	@Override
	public UnidadEstado avanzarEnElTurno() {
		return this;
	}

	@Override
	public boolean esPosibleRealizarAccion() {
		return false;
	}

	@Override
	public void terminarAccion() {
		// TODO Auto-generated method stub
		
	}

}
