package fiuba.algo3.algocraft.modelo;

public class JugadorEstadoFueraDeJuego extends JugadorEstado{

	public JugadorEstadoFueraDeJuego(Jugador jugador) {
		super(jugador);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JugadorEstado nextEstado() {
		return this;
	}

	@Override
	public EstadoDelJugador dameEstadoActual() {
		return EstadoDelJugador.FueraDeJuego;
	}

}
