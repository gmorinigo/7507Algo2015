package fiuba.algo3.algocraft;

import java.util.ArrayList;

public class Jugador {
	
	protected Raza raza;
	protected ArrayList<Construccion> contruccionesTerminadas;

	public Jugador(Raza raza) {
		this.raza = raza;
		this.contruccionesTerminadas = new ArrayList<Construccion>();
	}
	
	public ArrayList<Construccion> dameContruccionesTerminadas() {
		return this.contruccionesTerminadas;
	}

}
