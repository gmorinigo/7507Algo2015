package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.Construible;
import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;

abstract public class Unidad implements TurnoObserver, Construible{
	
	protected boolean naciendo;
	protected Salud salud;
	private ProgresoCreacion progresoCreacion;
	
	public abstract void disparar();
	public abstract String getName();
	
	public boolean estaTerminada() {
		return ! this.naciendo; 
	}
	
	public boolean esUnidadAerea(){
		return false;
	}
	
	public Unidad() {
		this.naciendo = true;
		this.salud = this.saludInicial();
		this.progresoCreacion = this.progresoCreacion();
	}
	
	@Override
	public void finDeTurno(Turno turno) {
		if(this.naciendo) {
			this.progresoCreacion.avanzarProgreso();
		}
			
	}
	
	@Override
	public void finalizarCreacion() {
		this.naciendo = false;
		
	}
	
	abstract protected Salud saludInicial();
	abstract protected ProgresoCreacion progresoCreacion();
	
	
}