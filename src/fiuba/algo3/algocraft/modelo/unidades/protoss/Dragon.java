package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadDos;

public class Dragon extends UnidadDos {

	private String nombre;
	
	public Dragon(String nombre) {
	}

	@Override
	public void disparar() {
	}
	public String getName(){ return this.nombre; }

	@Override
	protected Salud saludInicial() {
		return null;
	}

	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(5, this);
	}
	
}
