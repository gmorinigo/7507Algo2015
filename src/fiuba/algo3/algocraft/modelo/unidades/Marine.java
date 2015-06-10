package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.construciones.Barraca;


public class Marine implements Unidad {

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
