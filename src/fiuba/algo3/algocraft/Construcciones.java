package fiuba.algo3.algocraft;

public abstract class Construcciones {

	protected int turnoInicial;
	protected Turno turno;
	
	public abstract boolean estaTerminada();

	public void agregarTurno(Turno unTurno) {
		turnoInicial = unTurno.dameTurno();
		turno = unTurno;
	}

}
