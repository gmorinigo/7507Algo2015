package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadEstadoNaciendo extends UnidadEstado{

	protected int turnosNecesariosParaCreacion;
	
	public UnidadEstadoNaciendo(int turnosNecesariosParaCreacion, Unidad contexto) {
		super(contexto);
		this.turnosNecesariosParaCreacion = turnosNecesariosParaCreacion;
	}
	
	@Override
	public UnidadEstado avanzarEnElTurno() {
		this.turnosNecesariosParaCreacion--;
		return ( this.turnosNecesariosParaCreacion > 0 ) ? this: new UnidadEstadoViviendo(this.contexto);
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
