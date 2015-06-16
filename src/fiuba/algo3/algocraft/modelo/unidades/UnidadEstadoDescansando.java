package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadEstadoDescansando extends UnidadEstado{

	public UnidadEstadoDescansando(Unidad contexto) {
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
