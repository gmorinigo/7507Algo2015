package fiuba.algo3.algocraft.modelo.unidades;

public class NaveTransporte implements Unidad {

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
