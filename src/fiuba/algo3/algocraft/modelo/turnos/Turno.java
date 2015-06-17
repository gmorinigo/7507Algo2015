package fiuba.algo3.algocraft.modelo.turnos;

import java.util.ArrayList;
import java.util.Iterator;

import fiuba.algo3.algocraft.modelo.Jugador;

public class Turno {
	
	protected int turno;	
	protected Jugador jugador1;
	protected Jugador jugador2;
	protected Jugador jugadorConTurno;
	protected ArrayList<TurnoObserver> observadores;
	protected boolean terminado;
	
	public Turno(Jugador jugador1, Jugador jugador2) {
		this.turno = 1;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.jugadorConTurno = jugador1;
		this.observadores = new ArrayList<TurnoObserver>();
		this.terminado = false;
	}

	public void avanzarTurno() {
		if(this.terminado)
			return;
		Iterator<TurnoObserver> it = this.observadores.iterator();
		
		while (it.hasNext()) {
			it.next().finDeTurno(this);	
		}
		
		this.jugadorConTurno = (this.jugadorConTurno == this.jugador1) ? this.jugador2 : this.jugador1;
		this.turno++;
	}
	
	public void addObserver(TurnoObserver observer) {
		this.observadores.add(observer);
	}

	public int dameTurno() {
		return this.turno;
	}
	
    public Jugador dameJugador(){
    	return this.jugadorConTurno; 	
    }
    
    public void terminar() {
		this.terminado = true;
	}

}
