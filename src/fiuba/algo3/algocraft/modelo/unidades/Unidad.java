package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.Construible;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;

abstract public class Unidad implements TurnoObserver, Construible{
	
	protected Salud salud;
	protected boolean accioinRealizada;
	protected UnidadEstado estado;
	protected int tamanioTransporte;

	
	public int getTamanioTransporte(){
		return this.tamanioTransporte;
	}
	
	public abstract void disparar();
	
	public boolean esUnidadAerea(){
		return false;
	}
	
	public Unidad() {
		this.accioinRealizada = false;
		this.salud = this.saludInicial();
		this.estado = new UnidadEstadoNaciendo(this.turnosNecesariosParaCreacion(), this);
	}
	
	@Override
	public void finDeTurno(Turno turno) {
		this.estado = this.estado.avanzarEnElTurno();	
	}
	
	/*
	 * Aca hay que preguntar al estado si se puede realizar accion
	 * Cuando se termina la accion hay que decirle al estado que termine la accion
	 */
	abstract public void mover(int posicionX, int posicionY);
	
	/*
	 * Aca hay que preguntar al estado si se puede realizar accion
	 * Cuando se termina la accion hay que decirle al estado que termine la accion
	 */
	abstract public void atacar(int posicionX, int posicionY);
	

	abstract protected Salud saludInicial();
	abstract protected int turnosNecesariosParaCreacion();
	
}