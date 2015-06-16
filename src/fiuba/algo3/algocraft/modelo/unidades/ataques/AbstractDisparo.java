package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

abstract public class AbstractDisparo {
	
	protected Unidad unidad;
	
	public AbstractDisparo(Unidad unidad) {
		this.unidad = unidad;
	}
	
	public abstract boolean disparar(Celda objetivo);
	
}
