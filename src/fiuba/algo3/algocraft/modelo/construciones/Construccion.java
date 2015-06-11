package fiuba.algo3.algocraft.modelo.construciones;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.Construible;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;

public abstract class Construccion implements TurnoObserver, Construible{

	protected ArrayList<Celda> celdas;
	protected boolean estaOperativa;
	//protected Celda celdaSupIzquierda;
	protected Jugador jugador;
	protected Posicion posicionCeldaSupIzquierda;
	private ProgresoCreacion progresoCreacion;
	private boolean naciendo;
	
	public Construccion(Posicion unaPosicion, Jugador jugador) {
		this.celdas = new ArrayList<Celda>();
		this.perteneceA(jugador);
		this.estaOperativa = false;
		this.naciendo = true;
		//this.celdaSupIzquierda = new CeldaTerrestre(unaPosicion.dameFila(),unaPosicion.dameColumna());
		this.posicionCeldaSupIzquierda = unaPosicion;
		this.progresoCreacion = this.progresoCreacion();
	}

	public Construccion(){
		
	}
	
	public boolean estaTerminada() {
		return this.estaOperativa && !this.naciendo;
	}

	@Override
	public void finDeTurno(Turno turno) {
		if(this.naciendo) {
			this.progresoCreacion.avanzarProgreso();
		}
	}

	public void terminarConstruccion() {
		this.estaOperativa = true;
		this.naciendo = false;
	}
	

	public abstract void crearEstructura(Turno unTurno) throws CeldaOcupadaException, NoReuneLosRequisitosException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
	
	abstract public boolean reuneLosRequisitos(Jugador jugador);
	abstract public int costoGas();
	abstract public int costoMineral();
	
	public boolean construccionRecolectoraDeMineral(){
		return false;
	}
	
	public boolean construccionRecolectoraDeGas(){
		return false;
	}

	public ArrayList<Celda> dameCeldas(){
		return celdas;
	}
	
	/*
	public Celda dameCeldaSupIzquierda(){
		return this.celdaSupIzquierda;
	}*/
	
	public Posicion damePosicionCeldaSupIzquierda(){
		return this.posicionCeldaSupIzquierda;
	}
	
	public void agregarCeldas(ArrayList<Celda> celdas) {
		this.celdas = celdas;
	}
	
	protected void perteneceA(Jugador jugador) {
		this.jugador = jugador;
	}
	
	abstract protected ProgresoCreacion progresoCreacion();
	
	@Override
	public void finalizarCreacion() {
		this.terminarConstruccion();
	}
	
	

}
