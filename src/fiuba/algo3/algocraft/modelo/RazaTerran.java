package fiuba.algo3.algocraft.modelo;

import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryProtoss;


public class RazaTerran extends Raza{

	public  AbstractUnidadFactory getFactoryUnidades() {
		return new UnidadFactoryProtoss();
	}
	    		  
}
