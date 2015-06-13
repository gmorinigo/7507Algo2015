package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadEstadoNaciendo extends UnidadEstado{

	protected int turnosNecesariosParaCreacion;
	
	public UnidadEstadoNaciendo(int turnosNecesariosParaCreacion, Unidad contexto) {
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
