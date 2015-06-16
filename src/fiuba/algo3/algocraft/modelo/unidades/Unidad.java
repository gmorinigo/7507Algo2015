package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;

abstract public class Unidad implements TurnoObserver{
	
	protected Salud salud;
	protected UnidadEstado estado;
	protected int tamanioTransporte;
	protected Movimiento movimiento;
	protected Celda celda;

	public int getTamanioTransporte(){
		return this.tamanioTransporte;
	}
	
	public boolean esUnidadAerea(){
		return false;
	}
	
	public Unidad() {
		this.salud = this.saludInicial();
		this.estado = new UnidadEstadoNaciendo(this.turnosNecesariosParaCreacion(), this);
		this.celda  = null;
		this.movimiento = new Movimiento(this);
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
	
	/*
	 * Aca hay que preguntar al estado si se puede realizar accion
	 * Cuando se termina la accion hay que cambiar el estado a UnidadEstadoDesancansando
	 */
	public boolean atacar(Celda unaCelda) {
		if (!unaCelda.esAtacable(this)) return false;
		
		if (!this.estado.esPosibleRealizarAccion()) {
			return false;
		}
		
		unaCelda.atacarUnidadDeLaCeldaConUnidad(this);

		this.estado = new UnidadEstadoDescansando(this);
		return true;

	}
	
	abstract protected Salud saludInicial();
	public abstract int turnosNecesariosParaCreacion();
	protected boolean construccionValidaParaUnidad(){
		return true;}	
	/*
	 * Regenera salud, escudo, etc.
	 */
	abstract protected void vivir();

	public Celda dameCelda() {
		return celda;
	}

	public void setCelda(Celda unaCelda) {
		this.celda = unaCelda;
	}
	
	public boolean mover(TipoDireccion direccion) {
		if(! this.estado.esPosibleRealizarAccion()) {
			return false;
		}
		
		boolean movAplicado = this.movimiento.mover(direccion);
		if( ! movAplicado ) {
			return false;
		}
		
		this.estado = new UnidadEstadoDescansando(this);
		return true;
	}

	public abstract void recibirataque(Unidad unaUnidadAtacante);
	public abstract int DanioAtaque(Unidad unaUnidadAtacada);
	
	public boolean estaViva(){
		return this.salud.tieneVida();
	}
	
	public int obtenerCantidadVida(){
		return this.salud.valorDeSalud();
	}

	public boolean verificarSiPuedeAtacar(Unidad unaUnidad) {
		if (unaUnidad.esUnidadAerea() && !(this.atacaUnidadesAereas())){
			return false;
		}
		return true;
	}	
	
	protected abstract boolean atacaUnidadesAereas();
	
}