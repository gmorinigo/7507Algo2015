package fiuba.algo3.algocraft.modelo.turnos;


public class TurnoInfinito extends Turno{

	private int turno = 1;
	protected int cantidad;
	protected boolean estaFinalizado;
	
	public TurnoInfinito() {
		super(1);
		this.estaFinalizado = false;
	}
	
	
	public int dameTurno() {
		return turno;
	}

	public void aumentarTurno() {
		turno += 1;
		this.observer.finDeTurno();
	}
	
	public void finalizar() {
		this.estaFinalizado = true;
	}
	
	public int estaFinalizado() {
		return this.estaFinalizado();
	}

}
