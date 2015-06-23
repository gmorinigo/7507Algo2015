package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DisparoEMP extends AbstractDisparo{

	public DisparoEMP(Unidad unidadQueDispara, int radio) {
		super(unidadQueDispara, radio);
		
	}
 
	public boolean disparar(Celda objetivo) {
		return objetivo.atacarRadioDeLaCeldaConMisilEMP(this.unidad);
	}

}
