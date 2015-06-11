package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.DepositoDeSuministro;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;

public abstract class Raza {
	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
	
	public  AbstractUnidadFactory getFactoryUnidades() {
		return null;
	}

	public abstract int dameCapacidadDePoblacion(ArrayList<Construccion> construccionesTerminadas);
	    
}
