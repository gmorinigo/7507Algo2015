package fiuba.algo3.algocraft.modelo.turnos;

import java.util.ArrayList;
import java.util.Iterator;


public class Turno {
	
	protected int turno;
	
	protected ArrayList<TurnoObserver> observadores;
	
	protected TurnoObserver observer;
	
	public Turno() {
		this.turno = 1;
		this.observadores = new ArrayList<TurnoObserver>();
	}

	public void aumentarTurno() {
		Iterator<TurnoObserver> it = this.observadores.iterator();
		
		while (it.hasNext()) {
			it.next().finDeTurno(this);
		}
		
		this.turno++;
	}

	public void addObserver(TurnoObserver observer) {
		this.observer = observer;
	}

	public int dameTurno() {
		return this.turno;
	}

}
