package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;

abstract public class Unidad implements TurnoObserver{
	
	protected boolean naciendo;
	protected Salud salud;

	public abstract void disparar();
	public abstract String getName();
	//public boolean estaTerminado();
	
	public Unidad() {
		this.naciendo = true;
		this.salud = this.saludInicial();
	}
	
	@Override
	public void finDeTurno() {
		// TODO Auto-generated method stub	
	}
	
	abstract protected Salud saludInicial();
}