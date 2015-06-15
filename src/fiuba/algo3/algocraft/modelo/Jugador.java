package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;
import java.util.Iterator;

import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Jugador {
	
	private String nombreJugador;
	private String colorJugador;
	protected Raza raza;
	protected ArrayList<Construccion> construccionesTerminadas;
	protected ArrayList<Unidad> unidadesTerminadas;
	private Almacen almacenGas;
	private Almacen almacenMineral;

	public Jugador(String unNombre,Raza raza, String unColor) throws JugadorConNombreDemasiadoCortoException {
		if (unNombre.length() < 4){
			throw new JugadorConNombreDemasiadoCortoException();
		}
		
		this.raza = raza;
		this.construccionesTerminadas = new ArrayList<Construccion>();
		this.unidadesTerminadas = new ArrayList<Unidad>();
		this.almacenGas = new Almacen(500);
		this.almacenMineral = new Almacen(500);
		this.nombreJugador = unNombre;
		this.colorJugador = unColor;
	}

	public Jugador(Raza raza, Almacen mineral, Almacen gas) {
		this.raza = raza;
		this.almacenGas = gas;
		this.almacenMineral = mineral;
	}

	public ArrayList<Construccion> dameConstruccionesTerminadas() {
		return this.construccionesTerminadas;
	}
	
	public ArrayList<Unidad> dameUnidadesTerminadas() {
		return this.unidadesTerminadas;
	}
	
	public void agregarUnidad(Unidad unidad) {
		this.unidadesTerminadas.add(unidad);
	}
	
	public void agregarConstruccion(Construccion construccion) {
		this.construccionesTerminadas.add(construccion);
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

	public boolean tieneElMismoColor(String unColor){
		return (this.colorJugador == unColor);
	}
	
	public boolean verificarPoblacion() {
	return ((unidadesTerminadas.size() + 1) <= this.dameLimiteDePoblacion());
		
	}

	public String dameNombre() {
		return this.nombreJugador;
	}

	public String dameColor() {
		return this.colorJugador;
	}
	public Raza dameRaza() {
		return this.raza;
	}

	public int dameCantidadGas() {
		return this.almacenGas.cantidad();
	}
	
	public int dameCantidadMineral() {
		return this.almacenMineral.cantidad();
	}
	
	public int dameLimiteDePoblacion() {
		return raza.dameCapacidadDePoblacion(this.dameConstruccionesTerminadas());
	}

	public void verificarConstruccionCreada(TipoConstruccion tipo) throws ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException {
		Iterator<Construccion> it = construccionesTerminadas.iterator();
		boolean construccionEncontrada = false;
		
		while (it.hasNext() && !construccionEncontrada ) {
			construccionEncontrada = it.next().verificarTipoConstruccion(tipo);	
		}
		
		if (!construccionEncontrada){
			switch (tipo){
			case creadorUnidadesVoladoras: 
				throw new ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException();
			case creadorUnidadesBasicas: 
				if (this.raza instanceof RazaProtoss){
					throw new ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException();
				}
				else{
					throw new ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException();
				}
			default:
				break;
			}			
		}
		
	}

}