package fiuba.algo3.algocraft.modelo.construciones;

public class ConstruccionEstadoNaciendo extends ConstruccionEstado{
	
	protected int turnosNecesariosParaCreacion;

	public ConstruccionEstadoNaciendo(int turnosNecesariosParaCreacion, Construccion contexto) {
		super(contexto);
		this.turnosNecesariosParaCreacion = turnosNecesariosParaCreacion;
	}

	@Override
	public void avanzarEnElTurno() {
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
