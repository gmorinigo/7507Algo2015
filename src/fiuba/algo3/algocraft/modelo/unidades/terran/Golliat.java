package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadDos;

public class Golliat extends UnidadDos {

	//private DisparoStrategy disparoStrategy;
	
	public Golliat() {
		this.tamanioTransporte = 2;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}

	public void disparar()
	{
//		this.disparoStrategy.disparar();
	}
	
	@Override
	protected Salud saludInicial() {
		return null;
	}

	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(6, this);
	} 
}
