package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadCuatro;

public class AltoTemplario extends UnidadCuatro {

	private String nombre;
	public AltoTemplario(String nombre) {	}
	public void disparar() {}
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
