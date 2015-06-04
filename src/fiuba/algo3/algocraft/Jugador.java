package fiuba.algo3.algocraft;

import java.util.ArrayList;

public class Jugador {
	
	protected Raza raza;
	protected ArrayList<Construccion> contruccionesTerminadas;
	protected ArrayList<Unidad> unidadesTerminadas;

	public Jugador(Raza raza) {
		this.raza = raza;
		this.contruccionesTerminadas = new ArrayList<Construccion>();
		this.unidadesTerminadas = new ArrayList<Unidad>();
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

}
