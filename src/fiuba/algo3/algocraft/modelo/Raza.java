package fiuba.algo3.algocraft.modelo;

import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;

public abstract class Raza {
	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
	
	public  AbstractUnidadFactory getFactoryUnidades() {
		return null;
	}
	    
}
