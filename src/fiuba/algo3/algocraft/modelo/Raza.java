package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;

public abstract class Raza {
	
	protected AbstractUnidadFactory unidadFactory;
	protected AbstractConstruccionFactory construccionFactory;
	
	public Raza(AbstractUnidadFactory unidadFactory, AbstractConstruccionFactory construccionFactory) {
		this.unidadFactory = unidadFactory;
		this.construccionFactory = construccionFactory;
	}

	public  AbstractUnidadFactory getFactoryUnidades() {
		return this.unidadFactory;
	}

	public AbstractConstruccionFactory getFactoryConstrucciones() {
		return this.construccionFactory;
	}
	
	public abstract int dameCapacidadDePoblacion(ArrayList<Construccion> construccionesTerminadas);
	    
}
