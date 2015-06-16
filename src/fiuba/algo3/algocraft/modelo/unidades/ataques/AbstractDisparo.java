package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

abstract public class AbstractDisparo {
	
	protected Unidad unidad;
	protected int radio;
	
	public AbstractDisparo(Unidad unidad, int radio) {
		this.unidad = unidad;
		this.radio = radio;
	}
	
	public abstract boolean disparar(Celda objetivo);
	
}
