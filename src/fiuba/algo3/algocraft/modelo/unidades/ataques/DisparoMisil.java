package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DisparoMisil extends AbstractDisparo{

	protected int radio;
	
	public DisparoMisil(Unidad unidad, int radio) {
		super(unidad);
		this.radio = radio;
	}

	@Override
	public boolean disparar(Celda objetivo) {
		return false;
	}

}
