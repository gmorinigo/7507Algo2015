package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;
import fiuba.algo3.algocraft.modelo.unidades.ataques.AbstractDisparo;
import fiuba.algo3.algocraft.modelo.unidades.ataques.DisparoNormal;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;

abstract public class Unidad implements TurnoObserver{
	
	protected Salud salud;
	protected UnidadEstado estado;
	protected int tamanioTransporte;
	protected Movimiento movimiento;
	protected Celda celda;
	protected AbstractDisparo disparo;
	protected Jugador jugador;
	protected Posicion posicionDeConstruccion;
	protected boolean esUnaAlucinacion;
	
	public int getTamanioTransporte(){
		return this.tamanioTransporte;
	}
	
	public boolean esUnidadAerea(){
		return false;
	}
	
	public Unidad(Jugador unJugador) {
		this.salud = this.saludInicial();
		this.estado = new UnidadEstadoNaciendo(this.turnosNecesariosParaCreacion(), this);
		this.celda  = null;
		this.esUnaAlucinacion = false;
		this.movimiento = new Movimiento(this);
		this.disparo = new DisparoNormal(this, 1);
		this.jugador = unJugador;
	}
	

	public void finDeTurno(Turno turno) {
		this.estado.avanzarEnElTurno();	
//		this.estado = new UnidadEstadoViviendo(this);
	}
	
	public void finalizarNacimiento() {
		this.estado = new UnidadEstadoViviendo(this);
		Posicion posicionOcupada = posicionDeConstruccion; 
		Mapa mapa = Mapa.getInstance();
		Celda unaCelda = mapa.dameCelda(posicionOcupada);
		Posicion posNueva;
		
		while (unaCelda.celdaOcupada()){
	        posNueva = new Posicion(posicionOcupada.dameFila(),posicionOcupada.dameColumna()-1);
			unaCelda = mapa.dameCelda(posNueva);
			posicionOcupada = posNueva;
			//System.out.println("buscando celda libre " + posNueva.dameFila() + " " + posNueva.dameColumna() );
		}
		mapa.agregarUnidad(unaCelda.obtenerPosicion(), this);
	}
	
	public boolean estaOperativa() {
		return this.estado.estaOperativa();
	}
	
	/*
	 * Aca hay que preguntar al estado si se puede realizar accion
	 * Cuando se termina la accion hay que cambiar el estado a UnidadEstadoDesancansando
	 */
	public abstract boolean atacar(Celda unaCelda);
	
	abstract protected Salud saludInicial();
	public abstract int turnosNecesariosParaCreacion();
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

	public abstract boolean recibirataque(Unidad unaUnidadAtacante);
	public abstract int DanioAtaque(Unidad unaUnidadAtacada);
	
	public boolean estaViva(){
		return this.salud.tieneVida();
	}
	
	public int obtenerCantidadVida(){
		return this.salud.valorDeSalud();
	}

	public boolean verificarSiPuedeAtacar(Unidad unaUnidadAtacante) {
		if (this.esUnidadAerea() && !(unaUnidadAtacante.atacaUnidadesAereas())){
			return false;
		}
		return true;
	}	
	
	public void destruirUnidad() {
		this.jugador.quitarUnidad(this);
		this.celda.desocuparCelda();
	}
	
	protected abstract boolean atacaUnidadesAereas();

	public boolean sonUnidadesDelMismoJugador(Unidad unaUnidadAtacante) {
		return (this.jugador.verificarMismoJugador(unaUnidadAtacante.jugador));
	}

	public abstract int getRangoAtaque(Unidad unaUnidad);

	public abstract int getRangoAtaque(Construccion construccion);

	public abstract int DanioAtaque(Construccion construccion);

	public abstract int obtenerOcupacionSuministro();

	public boolean esUnaAlucinacion(){
		return this.esUnaAlucinacion;
	}
	
	public void marcarEstaUnidadComoAlucinacion(){
		this.esUnaAlucinacion = true;
		this.salud.settearSaludAlucinacionAcero();
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}

	public abstract Unidad crearAlucinacion();
	
}