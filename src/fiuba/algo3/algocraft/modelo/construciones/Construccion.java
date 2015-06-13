package fiuba.algo3.algocraft.modelo.construciones;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;

public abstract class Construccion implements TurnoObserver{

	protected ArrayList<Celda> celdas;
	protected Jugador jugador;
	protected Posicion posicionCeldaSupIzquierda;
	protected ConstruccionEstado estado;
	
	public Construccion(Posicion unaPosicion, Jugador jugador) {
		this.celdas = new ArrayList<Celda>();
		this.jugador = jugador;
		this.posicionCeldaSupIzquierda = unaPosicion;
		this.estado = new ConstruccionEstadoNaciendo(this.turnosNecesariosParaCreacion(), this);
	}

	public boolean estaTerminada(){
		return this.estaOperativa();
	}
	
	@Override
	public void finDeTurno(Turno turno) {
		this.estado.avanzarEnElTurno();
	}

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
	
	public Posicion damePosicionCeldaSupIzquierda(){
		return this.posicionCeldaSupIzquierda;
	}
	
	public void agregarCeldas(ArrayList<Celda> celdas) {
		this.celdas = celdas;
	}
	
	public void finalizarNacimiento() {
		this.estado = new ConstruccionEstadoViviendo(this);
	}
	
	public void finalizarCreacion() {
		this.estado = new ConstruccionEstadoViviendo(this);
	}
	
	public void terminarTrabajo() {
		this.estado = new ConstruccionEstadoViviendo(this);
	}
	
	public void destruir() {
		//TODO
	}
	
	public boolean estaOperativa() {
		return this.estado.estaOperativa();
	}
	
	abstract protected int turnosNecesariosParaCreacion();
	abstract protected void vivir();

}
