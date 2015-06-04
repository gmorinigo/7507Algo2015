package fiuba.algo3.algocraft.modelo.unidades;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;

public abstract class Unidad implements TurnoObserver{

	protected int turnoInicial;
	protected ArrayList<Celda> unidades;
	protected Celda celda; 
	protected boolean estaOperativo;
	
	public Unidad(){
		unidades = new ArrayList<Celda>();
		this.estaOperativo = false;
	}

	public void crearUnidad(Turno unTurno) {
		unTurno.setObserver(this);
	}

	public abstract boolean estaTerminado();

}
