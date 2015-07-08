package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.ArrayIndezOutOfBoundsException;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;
import fiuba.algo3.algocraft.modelo.unidades.ataques.AbstractDisparo;
import fiuba.algo3.algocraft.modelo.unidades.ataques.DisparoNormal;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import fiuba.algo3.algocraft.vista.Dibujable;

abstract public class Unidad implements TurnoObserver, Dibujable{
	
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
	

	public void finDeTurno(Turno turno) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		this.estado.avanzarEnElTurno();	
//		this.estado = new UnidadEstadoViviendo(this);
	}
	
	public void finalizarNacimiento() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		this.estado = new UnidadEstadoViviendo(this);
		Posicion posicionOcupada = posicionDeConstruccion; 
		Mapa mapa = Mapa.getInstance();
		Celda unaCelda = mapa.dameCelda(posicionOcupada);
		Posicion posNueva;
		int col = 1;
		while ((unaCelda.celdaOcupada()) || (unaCelda.esCeldaAerea())  || (unaCelda.tieneGas()) || (unaCelda.tieneMineral())){
	        posNueva = new Posicion(posicionOcupada.dameFila(),posicionOcupada.dameColumna()-col);
	        try{
	        	unaCelda = mapa.dameCelda(posNueva);
	        }
	        catch(ArrayIndexOutOfBoundsException e){
	        	col = -1;
	        	posNueva = new Posicion(posicionOcupada.dameFila(),posicionOcupada.dameColumna()+1);
	        }
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
	
	public boolean mover(TipoDireccion direccion) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
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
	public abstract boolean recibirataqueMisilEMP();
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
		Turno unTurno = Turno.getInstance();
		unTurno.removeObserver(this);
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

	public void recibirAtaqueTormentaPsionica() {
		this.salud.atacar(100);
		if(! this.salud.tieneVida()) {
			this.jugador.quitarUnidad(this);
			this.celda.desocuparCelda();
		}
	}

	public abstract int obtenerCantidadEscudo();

	public boolean esUnaNaveTransporte() {
		return false;
	}

	/**
	 * Afecta a una unidad en particular y la consume lentamente hasta matarla.
	 *  Cualquier otra unidad que esté exactamente al lado, también pierde vida
	 *   mientras esté a distancia 1 del irradiado. (Cuesta 75 de energía)
	 **/
	public void recibirAtaqueRadiacion() {
		this.salud.atacar(20);
		if(! this.salud.tieneVida()) {
			this.jugador.quitarUnidad(this);
			this.celda.desocuparCelda();
		}
	}
	
	public Posicion getPosicion() {
		return this.dameCelda().obtenerPosicion();
	}
	
	public boolean esUnAltoTemplario(){
		return false;
	}
	
	public boolean esUnaNaveCiencia(){
		return false;
	}
}