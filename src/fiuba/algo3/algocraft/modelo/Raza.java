package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;

public abstract class Raza {
	
	public enum TipoRaza {Protoss, Terran};
	protected TipoRaza raza;
	
	protected AbstractUnidadFactory unidadFactory;
	protected AbstractConstruccionFactory construccionFactory;
	
	public Raza(AbstractConstruccionFactory construccionFactory, AbstractUnidadFactory unidadFactory) {
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

	
	public boolean equals(Raza raza) {
		return (raza.raza == this.raza);
	}

	public abstract boolean esRazaProtoss();
}
