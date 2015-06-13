package fiuba.algo3.algocraft.modelo.construciones;

public class ConstruccionEstadoViviendo extends ConstruccionEstado {

	public ConstruccionEstadoViviendo(Construccion contexto) {
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
