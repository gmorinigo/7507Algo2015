package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadUno;

public class Zealot extends UnidadUno{

	public Zealot() {
		this.tamanioTransporte = 2;
	}
	
	public void disparar() {}
	
	@Override
	protected Salud saludInicial() {
		return null;
	}
	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(4, this);
	}
	
	
}
