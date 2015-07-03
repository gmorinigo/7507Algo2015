package fiuba.algo3.algocraft.modelo.construciones;

public class ConstruccionEstadoTrabajando extends ConstruccionEstado {
	
	protected int turnosParaTerminarTrabajo;

	public ConstruccionEstadoTrabajando(int turnosParaTerminarTrabajo, Construccion contexto) {
		super(contexto);
		this.turnosParaTerminarTrabajo = turnosParaTerminarTrabajo;
	}

	@Override
	public void avanzarEnElTurno() {
		this.turnosParaTerminarTrabajo--;
		if(this.turnosParaTerminarTrabajo <=0) {
			this.contexto.terminarTrabajo();
		}
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

	@Override
	public int dameTurnosFaltantes() {
		return turnosParaTerminarTrabajo;
	}

}
