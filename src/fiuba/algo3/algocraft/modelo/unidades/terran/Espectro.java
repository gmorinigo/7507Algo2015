package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTres;

public class Espectro extends UnidadTres {

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
