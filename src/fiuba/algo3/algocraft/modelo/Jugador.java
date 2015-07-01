package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;
import java.util.Iterator;

import fiuba.algo3.algocraft.modelo.JugadorEstado.EstadoDelJugador;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Jugador {
	
	private String nombreJugador;
	private String colorJugador;
	protected Raza raza;
	protected ArrayList<Construccion> construcciones;
	protected ArrayList<Unidad> unidadesTerminadas;
	private Almacen almacenGas;
	private Almacen almacenMineral;
	protected JugadorEstado estado;
	protected Jugador rival;

	public Jugador(String unNombre,Raza raza, String unColor) throws JugadorConNombreDemasiadoCortoException {
		if (unNombre.length() < 4){
			throw new JugadorConNombreDemasiadoCortoException();
		}
		
		this.raza = raza;
		this.construcciones = new ArrayList<Construccion>();
		this.unidadesTerminadas = new ArrayList<Unidad>();
		this.almacenGas = new Almacen(500);
		this.almacenMineral = new Almacen(500);
		this.nombreJugador = unNombre;
		this.colorJugador = unColor;
		this.estado = new JugadorEstadoFueraDeJuego(this);
	}

	public ArrayList<Construccion> dameConstrucciones() {
		return this.construcciones;
	}
	
	public ArrayList<Unidad> dameUnidadesTerminadas() {
		return this.unidadesTerminadas;
	}
	
	public void agregarUnidad(Unidad unidad) {
		this.unidadesTerminadas.add(unidad);
	}
	
	public void agregarConstruccion(Construccion construccion) {
		this.construcciones.add(construccion);
	}

	public Almacen dameAlmacenGas() {
		return this.almacenGas;
	}
	
	public Almacen dameAlmacenMineral() {
		return this.almacenMineral;
	}

	public boolean tieneElMismoNombre(String unNombre){
		return (this.nombreJugador.equals(unNombre));
	}

	public boolean tieneElMismoColor(String unColor){
		return (this.colorJugador == unColor);
	}
	
	public int obtenerCantidadPoblacionDisponible() {
		int poblacionOcupada = 0;
		
		Iterator<Unidad> it = unidadesTerminadas.iterator();
		
		while (it.hasNext()) {
			Unidad unaUnidad = it.next();
			if (unaUnidad.estaViva()){
				poblacionOcupada += unaUnidad.obtenerOcupacionSuministro();
			}
		}
		return (this.raza.dameCapacidadDePoblacion(construcciones) - poblacionOcupada);
	}

	public String dameNombre() {
		return this.nombreJugador;
	}

	public String dameColor() {
		return this.colorJugador;
	}
	public Raza dameRaza() {
		return this.raza;
	}

	public int dameCantidadGas() {
		return this.almacenGas.cantidad();
	}
	
	public int dameCantidadMineral() {
		return this.almacenMineral.cantidad();
	}
	
	public int dameLimiteDePoblacion() {
		return raza.dameCapacidadDePoblacion(this.dameConstrucciones());
	}

	public void verificarConstruccionCreada(TipoConstruccion tipo) throws ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException {
		Iterator<Construccion> it = construcciones.iterator();
		boolean construccionEncontrada = false;
		
		while (it.hasNext() && !construccionEncontrada ) {
			Construccion unaConstruccion = it.next();
			if (unaConstruccion.verificarTipoConstruccion(tipo)){
				if (unaConstruccion.estaTerminada())
				construccionEncontrada = true;
			}
		}
		
		if (!construccionEncontrada){
			switch (tipo){
			case creadorUnidadesVoladoras: 
				throw new ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException();
			case creadorUnidadesBasicas: 
				if (this.raza instanceof RazaProtoss){
					throw new ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException();
				}
				else{
					throw new ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException();
				}
			default:
				break;
			}			
		}
		
	}

	public boolean verificarMismoJugador(Jugador unJugador) {
		return ((this.nombreJugador == unJugador.nombreJugador) && (this.colorJugador == unJugador.colorJugador));
	}

	public void quitarUnidad(Unidad unidad) {
		this.unidadesTerminadas.remove(unidad);
		
		this.estado = this.estado.nextEstado();
	}

	public void quitarConstruccion(Construccion construccion) {
		this.construcciones.remove(construccion);
		
		this.estado = this.estado.nextEstado();
		
	}

	public int dameCantidadPoblacion() {
		int poblacionOcupada = 0;
		
		Iterator<Unidad> it = unidadesTerminadas.iterator();
		
		while (it.hasNext()) {
			Unidad unaUnidad = it.next();
			if (unaUnidad.estaViva()){
				poblacionOcupada += unaUnidad.obtenerOcupacionSuministro();
			}
		}
		return poblacionOcupada;
	}
	
	public void empezarPartida() {
		this.estado = new JugadorEstadoJugando(this);
	}
	
	public EstadoDelJugador dameEstadoActual() {
		return this.estado.dameEstadoActual();
	}
	
	public boolean estaEnJuego() {
		return this.estado.dameEstadoActual() == EstadoDelJugador.Jugando;
	}
	
	public void setRival(Jugador rival) {
		this.rival = rival;
	}
	
	public Jugador dameRival() {
		return this.rival;
	}
	
	public void darVictoria() {
		this.estado = new JugadorEstadoGanador(this);
	}

	public int obtenerCantidadPoblacionOcupada() {
		return (this.dameLimiteDePoblacion() - this.obtenerCantidadPoblacionDisponible());
	}

	public boolean esUnidadDelJugador(Unidad unaUnidad) {
		return (this.unidadesTerminadas.contains(unaUnidad));
	}

	public boolean esConstruccionDelJugador(Construccion unaConstruccion) {
		return (this.construcciones.contains(unaConstruccion));
	}
	
}