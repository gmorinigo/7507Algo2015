package fiuba.algo3.algocraft;

import java.util.ArrayList;

public abstract class Unidades {

	protected int turnoInicial;
	protected Turno turno;
	protected ArrayList<Celda> unidades;
	protected Celda celda; 
	protected Construcciones construcciones;
	
	public Unidades(){
		unidades = new ArrayList<Celda>();
	}

	public abstract boolean estaListoParaCrearse();
	
	public void crearUnidad(Turno unTurno) {
		turnoInicial = unTurno.dameTurno();
		turno = unTurno;
	}

	public abstract boolean estaTerminado();

}
