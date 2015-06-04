package fiuba.algo3.algocraft;

import java.util.ArrayList;

public abstract class Construccion implements TurnoObserver{

	protected ArrayList<Celda> celdas;
	protected boolean estaOperativa;
	
	
	public Construccion(Posicion unaPosicion) {
		Mapa.getInstance().agregarConstruccion(this);
		this.celdas = new ArrayList<Celda>();
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
	
	public void agregarCeldas(ArrayList<Celda> celdas) {
		this.celdas = celdas;
	}

}
