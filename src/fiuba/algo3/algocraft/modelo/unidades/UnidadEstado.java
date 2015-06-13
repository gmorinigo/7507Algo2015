package fiuba.algo3.algocraft.modelo.unidades;

abstract public class UnidadEstado {
	
	protected Unidad contexto;
	
	public UnidadEstado(Unidad contexto) {
		this.contexto = contexto;
	}
	
	abstract public void avanzarEnElTurno();
	abstract public boolean esPosibleRealizarAccion();
	abstract public void terminarAccion();
}
