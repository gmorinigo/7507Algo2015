package fiuba.algo3.algocraft.modelo;

import java.rmi.NoSuchObjectException;
import java.util.Observable;

import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoColorException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoNombreException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConMismaRazaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCantidadDeJugadoresSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class AlgoCraft extends Observable{
	private Mapa mapaDelJuego;
	private Jugador jugador1;
	private Jugador jugador2;
	
	public AlgoCraft() {
		this.mapaDelJuego = Mapa.getInstance();
		jugador1 = null;
		jugador2 = null;
	}
	
	public Mapa dameElMapaDelJuego(){
		return this.mapaDelJuego;
	}
	
	public void agregarJugador(String nombreJugador, Raza unaRaza, String unColor) 
	throws MaximaCantidadDeJugadoresSuperadaException, JugadorConElMismoNombreException, JugadorConElMismoColorException, JugadorConNombreDemasiadoCortoException, JugadorConMismaRazaException{		
		if (this.jugador1 == null){
			this.agregarJugadorNumero1(nombreJugador, unaRaza, unColor);
		}
		else{
			if (this.jugador2 == null){
				this.agregarJugadorNumero2(nombreJugador, unaRaza, unColor);
			}
			else{
				throw new MaximaCantidadDeJugadoresSuperadaException();
			}
		}
	}
	
	
	public void agregarJugadorNumero1(String nombreJugador, Raza unaRaza, String unColor) throws JugadorConNombreDemasiadoCortoException{
		this.jugador1 = new Jugador(nombreJugador, unaRaza, unColor);
	}
	
	
	public void agregarJugadorNumero2(String nombreJugador, Raza unaRaza, String colorJugador) 
	throws JugadorConElMismoNombreException, JugadorConElMismoColorException, JugadorConNombreDemasiadoCortoException{
		if (this.jugador1.tieneElMismoNombre(nombreJugador)){
			throw new JugadorConElMismoNombreException();
		}
		
		if (this.jugador1.tieneElMismoColor(colorJugador)){
			throw new JugadorConElMismoColorException();
		}
		
		this.jugador2 = new Jugador(nombreJugador, unaRaza, colorJugador);
		Turno unTurno = Turno.getInstance(this.jugador1, this.jugador2);
		unTurno.comenzar();
	}
	
	
	public int obtenerCantidadDeJugadores(){
		int cantJugadores=0;
		
		if (jugador1 != null) {
			cantJugadores+=1;
		}
		
		if (jugador2 != null) {
			cantJugadores+=1;
		}
		
		return cantJugadores;
	}
	
	public Jugador dameJugador1() {
		return this.jugador1;
	}
	
	public Jugador dameJugador2() {
		return this.jugador2;
	}

	public void agregarConstruccion(Jugador unJugador, TipoConstruccion unTipoConstruccion, Posicion posicionCeldaPresionada) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException {
		AbstractConstruccionFactory unFactory = unJugador.dameRaza().getFactoryConstrucciones();
		Construccion unaConstruccion = unFactory.crearConstruccion(unTipoConstruccion, posicionCeldaPresionada, unJugador);
		this.mapaDelJuego.agregarConstruccion(unaConstruccion);
		this.avisarObservers();
	}
	
	public void agregarUnidad(Jugador unJugador, TipoUnidad unTipoUnidad,Posicion posicionCeldaPresionada) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada{
		AbstractUnidadFactory unFactory = unJugador.dameRaza().getFactoryUnidades();
		Unidad unaUnidad = unFactory.crearUnidad(unTipoUnidad, unJugador, posicionCeldaPresionada);
		this.mapaDelJuego.agregarUnidad(posicionCeldaPresionada, unaUnidad);
		this.avisarObservers();
	}
	
	public void avisarObservers(){
        setChanged();
        this.notifyObservers();
	}

	public Jugador dameElJugadorDelTurno() {
		Turno unTurno = Turno.getInstance(this.jugador1, this.jugador2);
		return unTurno.obtenerJugadorConTurno();
	}

	public void avanzarTurno() {
		Turno unTurno = Turno.getInstance();
		unTurno.avanzarTurno();
		this.avisarObservers();
	}
	
}

