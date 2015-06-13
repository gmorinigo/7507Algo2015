package fiuba.algo3.algocraft.modelo.construciones;

public class ConstruccionEstadoViviendo extends ConstruccionEstado {
	
	protected boolean accionRealizada;

	public ConstruccionEstadoViviendo(Construccion contexto) {
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
