package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadEstadoViviendo extends UnidadEstado{

	public UnidadEstadoViviendo(Unidad contexto) {
		super(contexto);
	}

	@Override
	public void avanzarEnElTurno() {
		this.contexto.vivir();
	}

	@Override
	public boolean esPosibleRealizarAccion() {
		return true;
	}

	@Override
	public boolean estaOperativa() {
		return true;
	}
}
