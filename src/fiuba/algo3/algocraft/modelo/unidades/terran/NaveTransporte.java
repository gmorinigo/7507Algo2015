package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadCinco;

public class NaveTransporte extends UnidadCinco {

	public NaveTransporte(){

		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	@Override
	public void disparar() {}

	@Override
	protected Salud saludInicial() {
		return null;
	}
	
	public boolean esUnidadAerea(){
		return true;
	}


	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(5, this);
	}

}
