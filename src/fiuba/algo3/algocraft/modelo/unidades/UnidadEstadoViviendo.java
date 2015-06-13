package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadEstadoViviendo extends UnidadEstado{
	
	protected boolean accionRealizada;

	public UnidadEstadoViviendo(Unidad contexto) {
		super(contexto);
		this.accionRealizada = false;
	}

	@Override
	public void avanzarEnElTurno() {
		this.contexto.vivir();
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
