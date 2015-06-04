package fiuba.algo3.algocraft;

public class Turno {

	private int turno = 1;
	protected int cantidad;
	protected boolean estaFinalizado;
	protected TurnoObserver observer;
	
	public Turno(int cantidad) {
		this.cantidad = cantidad;
		this.estaFinalizado = false;
	}

	public int dameTurno() {
		return turno;
	}

	public void aumentarTurno() {
		turno += 1;
		if(this.turno == this.cantidad) {
			this.estaFinalizado = true;
			this.observer.finDeTurnos();
		}
	}
	
	public int estaFinalizado() {
		return this.estaFinalizado();
	}


	public void setObserver(TurnoObserver observer) {
		this.observer = observer;
	}

}
