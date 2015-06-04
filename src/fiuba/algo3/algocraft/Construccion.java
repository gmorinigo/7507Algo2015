package fiuba.algo3.algocraft;

import java.util.ArrayList;

public abstract class Construccion implements TurnoObserver{

	protected ArrayList<Celda> celdas;
	protected boolean estaOperativa;
	protected Celda celdaSupIzquierda;
	
	
	public Construccion(Posicion unaPosicion) {
		this.celdas = new ArrayList<Celda>();
		this.estaOperativa = false;
		this.celdaSupIzquierda = new Celda(unaPosicion.dameFila(),unaPosicion.dameColumna());
	}

	
	public boolean estaTerminada() {
		return this.estaOperativa;
	}

	public void finDeTurnos() {
		this.terminarConstruccion();
	}

	public void terminarConstruccion() {
		this.estaOperativa = true;
	}
	

	public void crearEstructura(Turno unTurno) throws CeldaOcupadaException{
		unTurno.setObserver(this);
		Mapa.getInstance().agregarConstruccion(this);
	}
	
	public ArrayList<Celda> dameCeldas(){
		return celdas;
	}
	
	public Celda dameCeldaSupIzquierda(){
		return this.celdaSupIzquierda;
	}
	
	public void agregarCeldas(ArrayList<Celda> celdas) {
		this.celdas = celdas;
	}

}
