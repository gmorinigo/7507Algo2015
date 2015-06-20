package fiuba.algo3.algocraft.modelo;

public class JugadorEstadoPerdedor extends JugadorEstado {

	public JugadorEstadoPerdedor(Jugador jugador) {
		super(jugador);
	}

	@Override
	public JugadorEstado nextEstado() {
		return this;
	}

	@Override
	public EstadoDelJugador dameEstadoActual() {
		return EstadoDelJugador.Perdedor;
	}

}
