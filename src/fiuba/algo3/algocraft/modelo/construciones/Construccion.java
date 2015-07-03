package fiuba.algo3.algocraft.modelo.construciones;

import java.util.ArrayList;
import java.util.Iterator;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;
import fiuba.algo3.algocraft.vista.Dibujable;

public abstract class Construccion implements TurnoObserver, Dibujable{

	protected ArrayList<Celda> celdas;
	protected Jugador jugador;
	protected Posicion posicionCeldaSupIzquierda;
	protected ConstruccionEstado estado;
	protected TipoConstruccion tipoConstruccion;
	protected Salud salud;
	
	public Construccion(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo) {
		this.celdas = new ArrayList<Celda>();
		this.jugador = jugador;
		this.posicionCeldaSupIzquierda = unaPosicion;
		this.estado = new ConstruccionEstadoNaciendo(this.turnosNecesariosParaCreacion(), this);
		this.tipoConstruccion = unTipo;
		this.salud = this.saludInicial();
	}

	public boolean estaTerminada(){
		return this.estaOperativa();
	}
	
	public void finDeTurno(Turno turno) {
		this.estado.avanzarEnElTurno();
	}

	public boolean construccionRecolectoraDeMineral(){
		return false;
	}
	
	public boolean construccionRecolectoraDeGas(){
		return false;
	}

	public ArrayList<Celda> dameCeldas(){
		return celdas;
	}
	
	public Posicion damePosicionCeldaSupIzquierda(){
		return this.posicionCeldaSupIzquierda;
	}
	
	public void agregarCeldas(ArrayList<Celda> celdas) {
		this.celdas = celdas;
	}
	
	public void finalizarNacimiento() {
		this.estado = new ConstruccionEstadoViviendo(this);
	}
	
	public void finalizarCreacion() {
		this.estado = new ConstruccionEstadoViviendo(this);
	}
	
	public void terminarTrabajo() {
		this.estado = new ConstruccionEstadoViviendo(this);
	}
	
	public void destruir() {
	}
	
	public boolean estaOperativa() {
		return this.estado.estaOperativa();
	}
	
	abstract protected int turnosNecesariosParaCreacion();
	abstract protected Salud saludInicial();

	public boolean recibirataque(Unidad unaUnidadAtacante){
		this.salud.atacar(unaUnidadAtacante.DanioAtaque(this));
		if(! this.salud.tieneVida()) {
			this.destruirConstruccion();
		}
		return true;
	}

	public void destruirConstruccion() {
		this.jugador.quitarConstruccion(this);
		Iterator<Celda> it = celdas.iterator();

		while (it.hasNext()) {
			it.next().desocuparCelda();
		}
		
		Turno unTurno = Turno.getInstance();
		unTurno.removeObserver(this);
	}
	
	abstract protected void vivir();

	public boolean verificarTipoConstruccion(TipoConstruccion tipo) {
		return (this.tipoConstruccion == tipo);
	}

	public boolean sonUnidadesDelMismoJugador(Unidad unaUnidadAtacante) {
		return (this.jugador.verificarMismoJugador(unaUnidadAtacante.getJugador()));
	}
	
	public boolean estaViva() {
		return this.salud.tieneVida();
	}
	
	public int obtenerCantidadVida(){
		return this.salud.valorDeSalud();
	}
	
	public void atacarConTormentaPsionica() {
		this.salud.atacar(100);
		if(! this.salud.tieneVida()) {
			this.jugador.quitarConstruccion(this);
			Iterator<Celda> it = celdas.iterator();

			while (it.hasNext()) {
				it.next().desocuparCelda();
			}
		}
	}

	
	public Jugador getJugador() {
		return this.jugador;
	}	
	
	public Posicion getPosicion() {
		return this.posicionCeldaSupIzquierda;
	}
	
	public int dameTurnosFaltantesParaLaCreacion(){
		return estado.dameTurnosFaltantes();
	}

	public int obtenerCantidadEscudo() {
		if (this.jugador.dameRaza().esRazaProtoss()){
			SaludProtoss unaSalud = (SaludProtoss) this.salud;
			return unaSalud.valorEscudo();
		}
		
		return 0;
	};
}
