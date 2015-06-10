package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.UnidadTerrestre;

public class NaveTransporte extends UnidadTerrestre {

	private String nombre;

	public NaveTransporte(String nombre/*, DisparoStrategy disparoStrategy*/){
		this.nombre=nombre;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	@Override
	public void disparar() {}

	@Override
	public String getName() {return this.nombre;}

}
