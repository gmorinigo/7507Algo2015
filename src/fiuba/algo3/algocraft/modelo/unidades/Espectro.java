package fiuba.algo3.algocraft.modelo.unidades;

public class Espectro implements Unidad2 {

	private String nombre;

	
	public Espectro(String nombre/*, DisparoStrategy disparoStrategy*/){
		this.nombre=nombre;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	
	@Override
	public void disparar() {  
		//		this.disparoStrategy.disparar();
		}
	
	
	@Override
	public String getName(){ return this.nombre; }
	
	

}
