package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DisparoNormal extends AbstractDisparo{

	public DisparoNormal(Unidad unidad, int radio) {
		super(unidad, radio);
	}

	public boolean disparar(Celda objetivo) {
		return objetivo.atacarUnidadDeLaCeldaConUnidad(this.unidad);
	}
}
