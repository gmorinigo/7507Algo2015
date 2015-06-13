package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadEstadoViviendo extends UnidadEstado{
	
	protected boolean accionRealizada;

	public UnidadEstadoViviendo(Unidad contexto) {
		super(contexto);
		this.accionRealizada = false;
	}

	@Override
	public UnidadEstado avanzarEnElTurno() {
		return this;
	}

	@Override
	public boolean esPosibleRealizarAccion() {
		return !this.accionRealizada;
	}

	@Override
	public void terminarAccion() {
		this.accionRealizada = true;
	}

}
