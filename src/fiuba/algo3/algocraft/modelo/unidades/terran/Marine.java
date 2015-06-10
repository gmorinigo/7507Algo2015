package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.UnidadTerrestre;


public class Marine extends UnidadTerrestre {

	private String nombre;
	//private DisparoStrategy disparoStrategy;
	
	public Marine(String nombre/*, DisparoStrategy disparoStrategy*/){
		this.nombre=nombre;
//		this.disparoStrategy=disparoStrategy;
//		this.disparoStrategy.setUnidad(this);
	}
	
	public void disparar()
	{
//		this.disparoStrategy.disparar();
	}
	
	public String toString(){
		return "Unidad "+nombre;
	}
	public String getName(){ return this.nombre; } 


}
