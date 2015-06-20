package fiuba.algo3.algocraft.modelo;

public class JugadorEstadoGanador extends JugadorEstado{

	public JugadorEstadoGanador(Jugador jugador) {
		super(jugador);
	}

	@Override
	public JugadorEstado nextEstado() {
		return this;
	}

	@Override
	public EstadoDelJugador dameEstadoActual() {
		return EstadoDelJugador.Ganador;
	}
}
