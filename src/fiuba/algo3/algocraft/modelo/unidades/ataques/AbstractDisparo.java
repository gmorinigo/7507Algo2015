package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

abstract public class AbstractDisparo {
	
	protected Unidad unidad;
	protected int radio;
	
	public AbstractDisparo(Unidad unidad, int radio) {
		this.unidad = unidad;
		this.radio = radio;
	}
	
	public abstract boolean disparar(Celda objetivo) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
	
}
