package fiuba.algo3.algocraft.modelo.unidades;


public class UnidadTerran implements Unidad {

	private String nombre;
	//private DisparoStrategy disparoStrategy;
	
	public UnidadTerran(String nombre/*, DisparoStrategy disparoStrategy*/){
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.nombre;
	}



}
