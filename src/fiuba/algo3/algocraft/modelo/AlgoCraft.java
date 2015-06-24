package fiuba.algo3.algocraft.modelo;

import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoColorException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoNombreException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConMismaRazaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCantidadDeJugadoresSuperadaException;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;

public class AlgoCraft {
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
	throws JugadorConElMismoNombreException, JugadorConElMismoColorException, JugadorConNombreDemasiadoCortoException, JugadorConMismaRazaException{
		if (this.jugador1.tieneElMismoNombre(nombreJugador)){
			throw new JugadorConElMismoNombreException();
		}
		
		if (this.jugador1.tieneElMismoColor(colorJugador)){
			throw new JugadorConElMismoColorException();
		}
		
		if(this.jugador1.dameRaza().equals(unaRaza)) {
			throw new JugadorConMismaRazaException();
		}
		
		this.jugador2 = new Jugador(nombreJugador, unaRaza, colorJugador);
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
	
}

