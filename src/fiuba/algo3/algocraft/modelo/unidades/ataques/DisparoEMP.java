package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DisparoEMP extends AbstractDisparo{

	public DisparoEMP(Unidad unidad, int radio) {
		super(unidad, radio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean disparar(Celda objetivo) {
		return objetivo.atacarUnidadDeLaCeldaConUnidad(this.unidad);
		
		//return false;
	}

}
