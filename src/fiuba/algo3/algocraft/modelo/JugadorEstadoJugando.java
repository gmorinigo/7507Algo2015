package fiuba.algo3.algocraft.modelo;

public class JugadorEstadoJugando extends JugadorEstado{

	public JugadorEstadoJugando(Jugador jugador) {
		super(jugador);
	}

	@Override
	public JugadorEstado nextEstado() {
		
		if(this.jugador.dameConstrucciones().isEmpty() &&
		this.jugador.dameUnidadesTerminadas().isEmpty()) {
			return new JugadorEstadoPerdedor(this.jugador);
		}
		
		return this;
		
	}

	@Override
	public EstadoDelJugador dameEstadoActual() {
		return EstadoDelJugador.Jugando;
	}

}
