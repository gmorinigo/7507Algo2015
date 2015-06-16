package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoescansando;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;


public class Marine extends UnidadTerran {

	//private DisparoStrategy disparoStrategy;


	public Marine() {
		this.tamanioTransporte = 1;
//		this.disparoStrategy=disparoStrategy;
//		this.disparoStrategy.setUnidad(this);
	}
	
	protected Salud saludInicial() {
		return new SaludTerran(40);
	}

	public int turnosNecesariosParaCreacion() {
		return 3;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
	}
	
	public int DanioAtaque() {
		return 6;
	} 
}
