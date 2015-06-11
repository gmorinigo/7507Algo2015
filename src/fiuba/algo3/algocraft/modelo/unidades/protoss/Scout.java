package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTres;

public class Scout extends UnidadTres {

	private String nombre;
	public Scout(String nombre) {	}
	public void disparar() {}
	public String getName(){ return this.nombre; }
	
	
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
