package fiuba.algo3.algocraft;

import java.util.ArrayList;

public abstract class Unidades implements TurnoObserver{

	protected int turnoInicial;
	protected ArrayList<Celda> unidades;
	protected Celda celda; 
	protected boolean estaOperativo;
	
	public Unidades(){
		unidades = new ArrayList<Celda>();
		this.estaOperativo = false;
	}

	public abstract boolean estaListoParaCrearse();
	
	public void crearUnidad(Turno unTurno) {
		unTurno.setObserver(this);
	}

	public abstract boolean estaTerminado();

}
