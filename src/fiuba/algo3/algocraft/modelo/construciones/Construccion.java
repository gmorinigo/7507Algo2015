package fiuba.algo3.algocraft.modelo.construciones;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.CeldaTerrestre;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;

public abstract class Construccion implements TurnoObserver{

	protected ArrayList<Celda> celdas;
	protected boolean estaOperativa;
	protected Celda celdaSupIzquierda;
	protected Jugador jugador;
	
	public Construccion(Posicion unaPosicion, Jugador jugador) {
		this.celdas = new ArrayList<Celda>();
		this.perteneceA(jugador);
		this.estaOperativa = false;
		this.celdaSupIzquierda = new CeldaTerrestre(unaPosicion.dameFila(),unaPosicion.dameColumna());
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
	

	public void crearEstructura(Turno unTurno) throws CeldaOcupadaException, NoReuneLosRequisitosException{
		if( ! this.reuneLosRequisitos(this.jugador)) {
			throw new NoReuneLosRequisitosException();
		}
		
		unTurno.setObserver(this);
		Mapa.getInstance().agregarConstruccion(this);
		this.jugador.agregarConstruccion(this);
	}
	
	abstract public boolean reuneLosRequisitos(Jugador jugador);
	abstract public int costoGas();
	abstract public int costoMineral();


	public ArrayList<Celda> dameCeldas(){
		return celdas;
	}
	
	public Celda dameCeldaSupIzquierda(){
		return this.celdaSupIzquierda;
	}
	
	public void agregarCeldas(ArrayList<Celda> celdas) {
		this.celdas = celdas;
	}
	
	protected void perteneceA(Jugador jugador) {
		this.jugador = jugador;
	}
	
	

}
