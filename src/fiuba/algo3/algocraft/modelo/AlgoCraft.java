package fiuba.algo3.algocraft.modelo;

import fiuba.algo3.algocraft.modelo.mapa.Mapa;

public class AlgoCraft {
	private Mapa mapaDelJuego;
	private Jugador jugador1;
	private Jugador jugador2;
	
	public AlgoCraft() {
		this.mapaDelJuego = Mapa.getInstance();
	}
	
	public Mapa dameElMapaDelJuego(){
		return this.mapaDelJuego;
	}
}
