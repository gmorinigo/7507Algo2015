package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTres;

public class Scout extends UnidadTres {

	public Scout() {	}
	public void disparar() {}
	
	@Override
	protected Salud saludInicial() {
		return null;
	}

	public boolean esUnidadAerea(){
		return true;
	}
	
	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(8, this);
	}
}
