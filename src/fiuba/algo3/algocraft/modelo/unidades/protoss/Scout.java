package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadVoladora;

public class Scout extends UnidadVoladora {

	private String nombre;
	public Scout(String nombre) {	}
	public void disparar() {}
	public String getName(){ return this.nombre; }
	
	
	@Override
	protected Salud saludInicial() {
		// TODO Auto-generated method stub
		return null;
	} 

}
