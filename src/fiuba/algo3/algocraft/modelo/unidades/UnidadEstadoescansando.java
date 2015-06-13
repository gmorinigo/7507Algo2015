package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadEstadoescansando extends UnidadEstado{

	public UnidadEstadoescansando(Unidad contexto) {
		super(contexto);
	}

	@Override
	public void avanzarEnElTurno() {
		this.contexto.vivir();
	}

	@Override
	public boolean esPosibleRealizarAccion() {
		return false;
	}

	@Override
	public boolean estaOperativa() {
		return true;
	}
}
