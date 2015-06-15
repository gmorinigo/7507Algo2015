package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeRealizarAccionException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento;

abstract public class Unidad implements TurnoObserver{
	
	protected Salud salud;
	protected boolean accioinRealizada;
	protected UnidadEstado estado;
	protected int tamanioTransporte;
	protected Movimiento movimiento;

	
	public int getTamanioTransporte(){
		return this.tamanioTransporte;
	}
	
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
		this.estado.avanzarEnElTurno();	
	}
	
	public void finalizarNacimiento() {
		this.estado = new UnidadEstadoViviendo(this);
		// TODO Aca va la logica para posicionar a la unidad en el mapa
	}
	
	public boolean estaOperativa() {
		return this.estado.estaOperativa();
	}
	
	public Movimiento dameMovimiento() {
		return this.movimiento;
	}
	
	/*
	 * Aca hay que preguntar al estado si se puede realizar accion
	 * Cuando se termina la accion hay que cambiar el estado a UnidadEstadoDesancansando
	 */
	abstract public void mover(int posicionX, int posicionY) throws NoSePuedeRealizarAccionException;
	
	/*
	 * Aca hay que preguntar al estado si se puede realizar accion
	 * Cuando se termina la accion hay que cambiar el estado a UnidadEstadoDesancansando
	 */
	abstract public void atacar(Posicion posicion) throws NoSePuedeRealizarAccionException;
	

	abstract protected Salud saludInicial();
	public abstract int turnosNecesariosParaCreacion();
	protected boolean construccionValidaParaUnidad(){
		return true;}	
	/*
	 * Regenera salud, escudo, etc.
	 */
	abstract protected void vivir();
	
}