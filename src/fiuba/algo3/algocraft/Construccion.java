package fiuba.algo3.algocraft;

import java.util.ArrayList;

public abstract class Construccion implements TurnoObserver{

	protected ArrayList<Celda> celdas;
	protected Celda celda;
	protected boolean estaOperativa;
	
	
	public Construccion(Posicion unaPosicion) {
		Celda unaCelda = new Celda(unaPosicion.dameFila(),unaPosicion.dameColumna());
		this.celdas = new ArrayList<Celda>();
		celda = unaCelda;
		this.estaOperativa = false;
	}

	public abstract boolean estaTerminada();
	
	public abstract void terminarConstruccion();

	public void crearEstructura(Turno unTurno) {
		unTurno.setObserver(this);
	}
	
	public ArrayList<Celda> dameCeldas(){
		return celdas;
	}

}
