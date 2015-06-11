package fiuba.algo3.algocraft.modelo.turnos;

import java.util.ArrayList;
import java.util.Iterator;

import fiuba.algo3.algocraft.modelo.Jugador;


public class Turno {
	
	protected int turno;	
	protected ArrayList<TurnoObserver> observadores;
	public Jugador jugador; 
	
	public Turno(Jugador unJugador) {
		this.turno = 1;
		this.observadores = new ArrayList<TurnoObserver>();
		this.jugador = unJugador;
	}

	public void aumentarTurno() {
		Iterator<TurnoObserver> it = this.observadores.iterator();
		
		while (it.hasNext()) {
			it.next().finDeTurno(this);	
		}
		
		this.turno++;
	}

	public void addObserver(TurnoObserver observer) {
		this.observadores.add(observer);
	}

	public int dameTurno() {
		return this.turno;
	}
    public Jugador dameJugador(){
    	return this.jugador; 	
    }

}
