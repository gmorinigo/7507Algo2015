package fiuba.algo3.algocraft.modelo;

import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;

public class RazaProtoss extends Raza {

	public  AbstractUnidadFactory getFactoryUnidades() {
		return new UnidadFactoryTerran();
	}
	    		  
}
