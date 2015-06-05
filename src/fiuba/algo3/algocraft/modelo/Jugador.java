package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Jugador {
	
	private String nombreJugador;
	protected Raza raza;
	protected ArrayList<Construccion> contruccionesTerminadas;
	protected ArrayList<Unidad> unidadesTerminadas;
	private Almacen almacenGas;
	private Almacen almacenMineral;

	public Jugador(String unNombre,Raza raza) {
		this.raza = raza;
		this.contruccionesTerminadas = new ArrayList<Construccion>();
		this.unidadesTerminadas = new ArrayList<Unidad>();
		this.almacenGas = new Almacen(500);
		this.almacenMineral = new Almacen(500);
		this.nombreJugador = unNombre;
	}
	
	public Jugador(Raza raza, Almacen mineral, Almacen gas) {
		this.raza = raza;
		this.almacenGas = gas;
		this.almacenMineral = mineral;
	}

	public ArrayList<Construccion> dameContruccionesTerminadas() {
		return this.contruccionesTerminadas;
	}
	
	public ArrayList<Unidad> dameUnidadesTerminadas() {
		return this.unidadesTerminadas;
	}
	
	public void agregarUnidad(Unidad unidad) {
		this.unidadesTerminadas.add(unidad);
	}
	
	public void agregarConstruccion(Construccion construccion) {
		this.contruccionesTerminadas.add(construccion);
	}

	public Almacen dameAlmacenGas() {
		return this.almacenGas;
	}
	
	public Almacen dameAlmacenMineral() {
		return this.almacenMineral;
	}

	public boolean tieneElMismoNombre(String unNombre){
		return (this.nombreJugador == unNombre);
	}

	public boolean verificarPoblacion() {
	return ((unidadesTerminadas.size() + 1) <= 200);
		
	}
}