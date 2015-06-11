package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;

public class RazaProtoss extends Raza {

	public  AbstractUnidadFactory getFactoryUnidades() {
		return new UnidadFactoryTerran();
	}

	@Override
	public int dameCapacidadDePoblacion(ArrayList<Construccion> construccionesTerminadas) {
		//Idem Terran
		return 0;
	}
	    		  
}
