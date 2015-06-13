package fiuba.algo3.algocraft.modelo.construciones;

abstract public class ConstruccionEstado {
	
	protected Construccion contexto;
	
	public ConstruccionEstado(Construccion contexto) {
		this.contexto = contexto;
	}
	
	abstract public void avanzarEnElTurno();
	abstract public boolean esPosibleRealizarAccion();

	abstract public boolean estaOperativa();
}
