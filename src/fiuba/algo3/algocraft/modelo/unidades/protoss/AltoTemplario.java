package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadCuatro;

public class AltoTemplario extends UnidadCuatro {

	public AltoTemplario() {
		this.tamanioTransporte = 2;
	}
	
	public void disparar() {}
	
	@Override
	protected Salud saludInicial() {
		return null;
	}

	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(5, this);
	} 

}
