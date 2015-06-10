package fiuba.algo3.algocraft.modelo.unidades;

public class Golliat implements Unidad {

	private String nombre;
	//private DisparoStrategy disparoStrategy;
	
	public Golliat(String nombre/*, DisparoStrategy disparoStrategy*/){
		this.nombre=nombre;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
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
