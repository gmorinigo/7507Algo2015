package fiuba.algo3.algocraft.modelo;

abstract public class JugadorEstado {

	protected Jugador jugador;
	public enum EstadoDelJugador{Ganador, Jugando, Perdedor}

	public JugadorEstado(Jugador jugador) {
		this.jugador = jugador;
	}
	
	abstract public JugadorEstado nextEstado();
	abstract public EstadoDelJugador dameEstadoActual();
	
}
