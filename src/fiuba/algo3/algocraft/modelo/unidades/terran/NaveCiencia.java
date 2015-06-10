package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.UnidadVoladora;

public class NaveCiencia extends UnidadVoladora {

	
	private String nombre;

	public NaveCiencia(String nombre/*, DisparoStrategy disparoStrategy*/){
		this.nombre=nombre;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	
	@Override
	public void disparar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() { return this.nombre; }
	
	}
