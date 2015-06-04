package fiuba.algo3.algocraft.modelo;

import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoNombreException;
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
	
	public void agregarJugador(String nombreJugador, Raza unaRaza) throws MaximaCantidadDeJugadoresSuperadaException, JugadorConElMismoNombreException{
		if (this.jugador1 == null){
			this.agregarJugadorNumero1(nombreJugador, unaRaza);
		}
		else{
			if (this.jugador2 == null){
				this.agregarJugadorNumero2(nombreJugador, unaRaza);
			}
			else{
				throw new MaximaCantidadDeJugadoresSuperadaException();
			}
		}
	}
	
	public void agregarJugadorNumero1(String nombreJugador, Raza unaRaza){
		this.jugador1 = new Jugador(nombreJugador, unaRaza);
	}
	
	public void agregarJugadorNumero2(String nombreJugador, Raza unaRaza) throws JugadorConElMismoNombreException{
		if (this.jugador1.tieneElMismoNombre(nombreJugador)){
			throw new JugadorConElMismoNombreException();
		}
		this.jugador2 = new Jugador(nombreJugador, unaRaza);
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
	
}

