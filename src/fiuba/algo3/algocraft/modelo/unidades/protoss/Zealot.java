package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadUno;

public class Zealot extends UnidadUno{

	private String nombre;
	public Zealot(String nombre) {	}
	public void disparar() {}
	public String getName(){ return this.nombre; }
	
	
	@Override
	protected Salud saludInicial() {
		// TODO Auto-generated method stub
		return null;
	} 
}
